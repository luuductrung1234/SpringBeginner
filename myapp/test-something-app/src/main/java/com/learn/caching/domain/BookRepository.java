package com.learn.caching.domain;

import java.util.List;
import java.util.UUID;

public interface BookRepository {
	Book loadByIsbn(String isbn) throws InterruptedException;

	List<Book> load() throws InterruptedException;

	Book getByIsbn(Isbn isbn) throws InterruptedException;

	Book getByIsbn(String isbn) throws InterruptedException;

	Book getByIsbn(String isbn, boolean checkWarehouse, boolean includeUsed) throws InterruptedException;

	Book get(UUID id) throws InterruptedException;

	Book get(UUID id, boolean checkWarehouse, boolean includeUsed) throws InterruptedException;
}