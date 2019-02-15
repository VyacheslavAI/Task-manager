package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import java.util.List;

public interface ITaskService {

    Task createTask(String userId, String projectId, Task task) throws ObjectIsNotValidException, InvalidArgumentException, ObjectNotFoundException;

    Task updateTask(Task task) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException;

    boolean addAllTask(List<Task> tasks);

    Task findTaskById(String taskId) throws InvalidArgumentException, ObjectNotFoundException;

    Task findTaskByName(String userId, String name) throws InvalidArgumentException, ObjectNotFoundException;

    List<Task> findAllUserTask(String userId) throws InvalidArgumentException;

    List<Task> findAllTaskByProject(String userId, String projectId) throws ObjectIsNotValidException, InvalidArgumentException;

    Task findTaskByProject(String userId, String projectId, String taskName) throws InvalidArgumentException, ObjectIsNotValidException, ObjectNotFoundException;

    List<Task> findAllTask();

    boolean deleteTask(String taskId) throws InvalidArgumentException, ObjectNotFoundException;

    boolean deleteAllTask();
}
