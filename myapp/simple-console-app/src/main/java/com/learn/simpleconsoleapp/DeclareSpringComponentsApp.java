package com.learn.simpleconsoleapp;

import com.learn.simpleconsoleapp.beans.CollectionInjection;
import com.learn.simpleconsoleapp.beans.Singer;
import com.learn.simpleconsoleapp.configs.AppConfig;
import com.learn.simpleconsoleapp.services.MessageRenderer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

public class DeclareSpringComponentsApp {
    public static void main(String[] args) {
        var xmlContext = new GenericXmlApplicationContext();
        xmlContext.load("classpath:application-context.xml");
        xmlContext.refresh();

        var annotationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("\n--------------------------");
        System.out.println("Get Started with ApplicationContext");
        System.out.println("--------------------------\n");

        var messageRenderer = xmlContext.getBean(MessageRenderer.class);
        messageRenderer.render();

        System.out.println("\n--------------------------");
        System.out.println("Collection Injection");
        System.out.println("--------------------------\n");

        var collectionInjection = xmlContext.getBean(CollectionInjection.class);
        collectionInjection.displayInfo();

        System.out.println("\n--------------------------");
        System.out.println("Method Injection");
        System.out.println("--------------------------\n");

        // TODO: read later

        System.out.println("\n--------------------------");
        System.out.println("Understand Bean Naming");
        System.out.println("--------------------------\n");

        var stringbeans = xmlContext.getBeansOfType(String.class);

        stringbeans.entrySet().stream().forEach(b -> System.out.println(b.getKey()));

        stringbeans.entrySet().stream().forEach(b ->
        {
            System.out.println("id: " + b.getKey() +
                    "\n aliases: " + Arrays.toString(xmlContext.getAliases(b.getKey())) + "\n");
        });

        var singerBeans = annotationContext.getBeansOfType(Singer.class);

        singerBeans.entrySet().stream().forEach(b ->
                System.out.println("id: " + b.getKey()
                        + "\n aliases: "
                        + Arrays.toString(annotationContext.getAliases(b.getKey())) + "\n")
        );

        xmlContext.close();
    }
}
