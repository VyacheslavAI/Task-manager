package ru.ivanov.todoproject.entity;

import java.io.Serializable;

public class Project extends AbstractEntity implements Serializable {

    private String name = "no name";

    private String userId;

    public Project() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
