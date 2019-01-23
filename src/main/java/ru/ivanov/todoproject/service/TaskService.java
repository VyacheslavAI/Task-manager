package ru.ivanov.todoproject.service;

import ru.ivanov.todoproject.api.ITaskRepository;
import ru.ivanov.todoproject.api.ITaskService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.repository.TaskRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.ivanov.todoproject.util.ValidationUtil.isProjectValid;
import static ru.ivanov.todoproject.util.ValidationUtil.isTaskValid;
import static ru.ivanov.todoproject.util.ValidationUtil.isUserValid;

public class TaskService implements ITaskService {

    private final ITaskRepository taskRepository = new TaskRepository();

    private ServiceLocator serviceLocator;

    @Override
    public Task createOrUpdateTask(final Task task) throws ObjectIsNotValidException {
        if (!isTaskValid(task)) throw new ObjectIsNotValidException(task);
        return taskRepository.merge(task);
    }

    @Override
    public boolean addAllTask(final List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) return false;
        tasks.removeAll(Collections.singleton(null));
        return taskRepository.addAll(tasks);
    }

    @Override
    public List<Task> loadAllTaskByUser(final User user) throws ObjectIsNotValidException {
        if (!isUserValid(user)) throw new ObjectIsNotValidException(user);
        final List<Task> tasks = loadAllTask();
        final List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getUserId().equals(user.getId())) {
                result.add(task);
            }
        }
        return result;
    }

    @Override
    public Task loadTaskById(final String id) throws IllegalArgumentException {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException();
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> loadAllTaskByName(final String name) {
        if (name == null || name.isEmpty()) return Collections.emptyList();
        return taskRepository.findByName(name);
    }

    @Override
    public List<Task> loadAllTaskByProject(final Project project) throws ObjectIsNotValidException {
        if (!isProjectValid(project)) throw new ObjectIsNotValidException(project);
        final List<Task> tasks = taskRepository.findAll();
        final List<Task> result = new ArrayList<>();
        for (final Task task : tasks) {
            if (project.getId().equals(task.getProjectId())) {
                result.add(task);
            }
        }
        return result;
    }

    @Override
    public List<Task> loadAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public Task deleteTask(final Task task) throws ObjectIsNotValidException {
        if (!isTaskValid(task)) throw new ObjectIsNotValidException(task);
        return taskRepository.delete(task);
    }

    @Override
    public boolean deleteAllTask() {
        return taskRepository.deleteAll();
    }

    @Override
    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    @Override
    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }
}
