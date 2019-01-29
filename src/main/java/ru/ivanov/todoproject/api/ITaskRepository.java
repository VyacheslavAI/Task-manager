package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Task;

import java.util.List;

public interface ITaskRepository extends IRepository<Task> {

    Task findTaskByName(String userId, String name);

    Task findTaskByName(String userId, String projectName, String taskName);

    Task findTaskById(String userId, String taskName);

    List<Task> findAllTask(String userId);

    List<Task> findAllTask();

    List<Task> findAllProjectTask(String userId, String projectId);
}
