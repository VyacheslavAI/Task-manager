package ru.ivanov.todoproject.util;

import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.entity.User;

public class ValidationUtil {

    public static boolean isProjectValid(final Project project) {
        if (project == null) return false;
        if (project.getUserId() == null) return false;
        if (project.getName() == null) return false;
        return project.getCreated() != null;
    }

    public static boolean isTaskValid(final Task task) {
        if (task == null) return false;
        if (task.getUserId() == null) return false;
        if (task.getProjectId() == null) return false;
        if (task.getCreated() == null) return false;
        return task.getName() != null;
    }

    public static boolean isUserValid(final User user) {
        if (user == null) return false;
        if (user.getLogin() == null) return false;
        if (user.getPasswordHash() == null) return false;
        return user.getCreated() != null;
    }

    public static boolean isSessionValid(final Session session) {
        if (session == null) return false;
        if (session.getTimestamp() == 0) return false;
        if (session.getCreated() == null) return false;
        if (session.getUserId() == null) return false;
        return session.getSignature() != null;
    }
}
