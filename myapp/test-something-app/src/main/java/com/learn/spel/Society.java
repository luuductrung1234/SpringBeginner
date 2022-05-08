package com.learn.spel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Society {
	private String name;

	public static String Advisors = "advisors";
	public static String President = "president";

	private List<Inventor> members = new ArrayList<Inventor>();
	private Map<String, Inventor> officers = new HashMap<>();

	public Society() {
	}

	public Society(String name, List<Inventor> members, Map<String, Inventor> officers) {
		this.name = name;
		this.members = members;
		this.officers = officers;
	}

	public boolean isMember(String name) {
		for (Inventor inventor : members) {
			if (inventor.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
}
