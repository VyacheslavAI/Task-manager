package ru.ivanov.todoproject.endpoint;

import ru.ivanov.todoproject.api.ITaskSOAPEndpoint;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.RequestNotAuthenticatedException;

import javax.jws.WebService;
import java.util.List;

import static ru.ivanov.todoproject.util.CollectionUtil.filterTasksByUserId;
import static ru.ivanov.todoproject.util.HashUtil.isSessionVerified;

@WebService(endpointInterface = "ru.ivanov.todoproject.api.ITaskSOAPEndpoint")
public class TaskSOAPEndpoint implements ITaskSOAPEndpoint {

    private ServiceLocator serviceLocator;

    @Override
    public Task createTask(final Session session, final Task task) throws RequestNotAuthenticatedException, ObjectIsNotValidException {
        if (!isSessionVerified(session)) throw new RequestNotAuthenticatedException();
        task.setUserId(session.getUserId());
        return serviceLocator.getTaskService().createOrUpdateTask(task);
    }

    @Override
    public List<Task> readTask(final Session session, final String name) throws RequestNotAuthenticatedException {
        if (!isSessionVerified(session)) throw new RequestNotAuthenticatedException();
        List<Task> tasks = serviceLocator.getTaskService().loadAllTaskByName(name);
        return filterTasksByUserId(tasks, session.getUserId());
    }

    @Override
    public Task updateTask(final Session session, final Task task) throws RequestNotAuthenticatedException, ObjectIsNotValidException {
        if (!isSessionVerified(session)) throw new RequestNotAuthenticatedException();
        return serviceLocator.getTaskService().createOrUpdateTask(task);
    }

    @Override
    public Task deleteTask(final Session session, final Task task) throws RequestNotAuthenticatedException, ObjectIsNotValidException {
        if (!isSessionVerified(session)) throw new RequestNotAuthenticatedException();
        return serviceLocator.getTaskService().deleteTask(task);
    }

    @Override
    public List<Task> showTasks(final Session session) throws RequestNotAuthenticatedException {
        if (!isSessionVerified(session)) throw new RequestNotAuthenticatedException();
        final List<Task> allTasks = serviceLocator.getTaskService().loadAllTask();
        return filterTasksByUserId(allTasks, session.getUserId());
    }

    @Override
    public List<Task> getTasksByProject(final Session session, final Project project) throws RequestNotAuthenticatedException, ObjectIsNotValidException {
        if (!isSessionVerified(session)) throw new RequestNotAuthenticatedException();
        final List<Task> projectTasks = serviceLocator.getTaskService().loadAllTaskByProject(project);
        return filterTasksByUserId(projectTasks, session.getUserId());
    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }
}