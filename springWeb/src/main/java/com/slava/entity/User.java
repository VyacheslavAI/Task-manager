package com.slava.entity;

public class User {

    private String name;

    private int age;

    public User() {
    }

    public User(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
