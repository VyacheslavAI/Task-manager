package ru.ivanov.todoproject.entity;

import java.io.Serializable;
import java.util.Date;

public class Project implements Serializable {

    private String id = "0";

    private String name = "no name";

    private Date created = new Date();

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public Project(String id, String name, Date created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
