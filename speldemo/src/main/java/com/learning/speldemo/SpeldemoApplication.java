package com.learning.speldemo;

import com.learning.speldemo.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@SpringBootApplication
public class SpeldemoApplication {
    private static Logger logger = LoggerFactory.getLogger(SpeldemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpeldemoApplication.class, args) ;

        AppExpressionParser.simpleSpEL();
    }

}
