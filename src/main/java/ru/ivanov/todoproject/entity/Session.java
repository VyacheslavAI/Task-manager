package ru.ivanov.todoproject.entity;

import java.io.Serializable;

public class Session extends AbstractEntity implements Serializable {

    private long timestamp;

    private String userId;

    private String signature;

    public Session() {
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
