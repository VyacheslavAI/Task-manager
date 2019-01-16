package ru.ivanov.todoproject.entity;

public class User extends AbstractEntity {

    private String password = "";

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
