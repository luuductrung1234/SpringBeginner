package com.learn.simpleconsoleapp;

import com.learn.simpleconsoleapp.beans.CollectionInjection;
import com.learn.simpleconsoleapp.beans.Singer;
import com.learn.simpleconsoleapp.beans.SingerWithInterface;
import com.learn.simpleconsoleapp.beans.SingerWithJSR250;
import com.learn.simpleconsoleapp.configs.AppConfig;
import com.learn.simpleconsoleapp.services.MessageRenderer;
import org.springframework.beans.factory.BeanCreationException;
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

        var stringBeans = xmlContext.getBeansOfType(String.class);

        stringBeans.entrySet().stream().forEach(b -> System.out.println(b.getKey()));

        stringBeans.entrySet().stream().forEach(b ->
        {
            System.out.println("[id]: " + b.getKey()
                    + "\n\t[aliases]: "
                    + Arrays.toString(xmlContext.getAliases(b.getKey())) + "\n");
        });

        Arrays.stream(annotationContext.getBeanFactory().getBeanNamesForType(Singer.class)).forEach(beanName -> {
            try {
                var singer = annotationContext.getBean(beanName);

                System.out.println("[id]: " + beanName
                        + "\n\t[aliases]: "
                        + Arrays.toString(annotationContext.getAliases(beanName))
                        + "\n\t[details]: " + singer + "\n");
            } catch (BeanCreationException ex) {
                System.out.println("An error occurred in bean configuration: " + ex.getMessage() + "\n");
            }
        });

        Arrays.stream(annotationContext.getBeanFactory().getBeanNamesForType(SingerWithInterface.class)).forEach(beanName -> {
            try {
                var singer = annotationContext.getBean(beanName);

                System.out.println("[id]: " + beanName
                        + "\n\t[aliases]: "
                        + Arrays.toString(annotationContext.getAliases(beanName))
                        + "\n\t[details]: " + singer + "\n");
            } catch (BeanCreationException ex) {
                System.out.println("An error occurred in bean configuration: " + ex.getMessage() + "\n");
            }
        });

        Arrays.stream(annotationContext.getBeanFactory().getBeanNamesForType(SingerWithJSR250.class)).forEach(beanName -> {
            try {
                var singer = annotationContext.getBean(beanName);

                System.out.println("[id]: " + beanName
                        + "\n\t[aliases]: "
                        + Arrays.toString(annotationContext.getAliases(beanName))
                        + "\n\t[details]: " + singer + "\n");
            } catch (BeanCreationException ex) {
                System.out.println("An error occurred in bean configuration: " + ex.getMessage() + "\n");
            }
        });


        // -> When application reach this point, ApplicationContext will perform destroy() or shutdown() automatically
    }
}
