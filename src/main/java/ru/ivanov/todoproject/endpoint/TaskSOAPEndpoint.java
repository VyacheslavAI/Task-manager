package ru.ivanov.todoproject.endpoint;

import ru.ivanov.todoproject.api.ITaskSOAPEndpoint;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.exception.AuthenticationException;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.security.SecurityServerManager;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.ivanov.todoproject.api.ITaskSOAPEndpoint")
public class TaskSOAPEndpoint implements ITaskSOAPEndpoint {

    private ServiceLocator serviceLocator;

    private SecurityServerManager securityManager;

    @Override
    public Task createTask(final Session session, final Task task) throws AuthenticationException, ObjectIsNotValidException, InvalidArgumentException {
        if (!securityManager.isSessionVerified(session)) throw new AuthenticationException();
        return serviceLocator.getTaskService().createTask(session.getUserId(), task);
    }

    @Override
    public List<Task> readTask(final Session session, final String name) throws AuthenticationException, InvalidArgumentException {
        if (!securityManager.isSessionVerified(session)) throw new AuthenticationException();
        return serviceLocator.getTaskService().loadUserTaskByName(session.getUserId(), name);
    }

    @Override
    public Task updateTask(final Session session, final Task task) throws AuthenticationException, ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException {
        if (!securityManager.isSessionVerified(session)) throw new AuthenticationException();
        return serviceLocator.getTaskService().updateTask(task);
    }

    @Override
    public Task deleteTask(final Session session, final Task task) throws AuthenticationException, ObjectIsNotValidException, ObjectNotFoundException {
        if (!securityManager.isSessionVerified(session)) throw new AuthenticationException();
        return serviceLocator.getTaskService().deleteTask(task);
    }

    @Override
    public List<Task> showTasks(final Session session) throws AuthenticationException, InvalidArgumentException {
        if (!securityManager.isSessionVerified(session)) throw new AuthenticationException();
        return serviceLocator.getTaskService().loadAllUserTask(session.getUserId());
    }

    @Override
    public List<Task> getTasksByProject(final Session session, final Project project) throws AuthenticationException, ObjectIsNotValidException, InvalidArgumentException {
        if (!securityManager.isSessionVerified(session)) throw new AuthenticationException();
        return serviceLocator.getTaskService().loadUserTaskByProject(session.getUserId(), project);
    }

    @Override
    public void setServiceLocator(final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public void setSecurityManager(final SecurityServerManager securityManager) {
        this.securityManager = securityManager;
    }
}