package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.entity.User;

import java.util.List;

public interface ITaskService {
    Task createOrUpdateTask(Task task);

    void addAllTask(List<Task> tasks);

    List<Task> loadAllTaskByUser(User user);

    Task loadTaskById(String id);

    List<Task> loadAllTaskByName(String name);

    List<Task> loadAllTaskByProject(Project project);

    List<Task> loadAllTask();

    Task deleteTask(Task task);

    void deleteAllTask();

    ServiceLocator getServiceLocator();

    void setServiceLocator(ServiceLocator serviceLocator);
}
