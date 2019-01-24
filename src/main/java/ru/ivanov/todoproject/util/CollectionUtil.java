package ru.ivanov.todoproject.util;

import ru.ivanov.todoproject.api.IPredicate;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionUtil {

    public static <T> List<T> filter(final Collection<T> target, final IPredicate<T> predicate) {
        final List<T> result = new ArrayList<>();
        for (T type : target) {
            if (predicate.apply(type)) {
                result.add(type);
            }
        }
        return result;
    }

    public static List<Project> filterProjectsByUserId(final List<Project> projects, final String userId) {
        final List<Project> result = new ArrayList<>();
        for (Project project : projects) {
            if (project.getUserId().equals(userId)) {
                result.add(project);
            }
        }
        return result;
    }

    public static List<Task> filterTasksByUserId(final List<Task> tasks, final String userId) {
        final List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getUserId().equals(userId)) {
                result.add(task);
            }
        }
        return result;
    }
}
