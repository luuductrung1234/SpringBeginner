package com.learn.caching.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Library implements Serializable {
	private String name;
	private String address;

	public Library(String name, String address) {
		this.name = name;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Library{" + "name='" + name + '\'' + ", address='" + address + '\'' + '}';
	}
}
