package ru.ivanov.todoproject.userdata;

import ru.ivanov.todoproject.api.Session;

public class UserData {

    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public boolean isUserAuthorized() {
        return session != null;
    }
}
