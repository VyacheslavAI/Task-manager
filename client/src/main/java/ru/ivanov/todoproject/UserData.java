package ru.ivanov.todoproject;

import ru.ivanov.todoproject.api.Session;

public class UserData {

    private Session session;

    public boolean isUserAuthorized() {
        return session != null;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
