package com.learn.spel;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.stereotype.Component;

@Profile("spel")
@Component
public class SpELRunner implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpELRunner.class);

	@Override
	public void run(String... args) throws Exception {
		spelLiteralExpressions();
		spelAgainstSpecificObject();
		spelTypeConversion();
		spelParserConfiguration();
		spelCompilerConfiguration();

		spelPropertiesArraysListsMapsIndexers();
		spelInlineListsMaps();
		spelRelationalOperators();
		spelLogicalOperators();
		spelTypes();
	}

	private void spelLiteralExpressions() {
		var parser = new SpelExpressionParser();

		var avogadrosNumber = parser.parseExpression("6.0221415E+23").getValue(Double.class);
		LOGGER.info("{}", avogadrosNumber);

		// evals to 2147483647
		var maxValue = parser.parseExpression("0x7FFFFFFF").getValue(Integer.class);
		LOGGER.info("{}", maxValue);

		var trueValue = parser.parseExpression("true").getValue(Boolean.class);
		LOGGER.info("{}", trueValue);

		var nullValue = parser.parseExpression("null").getValue();
		LOGGER.info("{}", nullValue);

		var message = parser.parseExpression("'Hello World!'").getValue(String.class);
		LOGGER.info(message);

		message = parser.parseExpression("'Hello World!'.concat('!')").getValue(String.class);
		LOGGER.info(message);

		var length = parser.parseExpression("'Hello World!'.bytes.length").getValue(Integer.class);
		LOGGER.info("{}", length);

		message = parser.parseExpression("new String('Hello World!').toUpperCase()").getValue(String.class);
		LOGGER.info(message);
	}

	private void spelAgainstSpecificObject() {
		// Create and set a calendar
		var c = new GregorianCalendar();
		c.set(1856, 7, 9);

		// The constructor arguments are name, birthday, and nationality.
		Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

		var parser = new SpelExpressionParser();

		// Parse name as an expression
		var name = parser.parseExpression("name").getValue(tesla, String.class);
		// name == "Nikola Tesla"
		LOGGER.info(name);

		var result = parser.parseExpression("name == 'Nikola Tesla'").getValue(tesla, Boolean.class);
		// result == true
		LOGGER.info("{}", result);
	}

	private void spelTypeConversion() {

		var demoModel = new DemoModel();
		demoModel.getBooleanList().add(true);

		var context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
		var parser = new SpelExpressionParser();

		// "false" is passed in here as a String. SpEL and the conversion service
		// will recognize that it needs to be a Boolean and convert it accordingly.
		parser.parseExpression("booleanList[0]").setValue(context, demoModel, "false");

		// b is false
		var b = demoModel.getBooleanList().get(0);
		LOGGER.info("{}", b);
	}

	private void spelParserConfiguration() {
		// Turn on:
		// - auto null reference initialization
		// - auto collection growing
		var config = new SpelParserConfiguration(true, true);

		var parser = new SpelExpressionParser(config);

		var demoModel = new DemoModel();

		Object o = parser.parseExpression("stringList[3]").getValue(demoModel);
		LOGGER.info("StringList initialized with {} entries and the 4th entry is {}",
				demoModel.getStringList().size(), o);

		// demo.list will now be a real collection of 4 entries
		// Each entry is a new empty String
	}

	private void spelCompilerConfiguration() {
		var config = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE,
				this.getClass().getClassLoader());

		var parser = new SpelExpressionParser(config);

		var c = new GregorianCalendar();
		c.set(1856, 7, 9);
		var tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

		var birthday = parser.parseExpression("birthday").getValue(tesla, Date.class);
		LOGGER.info("{}", birthday);
	}

	private void spelPropertiesArraysListsMapsIndexers() {
		var parser = new SpelExpressionParser();
		var context = SimpleEvaluationContext.forReadOnlyDataBinding().build();

		var c = new GregorianCalendar();
		c.set(1856, 7, 9);

		var inventions = new ArrayList<String>();
		inventions.add("Physics");
		inventions.add("Blah Blah");
		inventions.add("....");
		inventions.add("Induction motor");
		inventions.add("Electronic Force");
		inventions.add("Foundation of Quantum Theory");
		inventions.add("Wireless communication");
		var inventionArray = new String[inventions.size()];
		var tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian", inventions.toArray(inventionArray));

		var members = new ArrayList<Inventor>();
		members.add(tesla);
		var officers = new HashMap<String, Inventor>();
		officers.put(Society.Advisors, tesla);
		var ieee = new Society("IEEE", members, officers);

		var year = parser.parseExpression("birthdate.year + 1900").getValue(context, tesla, Integer.class);
		LOGGER.info("{}", year);

		var city = parser.parseExpression("placeOfBirth.city").getValue(context, tesla, String.class);
		LOGGER.info("{}", city);

		String invention = parser.parseExpression("inventions[3]").getValue(
				context, tesla, String.class);
		LOGGER.info("{}", invention);

		String name = parser.parseExpression("members[0].name").getValue(
				context, ieee, String.class);
		LOGGER.info("{}", name);

		invention = parser.parseExpression("members[0].inventions[6]").getValue(
				context, ieee, String.class);
		LOGGER.info("{}", invention);

		var advisor = parser.parseExpression("officers['advisors']").getValue(
				context, ieee, Inventor.class);
		LOGGER.info("{}", advisor);

		city = parser.parseExpression("officers['advisors'].placeOfBirth.city").getValue(
				context, ieee, String.class);
		LOGGER.info("{}", city);

		// setting values
		parser.parseExpression("officers['advisors'][0].placeOfBirth.country").setValue(
				context, ieee, "Croatia");

		var country = parser.parseExpression("officers['advisors'].placeOfBirth.country").getValue(
				context, ieee, String.class);
		LOGGER.info("{}", country);
	}

	private void spelInlineListsMaps() {
		var parser = new SpelExpressionParser();
		var context = SimpleEvaluationContext.forReadOnlyDataBinding().build();

		var numbers = parser.parseExpression("{1,2,3,4}").getValue(context, List.class);
		LOGGER.info("{}", numbers);

		var listOfLists = parser.parseExpression("{{'a','b'},{'x','y'}}").getValue(context, List.class);
		LOGGER.info("{}", listOfLists);

		var inventorInfo = parser.parseExpression("{name:'Nikola',dob:'10-July-1856'}").getValue(context, Map.class);
		LOGGER.info("{}", inventorInfo);

		var mapOfMaps = parser
				.parseExpression("{name:{first:'Nikola',last:'Tesla'},dob:{day:10,month:'July',year:1856}}")
				.getValue(context, Map.class);
		LOGGER.info("{}", mapOfMaps);
	}

	private void spelRelationalOperators() {
		var parser = new SpelExpressionParser();

		// evaluates to true
		var trueValue = parser.parseExpression("2 == 2").getValue(Boolean.class);
		LOGGER.info("{}", trueValue);

		// evaluates to false
		var falseValue = parser.parseExpression("2 < -5.0").getValue(Boolean.class);
		LOGGER.info("{}", falseValue);

		// evaluates to true
		trueValue = parser.parseExpression("'black' < 'block'").getValue(Boolean.class);
		LOGGER.info("{}", trueValue);

		// evaluates to false
		falseValue = parser.parseExpression(
				"'xyz' instanceof T(Integer)").getValue(Boolean.class);
		LOGGER.info("{}", falseValue);

		// evaluates to true
		trueValue = parser.parseExpression(
				"'5.00' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);
		LOGGER.info("{}", trueValue);

		// evaluates to false
		falseValue = parser.parseExpression(
				"'5.0067' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);
		LOGGER.info("{}", falseValue);
	}

	private void spelLogicalOperators() {
		var parser = new SpelExpressionParser();

		var c = new GregorianCalendar();
		c.set(1856, 7, 9);

		var inventions = new ArrayList<String>();
		inventions.add("Physics");
		inventions.add("Blah Blah");
		inventions.add("....");
		inventions.add("Induction motor");
		inventions.add("Electronic Force");
		inventions.add("Foundation of Quantum Theory");
		inventions.add("Wireless communication");
		var inventionArray = new String[inventions.size()];
		var tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian", inventions.toArray(inventionArray));

		var members = new ArrayList<Inventor>();
		members.add(tesla);
		var officers = new HashMap<String, Inventor>();
		officers.put(Society.Advisors, tesla);
		var ieee = new Society("IEEE", members, officers);

		// -- AND --

		// evaluates to false
		var falseValue = parser.parseExpression("true and false").getValue(Boolean.class);
		LOGGER.info("{}", falseValue);

		// evaluates to true
		String expression = "isMember('Nikola Tesla') and isMember('Mihajlo Pupin')";
		falseValue = parser.parseExpression(expression).getValue(ieee, Boolean.class);
		LOGGER.info("{}", falseValue);

		// -- OR --

		// evaluates to true
		var trueValue = parser.parseExpression("true or false").getValue(Boolean.class);
		LOGGER.info("{}", trueValue);

		// evaluates to true
		expression = "isMember('Nikola Tesla') or isMember('Albert Einstein')";
		trueValue = parser.parseExpression(expression).getValue(ieee, Boolean.class);
		LOGGER.info("{}", trueValue);

		// -- NOT --

		// evaluates to false
		falseValue = parser.parseExpression("!true").getValue(Boolean.class);
		LOGGER.info("{}", falseValue);

		// -- AND and NOT --
		expression = "isMember('Nikola Tesla') and !isMember('Mihajlo Pupin')";
		trueValue = parser.parseExpression(expression).getValue(ieee, Boolean.class);
		LOGGER.info("{}", trueValue);
	}

	private void spelTypes() {
		var parser = new SpelExpressionParser();

		var dateClass = parser.parseExpression("T(java.util.Date)").getValue(Class.class);
		LOGGER.info("{}", dateClass);

		var stringClass = parser.parseExpression("T(String)").getValue(Class.class);
		LOGGER.info("{}", stringClass);

		var trueValue = parser.parseExpression(
				"T(java.math.RoundingMode).CEILING < T(java.math.RoundingMode).FLOOR")
				.getValue(Boolean.class);
		LOGGER.info("{}", trueValue);
	}
}
