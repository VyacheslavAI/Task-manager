package ru.ivanov.todoproject.endpoint;

import ru.ivanov.todoproject.api.ITaskSOAPEndpoint;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.Task;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.ivanov.todoproject.api.ITaskSOAPEndpoint")
public class TaskSOAPEndpoint implements ITaskSOAPEndpoint {

    private ServiceLocator serviceLocator;

    @Override
    public Task createTask(final Session session, final Task task) {
        task.setUserId(session.getUserId());
        return serviceLocator.getTaskService().createOrUpdateTask(task);
    }

    @Override
    public List<Task> readTask(final Session session, final String name) {
        List<Task> taskList = serviceLocator.getTaskService().loadTasksByName(name);
    }

    @Override
    public Task updateTask(final Session session, final Task task) {
        return serviceLocator.getTaskService().createOrUpdateTask(task);
    }

    @Override
    public Task deleteTask(final Session session, final Task task) {
        return serviceLocator.getTaskService().deleteTask(task);
    }

    @Override
    public List<Task> showTasks(final Session session) {
        return serviceLocator.getTaskService().loadAllTask();
    }

    @Override
    public List<Task> getTasksByProject(final Session session, final Project project) {
        return serviceLocator.getTaskService().loadTasksByProject(project);
    }
}
