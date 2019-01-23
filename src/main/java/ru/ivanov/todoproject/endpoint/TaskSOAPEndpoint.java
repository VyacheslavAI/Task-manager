package ru.ivanov.todoproject.endpoint;

import ru.ivanov.todoproject.api.ITaskSOAPEndpoint;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.entity.User;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "ru.ivanov.todoproject.api.ITaskSOAPEndpoint")
public class TaskSOAPEndpoint implements ITaskSOAPEndpoint {

    private ServiceLocator serviceLocator;

    @Override
    public Task createTask(final Session session, final Task task) {
        if (session == null || task == null) return null;
        final User user = serviceLocator.getUserService().getUserBySession(session);
        if (user == null || !task.getUserId().equals(user.getId())) return null;
        return serviceLocator.getTaskService().createOrUpdateTask(task);
    }

    @Override
    public List<Task> readTask(final Session session, final String name) {
        if (session == null || name == null || name.isEmpty()) return null;
        final User user = serviceLocator.getUserService().getUserBySession(session);
        if (user == null) return null;
        final List<Task> tasks = serviceLocator.getTaskService().loadAllTaskByUser(user);
        final List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().equals(name)) {
                result.add(task);
            }
        }
        return result;
    }

    @Override
    public Task updateTask(final Session session, final Task task) {
        if (session == null || task == null) return null;
        final User user = serviceLocator.getUserService().getUserBySession(session);
        if (user == null || !task.getUserId().equals(user.getId())) return null;
        return serviceLocator.getTaskService().createOrUpdateTask(task);
    }

    @Override
    public Task deleteTask(final Session session, final Task task) {
        if (session == null || task == null) return null;
        final User user = serviceLocator.getUserService().getUserBySession(session);
        if (user == null || !task.getUserId().equals(user.getId())) return null;
        return serviceLocator.getTaskService().deleteTask(task);
    }

    @Override
    public List<Task> showTasks(final Session session) {
        if (session == null) return null;
        final User user = serviceLocator.getUserService().getUserBySession(session);
        return serviceLocator.getTaskService().loadAllTaskByUser(user);
    }

    @Override
    public List<Task> getTasksByProject(final Session session, final Project project) {
        if (session == null || project == null) return null;
        final User user = serviceLocator.getUserService().getUserBySession(session);
        if (user == null || !project.getUserId().equals(user.getId())) return null;
        List<Task> tasks = serviceLocator.getTaskService().loadAllTaskByUser(user);
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getProjectId().equals(project.getId())) {
                result.add(task);
            }
        }
        return result;
    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }
}