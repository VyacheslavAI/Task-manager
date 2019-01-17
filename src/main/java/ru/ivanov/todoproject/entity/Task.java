package ru.ivanov.todoproject.entity;

import java.io.Serializable;

public class Task extends AbstractEntity implements Serializable {

    private String name = "no name";

    private String projectId = "0";

    private String userId;

    public Task() {
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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
