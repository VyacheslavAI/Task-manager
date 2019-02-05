package ru.ivanov.todoproject.api;

import org.hibernate.SessionFactory;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.validator.Validator;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

@XmlJavaTypeAdapter(ServiceLocator.TaskServiceAdapter.class)
public interface ITaskService {

    Task createTask(String userId, Task task) throws ObjectIsNotValidException, InvalidArgumentException;

    Task updateTask(String userId, Task task) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException;

    boolean addAllTask(List<Task> tasks);

    Task loadTaskById(String userId, String id) throws InvalidArgumentException, ObjectNotFoundException;

    Task loadUserTaskByName(String userId, String name) throws InvalidArgumentException, ObjectNotFoundException;

    List<Task> loadAllUserTaskByProject(String userId, Project project) throws ObjectIsNotValidException, InvalidArgumentException;

    List<Task> loadAllUserTask(String userId) throws InvalidArgumentException;

    Task loadTaskByProject(String userId, Project project, String taskName) throws InvalidArgumentException, ObjectIsNotValidException, ObjectNotFoundException;

    List<Task> loadAllTask();

    Task deleteTask(String userId, String projectId, String taskName) throws ObjectNotFoundException, InvalidArgumentException;

    boolean deleteAllTask();
}
