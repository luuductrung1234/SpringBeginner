package com.learn.caching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BookRepositoryImpl implements BookRepository {
	@Override
	@Cacheable(cacheNames = "books-isbn", key = "#isbn.getCode()")
	public Book getByIsbn(Isbn isbn) throws InterruptedException {
		simulateSlowService();
		return new Book(isbn.getCode(), "Some book");
	}

	@Override
	@Cacheable("books-string")
	public Book getByIsbn(String isbn) throws InterruptedException {
		simulateSlowService();
		return new Book(isbn, "Some book");
	}

	@Override
	@Cacheable(cacheNames = "books-string", key = "#isbn")
	public Book getByIsbn(String isbn, boolean checkWarehouse, boolean includeUsed) throws InterruptedException {
		simulateSlowService();
		return new Book(isbn, "Some book");
	}

	@Override
	@Cacheable(cacheNames = "books-uuid")
	public Book get(UUID id) throws InterruptedException {
		simulateSlowService();
		return new Book(id, "isbn-1234", "Some book");
	}

	@Override
	@Cacheable(cacheNames = "books")
	public Book get(UUID id, boolean checkWarehouse, boolean includeUsed) throws InterruptedException {
		simulateSlowService();
		return new Book(id, "isbn-1234", "Some book");
	}

	@Override
	@CacheEvict(cacheNames = { "books-string" })
	public Book loadByIsbn(String isbn) throws InterruptedException {
		simulateSlowService();
		return new Book(isbn, "Some book");
	}

	@Override
	@CacheEvict(cacheNames = { "books", "books-string", "books-uuid" }, allEntries = true)
	public List<Book> load() throws InterruptedException {
		simulateSlowService();
		var books = new ArrayList<Book>();
		books.add(new Book("isbn-1234", "Some book"));
		books.add(new Book("isbn-4567", "Some book"));
		books.add(new Book("isbn-7890", "Some book"));
		return books;
	}

	// Don't do this at home
	private void simulateSlowService() throws InterruptedException {
		try {
			long time = 3000L;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			log.error("Interrupted!", e);
			// Restore interrupted state...
			Thread.currentThread().interrupt();
		}
	}
}