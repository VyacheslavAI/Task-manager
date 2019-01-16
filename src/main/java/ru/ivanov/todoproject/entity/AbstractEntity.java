package ru.ivanov.todoproject.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public abstract class AbstractEntity implements Serializable {

    private final String id = UUID.randomUUID().toString();

    private String userId;

    private String name = "no name";

    private Date created = new Date();

    public String getId() {
        return id;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
