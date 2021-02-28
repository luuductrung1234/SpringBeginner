package com.learn.simpleconsoleapp.models;

public class Person {
    private String name;
    private String identityNumber;
    private int age;
    private Address address;

    public Person(String name, String identityNumber, int age, Address address) {
        this.name = name;
        this.identityNumber = identityNumber;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public Address getAddress() {
        return address;
    }
}
