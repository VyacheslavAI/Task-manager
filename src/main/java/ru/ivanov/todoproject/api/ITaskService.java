package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;

import java.util.List;

public interface ITaskService {

    Task createOrUpdateTask(final Task task);

    Task loadById(final String id);

    void addAllTask(List<Task> tasks);

    List<Task> loadTasksByName(final String name);

    List<Task> loadTasksByProject(final Project project);

    List<Task> loadAllTask();

    Task deleteTask(final Task task);

    void deleteAllTask();
}

