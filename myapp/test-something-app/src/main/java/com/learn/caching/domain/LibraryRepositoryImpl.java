package com.learn.caching.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LibraryRepositoryImpl implements LibraryRepository {

	@Override
	@CacheEvict("libraries")
	public Library loadByName(String name) throws InterruptedException {
		simulateSlowService();
		return new Library(name, "123 Street");
	}

	@Override
	@CacheEvict(cacheNames = "libraries", allEntries = true)
	public List<Library> load() throws InterruptedException {
		simulateSlowService();
		var libraries = new ArrayList<Library>();
		libraries.add(new Library("Liberty Library", "123 street"));
		libraries.add(new Library("Social Library", "234/2 street"));
		libraries.add(new Library("Urban Library", "3B street"));
		return libraries;
	}

	@Override
	@Cacheable("libraries")
	public Library getByName(String name) throws InterruptedException {
		simulateSlowService();
		return new Library(name, "123 Street");
	}

	@Override
	@Cacheable(cacheNames = "libraries", key = "#name")
	public Library find(String name, boolean dailyAvailable, boolean weeklyAvailable) throws InterruptedException {
		simulateSlowService();
		return new Library(name, "123 Street");
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
