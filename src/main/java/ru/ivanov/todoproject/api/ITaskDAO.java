package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Task;

import java.util.List;

public interface ITaskDAO {

    Task createOrUpdateTask(Task task);

    Task deleteTask(Task task);

    List<Task> getTasksByName(String name);

    List<Task> getAllTasks();

    void deleteAllTasks();

    void addAllTasks(List<Task> tasks);
}
