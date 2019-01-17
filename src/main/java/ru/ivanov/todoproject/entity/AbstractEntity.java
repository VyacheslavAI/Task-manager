package ru.ivanov.todoproject.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public abstract class AbstractEntity implements Serializable {

    private final String id = UUID.randomUUID().toString();

    private Date created = new Date();

    public String getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
