package com.learn.spel;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
class DemoModel {
	private List<Boolean> booleanList = new ArrayList<>();
	private List<String> stringList = new ArrayList<>();
}