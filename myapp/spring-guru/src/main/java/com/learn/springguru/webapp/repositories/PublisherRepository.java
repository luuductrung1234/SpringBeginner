package com.learn.springguru.webapp.repositories;

import com.learn.springguru.webapp.domain.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
