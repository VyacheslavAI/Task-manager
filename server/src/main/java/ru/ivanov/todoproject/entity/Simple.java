package ru.ivanov.todoproject.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Simple {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
