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

    public static List<Project> filterProjectByName(final List<Project> projects, final String name) {
        final List<Project> result = new ArrayList<>();
        for (final Project project : projects) {
            if (project.getName().equals(name)) {
                result.add(project);
            }
        }
        return result;
    }

    public static List<Task> filterTaskByName(final List<Task> projects, final String name) {
        final List<Task> result = new ArrayList<>();
        for (final Task project : projects) {
            if (project.getName().equals(name)) {
                result.add(project);
            }
        }
        return result;
    }
}
