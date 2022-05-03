package demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.repositories.entities.Vehicle;
import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
//        properties = {
//                "server.port=8080"
//        })
public class VehiclesTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void findAll() throws IOException {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:" + "8080" + "/graphql")
                .build();

        GraphQLWebClient graphQLWebClient = GraphQLWebClient.newInstance(webClient, objectMapper);

        GraphQLResponse entity = graphQLWebClient.post(GraphQLRequest.builder()
                        .query(Files.readString(Paths.get("src", "test", "resources", "graphql", "findById.graphql").toAbsolutePath()))
                        .variables(Map.of("id", "1")).build())
                .block();
        Assertions.assertThat(entity.getFirst(Vehicle.class)).isNull();
    }
}
