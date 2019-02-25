package ru.ivanov.todoproject.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import ru.ivanov.todoproject.api.ITaskSOAPEndpoint;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import javax.jws.WebService;

@WebService(endpointInterface = "ru.ivanov.todoproject.api.ITaskSOAPEndpoint")
public class TaskSOAPEndpoint implements ITaskSOAPEndpoint {

    @Autowired
    private ServiceLocator serviceLocator;

    @Override
    public Task createTask(String userId, String projectId, String taskName) throws ObjectIsNotValidException, InvalidArgumentException, ObjectNotFoundException {
        final Task task = new Task();
        task.setName(taskName);
        return serviceLocator.getTaskService().createTask(userId, projectId, task);
    }

    @Override
    public Task updateTask(Task task) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException {
        return serviceLocator.getTaskService().updateTask(task);
    }

    @Override
    public Task findTaskById(String taskId) throws InvalidArgumentException, ObjectNotFoundException {
        return serviceLocator.getTaskService().findTaskById(taskId);
    }

    @Override
    public boolean deleteTask(String taskId) throws InvalidArgumentException, ObjectNotFoundException {
        return serviceLocator.getTaskService().deleteTask(taskId);
    }
}
