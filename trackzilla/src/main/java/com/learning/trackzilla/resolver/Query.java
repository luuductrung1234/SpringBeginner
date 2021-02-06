package com.learning.trackzilla.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.learning.trackzilla.entity.Application;
import com.learning.trackzilla.repository.ApplicationRepository;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {
    private ApplicationRepository applicationRepository;

    public Query(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public Iterable<Application> findAllApplications() {
        return applicationRepository.findAll();
    }

    public long countApplications() {
        return applicationRepository.count();
    }
}
