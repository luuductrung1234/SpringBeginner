package com.learn.simpleconsoleapp.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Component("injectCollection")
public class CollectionInjection {
    private Map<String, Object> map;
    private Properties props;
    private Set set;
    private List list;

    public CollectionInjection(
            @Qualifier("map") Map<String, Object> map,
            @Qualifier("props") Properties props,
            @Qualifier("set") Set set,
            @Qualifier("list") List list) {
        this.map = map;
        this.props = props;
        this.set = set;
        this.list = list;
    }

    public void displayInfo() {
        System.out.println("Map contents:\n");
        map.entrySet().stream().forEach(e -> System.out.println(
                "Key: " + e.getKey() + " - Value: " + e.getValue()));
        System.out.println("\nProperties contents:\n");
        props.entrySet().stream().forEach(e -> System.out.println(
                "Key: " + e.getKey() + " - Value: " + e.getValue()));
        System.out.println("\nSet contents:\n");
        set.forEach(obj -> System.out.println("Value: " + obj));
        System.out.println("\nList contents:\n");
        list.forEach(obj -> System.out.println("Value: " + obj));
    }
}
