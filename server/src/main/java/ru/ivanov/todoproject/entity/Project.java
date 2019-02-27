package ru.ivanov.todoproject.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Project extends AbstractEntity {

    private String name = "no name";

    private String userId;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "project", fetch = FetchType.LAZY)
    private List<Task> tasks;

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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
