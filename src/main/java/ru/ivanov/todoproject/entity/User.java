package ru.ivanov.todoproject.entity;

import java.io.Serializable;

public class User extends AbstractEntity {

    private String login;

    private String password = "";

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
