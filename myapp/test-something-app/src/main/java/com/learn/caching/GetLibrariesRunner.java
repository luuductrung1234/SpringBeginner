package com.learn.caching;

import com.learn.caching.domain.LibraryRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Profile("caching")
@Component
public class GetLibrariesRunner implements CommandLineRunner {

	private LibraryRepository libraryRepository;

	public GetLibrariesRunner(LibraryRepository libraryRepository) {
		this.libraryRepository = libraryRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(".... Fetching libraries");
		getLibrary("isbn-1234");
		getLibrary("isbn-4567");
		getLibrary("isbn-1234");
		getLibrary("isbn-4567");
		getLibrary("isbn-1234");
		getLibrary("isbn-1234");

		findLibrary("isbn-1234", false, true);
		findLibrary("isbn-1234", true, false);
		findLibrary("isbn-7890", true, false);
		findLibrary("isbn-7890", false, false);

		log.info(".... Cache Evict");
		loadLibrary("isbn-1234");
		loadLibrary("isbn-4567");
		loadLibrary("isbn-7890");

		log.info(".... Fetching books");
		getLibrary("isbn-1234");
		getLibrary("isbn-4567");
		getLibrary("isbn-1234");
		getLibrary("isbn-4567");
		getLibrary("isbn-1234");
		getLibrary("isbn-1234");

		findLibrary("isbn-1234", false, true);
		findLibrary("isbn-1234", true, false);
		findLibrary("isbn-7890", true, false);
		findLibrary("isbn-7890", false, false);

		log.info(".... Fetching books");
		getLibrary("isbn-1234");
		getLibrary("isbn-4567");
		getLibrary("isbn-1234");
		getLibrary("isbn-4567");
		getLibrary("isbn-1234");
		getLibrary("isbn-1234");

		findLibrary("isbn-1234", false, true);
		findLibrary("isbn-1234", true, false);
		findLibrary("isbn-7890", true, false);
		findLibrary("isbn-7890", false, false);

		log.info(".... Cache Evict");
		loadLibraries();

		log.info(".... Fetching books");
		getLibrary("isbn-1234");
		getLibrary("isbn-4567");
		getLibrary("isbn-1234");
		getLibrary("isbn-4567");
		getLibrary("isbn-1234");
		getLibrary("isbn-1234");

		findLibrary("isbn-1234", false, true);
		findLibrary("isbn-1234", true, false);
		findLibrary("isbn-7890", true, false);
		findLibrary("isbn-7890", false, false);
	}

	private void findLibrary(String name, boolean dailyAvailable, boolean weeklyAvailable) throws InterruptedException {
		var startTime = System.currentTimeMillis();
		log.info("{} --> {}", name, libraryRepository.find(name, dailyAvailable, weeklyAvailable));
		var stopTime = System.currentTimeMillis();
		log.info("Elapsed time was {} milliseconds", stopTime - startTime);
	}

	private void getLibrary(String name) throws InterruptedException {
		var startTime = System.currentTimeMillis();
		log.info("{} --> {}", name, libraryRepository.getByName(name));
		var stopTime = System.currentTimeMillis();
		log.info("Elapsed time was {} milliseconds", stopTime - startTime);
	}

	private void loadLibrary(String name) throws InterruptedException {
		var startTime = System.currentTimeMillis();
		log.info("{} --> {}", name, libraryRepository.loadByName(name));
		var stopTime = System.currentTimeMillis();
		log.info("Elapsed time was {} milliseconds", stopTime - startTime);
	}

	private void loadLibraries() throws InterruptedException {
		var startTime = System.currentTimeMillis();
		libraryRepository.load().stream().forEach(b -> log.info("{}", b));
		var stopTime = System.currentTimeMillis();
		log.info("Elapsed time was {} milliseconds", stopTime - startTime);
	}
}
