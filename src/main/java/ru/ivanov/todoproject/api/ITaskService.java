package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.validator.Validator;

import java.util.List;

public interface ITaskService {

    Task createTask(String userId, Task task) throws ObjectIsNotValidException, InvalidArgumentException;

    Task updateTask(Task task) throws ObjectIsNotValidException, InvalidArgumentException, ObjectNotFoundException;

    boolean addAllTask(List<Task> tasks);

    Task loadTaskById(String userId, String id) throws InvalidArgumentException, ObjectNotFoundException;

    List<Task> loadUserTaskByName(String userId, String name) throws InvalidArgumentException;

    List<Task> loadUserTaskByProject(String userId, Project project) throws ObjectIsNotValidException, InvalidArgumentException;

    List<Task> loadAllUserTask(String userId) throws InvalidArgumentException;

    List<Task> loadAllTask();

    Task deleteTask(Task task) throws ObjectIsNotValidException, ObjectNotFoundException;

    boolean deleteAllTask();

    void setServiceLocator(ServiceLocator serviceLocator);

    void setValidator(Validator validator);
}
