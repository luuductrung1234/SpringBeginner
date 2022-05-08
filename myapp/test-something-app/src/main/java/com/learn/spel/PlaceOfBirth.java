package com.learn.spel;

import lombok.Data;

@Data
public class PlaceOfBirth {
	private String city;
	private String country;

	public PlaceOfBirth(String city) {
		this.city = city;
	}

	public PlaceOfBirth(String city, String country) {
		this(city);
		this.country = country;
	}
}
