package com.learn.caching;

import java.util.UUID;

import com.learn.caching.domain.BookRepository;
import com.learn.caching.domain.Isbn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Profile("caching")
@Component
public class GetBooksRunner implements CommandLineRunner {
	private BookRepository bookRepository;

	public GetBooksRunner(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(".... Fetching books");
		findBook("isbn-1234");
		findBook("isbn-4567");
		findBook("isbn-1234");
		findBook("isbn-4567");
		findBook("isbn-1234");
		findBook("isbn-1234");

		findBook("isbn-1234", false, true);
		findBook("isbn-1234", true, false);
		findBook("isbn-7890", true, false);
		findBook("isbn-7890", false, false);

		findBook(UUID.fromString("ca402bbe-2dca-450e-9a85-c10341b273ca"));
		findBook(UUID.fromString("90fa3d59-0d81-43eb-825f-d13ca9c70356"));
		findBook(UUID.fromString("ca402bbe-2dca-450e-9a85-c10341b273ca"));
		findBook(UUID.fromString("90fa3d59-0d81-43eb-825f-d13ca9c70356"));

		findBook(UUID.fromString("ca402bbe-2dca-450e-9a85-c10341b273ca"), false, true);
		findBook(UUID.fromString("ca402bbe-2dca-450e-9a85-c10341b273ca"), true, false);
		findBook(UUID.fromString("ca402bbe-2dca-450e-9a85-c10341b273ca"), false, true);
		findBook(UUID.fromString("90fa3d59-0d81-43eb-825f-d13ca9c70356"), true, false);
		findBook(UUID.fromString("90fa3d59-0d81-43eb-825f-d13ca9c70356"), false, false);

		log.info(".... Cache Evict");
		loadBook("isbn-1234");
		loadBook("isbn-4567");
		loadBook("isbn-7890");

		log.info(".... Fetching books");
		findBook("isbn-1234");
		findBook("isbn-4567");
		findBook("isbn-1234");
		findBook("isbn-4567");
		findBook("isbn-1234");
		findBook("isbn-1234");

		findBook("isbn-1234", false, true);
		findBook("isbn-1234", true, false);
		findBook("isbn-7890", true, false);
		findBook("isbn-7890", false, false);

		findBook(UUID.fromString("ca402bbe-2dca-450e-9a85-c10341b273ca"));
		findBook(UUID.fromString("90fa3d59-0d81-43eb-825f-d13ca9c70356"));
		findBook(UUID.fromString("ca402bbe-2dca-450e-9a85-c10341b273ca"));
		findBook(UUID.fromString("90fa3d59-0d81-43eb-825f-d13ca9c70356"));

		findBook(UUID.fromString("ca402bbe-2dca-450e-9a85-c10341b273ca"), false, true);
		findBook(UUID.fromString("ca402bbe-2dca-450e-9a85-c10341b273ca"), true, false);
		findBook(UUID.fromString("90fa3d59-0d81-43eb-825f-d13ca9c70356"), true, false);
		findBook(UUID.fromString("90fa3d59-0d81-43eb-825f-d13ca9c70356"), false, false);

		log.info(".... Fetching books");
		findBook("isbn-1234");
		findBook("isbn-4567");
		findBook("isbn-1234");
		findBook("isbn-4567");
		findBook("isbn-1234");
		findBook("isbn-1234");

		findBook("isbn-1234", false, true);
		findBook("isbn-1234", true, false);
		findBook("isbn-7890", true, false);
		findBook("isbn-7890", false, false);

		log.info(".... Cache Evict");
		loadBooks();

		log.info(".... Fetching books");
		findBook("isbn-1234");
		findBook("isbn-4567");
		findBook("isbn-1234");
		findBook("isbn-4567");
		findBook("isbn-1234");
		findBook("isbn-1234");

		findBook("isbn-1234", false, true);
		findBook("isbn-1234", true, false);
		findBook("isbn-7890", true, false);
		findBook("isbn-7890", false, false);
	}

	private void findBook(String isbn, boolean checkWarehouse, boolean includeUsed) throws InterruptedException {
		var startTime = System.currentTimeMillis();
		log.info("Find Book {} --> {}", isbn, bookRepository.getByIsbn(isbn, checkWarehouse, includeUsed));
		var stopTime = System.currentTimeMillis();
		log.info("Elapsed time was {} milliseconds", stopTime - startTime);
	}

	private void findBook(String isbn) throws InterruptedException {
		var startTime = System.currentTimeMillis();
		var isbnFilter = new Isbn();
		isbnFilter.setCode(isbn);
		log.info("Find Book {} --> {}", isbn, bookRepository.getByIsbn(isbnFilter));
		var stopTime = System.currentTimeMillis();
		log.info("Elapsed time was {} milliseconds", stopTime - startTime);
	}

	private void findBook(UUID id, boolean checkWarehouse, boolean includeUsed) throws InterruptedException {
		var startTime = System.currentTimeMillis();
		log.info("Find Book {} --> {}", id, bookRepository.get(id, checkWarehouse, includeUsed));
		var stopTime = System.currentTimeMillis();
		log.info("Elapsed time was {} milliseconds", stopTime - startTime);
	}

	private void findBook(UUID id) throws InterruptedException {
		var startTime = System.currentTimeMillis();
		log.info("Find Book {} --> {}", id, bookRepository.get(id));
		var stopTime = System.currentTimeMillis();
		log.info("Elapsed time was {} milliseconds", stopTime - startTime);
	}

	private void loadBook(String isbn) throws InterruptedException {
		var startTime = System.currentTimeMillis();
		log.info("Load Book {} --> {}", isbn, bookRepository.loadByIsbn(isbn));
		var stopTime = System.currentTimeMillis();
		log.info("Elapsed time was {} milliseconds", stopTime - startTime);
	}

	private void loadBooks() throws InterruptedException {
		var startTime = System.currentTimeMillis();
		log.info("Load Books: ");
		bookRepository.load().stream().forEach(b -> log.info("{}", b));
		var stopTime = System.currentTimeMillis();
		log.info("Elapsed time was {} milliseconds", stopTime - startTime);
	}
}
