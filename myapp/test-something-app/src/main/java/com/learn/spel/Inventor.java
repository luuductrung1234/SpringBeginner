package com.learn.spel;

import java.util.Date;
import java.util.GregorianCalendar;

import lombok.Data;

@Data
public class Inventor {
	private String name;
	private String nationality;
	private String[] inventions;
	private Date birthdate;
	private PlaceOfBirth placeOfBirth;

	public Inventor(String name, String nationality) {
		GregorianCalendar c = new GregorianCalendar();
		this.name = name;
		this.nationality = nationality;
		this.birthdate = c.getTime();
	}

	public Inventor(String name, Date birthdate, String nationality) {
		this.name = name;
		this.nationality = nationality;
		this.birthdate = birthdate;
	}

	public Inventor(String name, Date birthdate, String nationality, String[] inventions) {
		this.name = name;
		this.nationality = nationality;
		this.birthdate = birthdate;
		this.inventions = inventions;
	}

	public Inventor() {
	}
}