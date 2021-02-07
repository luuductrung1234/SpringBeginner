package com.learning.speldemo;

import com.learning.speldemo.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class AppExpressionParser {
    private static Logger logger = LoggerFactory.getLogger(AppExpressionParser.class);

    public static void simpleSpEL() {
        // Simple Expression

        var parser = new SpelExpressionParser();
        var exp1 = parser.parseExpression("'Hello World'");
        logger.info((String) exp1.getValue());

        var exp2 = parser.parseExpression("'Hello World'.length()");
        logger.info("{}", exp2.getValue());

        var exp3 = parser.parseExpression("'Hello World'.length()*10");
        logger.info("{}", exp3.getValue());

        var exp4 = parser.parseExpression("'Hello World'.length()>10");
        logger.info("{}", exp4.getValue());

        var exp5 = parser.parseExpression("'Hello World'.length()>10 and 'Hello World'.length()< 50");
        logger.info("{}", exp5.getValue());

        // Expression with Evaluation Context

        var ec1 = new StandardEvaluationContext();
        ec1.setVariable("greeting", "Hello USA");
        var message = parser.parseExpression("#greeting.substring(6)").getValue(ec1);
        logger.info("{}", message);

        var user = new User();
        var userContext = new StandardEvaluationContext(user);
        parser.parseExpression("country").setValue(userContext, "USA");
        parser.parseExpression("age").setValue(userContext, 20);
        parser.parseExpression("timeZone").setValue(userContext, "America/New_York");
        logger.info("{}", user.getCountry());
        logger.info("{}", user.getAge());
        logger.info("{}", user.getTimeZone());

        var propsContext = new StandardEvaluationContext();
        propsContext.setVariable("systemProperties", System.getProperties());
        var expCountry = parser.parseExpression("#systemProperties['user.country']");
        parser.parseExpression("country").setValue(userContext, expCountry.getValue(propsContext));
        logger.info("{}", user.getCountry());
    }
}
