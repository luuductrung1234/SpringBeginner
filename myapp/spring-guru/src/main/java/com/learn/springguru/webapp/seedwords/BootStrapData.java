package com.learn.springguru.webapp.seedwords;

import com.learn.springguru.webapp.domain.Address;
import com.learn.springguru.webapp.domain.Author;
import com.learn.springguru.webapp.domain.Book;
import com.learn.springguru.webapp.domain.Publisher;
import com.learn.springguru.webapp.repositories.AuthorRepository;
import com.learn.springguru.webapp.repositories.BookRepository;
import com.learn.springguru.webapp.repositories.PublisherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(BootStrapData.class);

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("start " + BootStrapData.class.getSimpleName());

        var sfg = new Publisher("SFG Publishing", new Address("St Petersburg", "", "FL", ""));
        publisherRepository.save(sfg);

        var times = new Publisher("The Times Publishing", new Address("St Liberty", "New York", "New York", "20000"));
        publisherRepository.save(times);


        var eric = new Author("Eric", "Evans");
        var ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        var rod = new Author("Rod", "Johnson");
        var noEjb = new Book("J2EE Development without EJB", "3983324845");
        rod.getBooks().add(noEjb);
        noEjb.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEjb);

        logger.info("finish " + BootStrapData.class.getSimpleName());
    }
}
