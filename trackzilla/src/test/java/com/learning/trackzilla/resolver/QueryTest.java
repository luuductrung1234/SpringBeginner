package com.learning.trackzilla.resolver;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.learning.trackzilla.entity.Application;
import com.learning.trackzilla.repository.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;

@GraphQLTest
public class QueryTest {
    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    ApplicationRepository applicationRepository;

    @Test
    public void getApplicationsTest() throws Exception{
        doReturn(new ArrayList<Application>()).when(applicationRepository).findAll();

        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/get-application.graphql");
        assertThat(response.isOk()).isTrue();
    }
}
