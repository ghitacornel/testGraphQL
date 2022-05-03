package demo;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@GraphQLTest
public class VehiclesTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    public void findAll() throws IOException {
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/findAll.graphql");
        assertThat(response.isOk()).isTrue();
    }
}
