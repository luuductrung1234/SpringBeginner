package com.learn.spel;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
public class User {
	@Value("#{ systemProperties['user.region'] }")
	private String defaultLocale;
}
