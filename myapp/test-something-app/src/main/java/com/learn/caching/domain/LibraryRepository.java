package com.learn.caching.domain;

import java.util.List;

public interface LibraryRepository {
	Library loadByName(String name) throws InterruptedException;

	List<Library> load() throws InterruptedException;

	Library getByName(String name) throws InterruptedException;

	Library find(String name, boolean dailyAvailable, boolean weeklyAvailable) throws InterruptedException;
}
