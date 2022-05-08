package com.learn.caching.domain;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

@Data
public class Book implements Serializable {
	private UUID id;
	private String isbn;
	private String title;

	public Book(UUID id, String isbn, String title) {
		this.id = id;
		this.isbn = isbn;
		this.title = title;
	}

	public Book(String isbn, String title) {
		this.id = UUID.randomUUID();
		this.isbn = isbn;
		this.title = title;
	}

	@Override
	public String toString() {
		return "Book{" + "isbn='" + isbn + '\'' + ", title='" + title + '\'' + '}';
	}
}
