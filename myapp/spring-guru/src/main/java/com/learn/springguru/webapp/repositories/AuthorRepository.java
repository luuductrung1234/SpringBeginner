package com.learn.springguru.webapp.repositories;

import com.learn.springguru.webapp.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
