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

import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.ivanov.todoproject.api.ITaskSOAPEndpoint")
public class TaskSOAPEndpoint implements ITaskSOAPEndpoint {

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private SecurityServerManager securityManager;

    @Override
    public Task createTask(final Session session, final Task task) throws AuthenticationException, ObjectIsNotValidException, InvalidArgumentException {
        if (!securityManager.isSessionVerified(session)) throw new AuthenticationException();
        return serviceLocator.getTaskService().createTask(session.getUserId(), task);
    }

    @Override
    public Task readTask(final Session session, final Project project, final String taskName) throws AuthenticationException, ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException {
        if (!securityManager.isSessionVerified(session)) throw new AuthenticationException();
        return serviceLocator.getTaskService().findTaskByProject(session.getUserId(), project, taskName);
    }

    @Override
    public Task updateTask(final Session session, final Task task) throws AuthenticationException, ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException {
        if (!securityManager.isSessionVerified(session)) throw new AuthenticationException();
        return serviceLocator.getTaskService().updateTask(task);
    }

    @Override
    public boolean deleteTask(final Session session, final String projectId, final String taskName) throws AuthenticationException, ObjectNotFoundException, InvalidArgumentException {
        if (!securityManager.isSessionVerified(session)) throw new AuthenticationException();
        return serviceLocator.getTaskService().deleteTask(session.getUserId(), projectId, taskName);
    }

    @Override
    public List<Task> showTasks(final Session session) throws AuthenticationException, InvalidArgumentException {
        if (!securityManager.isSessionVerified(session)) throw new AuthenticationException();
        return serviceLocator.getTaskService().findAllUserTask(session.getUserId());
    }

    @Override
    public List<Task> getAllTaskByProject(final Session session, final Project project) throws AuthenticationException, ObjectIsNotValidException, InvalidArgumentException {
        if (!securityManager.isSessionVerified(session)) throw new AuthenticationException();
        return serviceLocator.getTaskService().findAllTaskByProject(session.getUserId(), project);
    }
}