package com.learn.simpleconsoleapp;

import com.learn.simpleconsoleapp.services.Oracle;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class XmlConfigWithBeanFactorApp {
    public static void main(String[] args) {
        var beanFactory = new DefaultListableBeanFactory();

        var beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        beanDefinitionReader.loadBeanDefinitions(
                new ClassPathResource("spring/xml-bean-factory-config.xml"));

        var oracle = beanFactory.getBean(Oracle.class);
        System.out.println(oracle.defineMeaningOfLife());
    }
}