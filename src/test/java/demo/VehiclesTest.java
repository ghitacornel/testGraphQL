package demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.repositories.entities.Vehicle;
import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VehiclesTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ServletWebServerApplicationContext webServerAppCtxt;

    GraphQLWebClient graphQLWebClient;

    @BeforeEach
    public void setUp() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:" + webServerAppCtxt.getWebServer().getPort() + "/graphql")
                .build();
        graphQLWebClient = GraphQLWebClient.newInstance(webClient, objectMapper);
    }

    @Test
    public void testCreateRead() throws IOException {

        {
            GraphQLResponse entity = graphQLWebClient.post(GraphQLRequest.builder()
                            .query(Files.readString(Paths.get("src", "test", "resources", "graphql", "findAll.graphql").toAbsolutePath()))
                            .build())
                    .block();
            Assertions.assertThat(entity.getFirstList(Vehicle.class)).isEmpty();
        }

        Vehicle vehicle;
        {
            GraphQLResponse entity = graphQLWebClient.post(GraphQLRequest.builder()
                            .query(Files.readString(Paths.get("src", "test", "resources", "graphql", "createVehicle.graphql").toAbsolutePath()))
                            .variables(Map.of("modelCode", "1310", "type", "A", "brandName", "dacia")).build())
                    .block();
            vehicle = entity.getFirst(Vehicle.class);
            Assertions.assertThat(vehicle).isNotNull();
            Assertions.assertThat(vehicle.getId()).isNotNull();
            Assertions.assertThat(vehicle.getBrandName()).isEqualTo("dacia");
            Assertions.assertThat(vehicle.getType()).isEqualTo("A");
            Assertions.assertThat(vehicle.getModelCode()).isEqualTo("1310");
        }

        {
            GraphQLResponse entity = graphQLWebClient.post(GraphQLRequest.builder()
                            .query(Files.readString(Paths.get("src", "test", "resources", "graphql", "findById.graphql").toAbsolutePath()))
                            .variables(Map.of("id", vehicle.getId())).build())
                    .block();
            vehicle = entity.getFirst(Vehicle.class);
            Assertions.assertThat(vehicle).isNotNull();
            Assertions.assertThat(vehicle.getId()).isNotNull();
            Assertions.assertThat(vehicle.getBrandName()).isEqualTo("dacia");
            Assertions.assertThat(vehicle.getType()).isEqualTo("A");
            Assertions.assertThat(vehicle.getModelCode()).isEqualTo("1310");
        }

        {
            GraphQLResponse entity = graphQLWebClient.post(GraphQLRequest.builder()
                            .query(Files.readString(Paths.get("src", "test", "resources", "graphql", "deleteById.graphql").toAbsolutePath()))
                            .variables(Map.of("id", vehicle.getId())).build())
                    .block();
            Long id = entity.getFirst(Long.class);
            Assertions.assertThat(id).isEqualTo(vehicle.getId());
        }

        {
            GraphQLResponse entity = graphQLWebClient.post(GraphQLRequest.builder()
                            .query(Files.readString(Paths.get("src", "test", "resources", "graphql", "findById.graphql").toAbsolutePath()))
                            .variables(Map.of("id", vehicle.getId())).build())
                    .block();
            vehicle = entity.getFirst(Vehicle.class);
            Assertions.assertThat(vehicle).isNull();
        }
    }
}
