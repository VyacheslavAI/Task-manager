package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;

import java.util.List;

public interface ITaskService {
    Task createOrUpdateTask(Task task) throws ObjectIsNotValidException;

    boolean addAllTask(List<Task> tasks);

    List<Task> loadAllTaskByUser(User user) throws ObjectIsNotValidException;

    Task loadTaskById(String id) throws InvalidArgumentException;

    List<Task> loadAllTaskByName(String name);

    List<Task> loadAllTaskByProject(Project project) throws ObjectIsNotValidException;

    List<Task> loadAllTask();

    Task deleteTask(Task task) throws ObjectIsNotValidException;

    boolean deleteAllTask();

    ServiceLocator getServiceLocator();

    void setServiceLocator(ServiceLocator serviceLocator);
}
