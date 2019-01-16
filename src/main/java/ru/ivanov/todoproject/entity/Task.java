package ru.ivanov.todoproject.entity;

import java.io.Serializable;

public class Task extends AbstractEntity implements Serializable {

    private String projectId = "0";

    public Task() {
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {

        this.projectId = projectId;
    }
}
