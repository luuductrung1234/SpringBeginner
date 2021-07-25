package com.learn.springguru.webapp.repositories;

import com.learn.springguru.webapp.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
