package ru.ivanov.todoproject.util;

import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.entity.User;

import javax.inject.Singleton;

@Singleton
public class Validator {

    public boolean isProjectValid(final Project project) {
        if (project == null) return false;
        final String id = project.getId();
        final String name = project.getName();
        if (id == null || id.isEmpty()) return false;
        return name != null;
    }

    public boolean isTaskValid(final Task task) {
        if (task == null) return false;
        final String id = task.getId();
        final String name = task.getName();
        if (id == null || id.isEmpty()) return false;
        return name != null;
    }

    public boolean isUserValid(final User user) {
        if (user == null) return false;
        final String id = user.getId();
        final String login = user.getLogin();
        final String passwordHash = user.getPasswordHash();
        if (id == null || id.isEmpty()) return false;
        if (login == null || login.isEmpty()) return false;
        return passwordHash != null;
    }

    public boolean isSessionValid(final Session session) {
        if (session == null) return false;
        final String id = session.getId();
        final long timestamp = session.getTimestamp();
        final String userId = session.getUserId();
        final String signature = session.getSignature();
        if (id == null || id.isEmpty()) return false;
        if (timestamp <= 0) return false;
        if (userId == null) return false;
        return signature != null && !signature.isEmpty();
    }

    public static boolean isArgumentsValid(String...args) {
        for (String argument : args) {
            if (argument == null || argument.isEmpty())
                return false;
        }
        return true;
    }
}
