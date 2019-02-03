package ru.ivanov.todoproject.entity;

import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity {

    private String login;

    private String passwordHash;

    public User() {
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
