package ru.ivanov.todoproject.service;

import ru.ivanov.todoproject.api.ITaskRepository;
import ru.ivanov.todoproject.api.ITaskService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.repository.TaskRepository;
import ru.ivanov.todoproject.validator.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.ivanov.todoproject.util.ValidationUtil.*;

public class TaskService implements ITaskService {

    private final ITaskRepository taskRepository = new TaskRepository();

    private ServiceLocator serviceLocator;

    private Validator validator;

    @Override
    public Task createOrUpdateTask(final Task task) throws ObjectIsNotValidException {
        if (!validator.isTaskValid(task)) throw new ObjectIsNotValidException(task);
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
        if (!validator.isUserValid(user)) throw new ObjectIsNotValidException(user);
        final List<Task> allTask = loadAllTask();
        return filterTasksByUserId(allTask, user.getId());
    }

    @Override
    public Task loadTaskById(final String id) throws InvalidArgumentException {
        if (id == null || id.isEmpty()) throw new InvalidArgumentException();
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> loadAllTaskByName(final String name) {
        if (name == null || name.isEmpty()) return Collections.emptyList();
        return taskRepository.findByName(name);
    }

    @Override
    public List<Task> loadAllTaskByProject(final Project project) throws ObjectIsNotValidException {
        if (!validator.isProjectValid(project)) throw new ObjectIsNotValidException(project);
        final List<Task> tasks = taskRepository.findAll();
        return filterTasksByProjectId(tasks, project.getId());
    }

    @Override
    public List<Task> loadAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public Task deleteTask(final Task task) throws ObjectIsNotValidException {
        if (!validator.isTaskValid(task)) throw new ObjectIsNotValidException(task);
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
    public void setServiceLocator(final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    private List<Task> filterTasksByUserId(final List<Task> tasks, final String userId) {
        if (tasks == null || tasks.isEmpty()) return Collections.emptyList();
        if (userId == null || userId.isEmpty()) return Collections.emptyList();
        final List<Task> result = new ArrayList<>();
        for (final Task task : tasks) {
            if (task.getUserId().equals(userId)) {
                result.add(task);
            }
        }
        return result;
    }

    private List<Task> filterTasksByProjectId(final List<Task> tasks, final String projectId) {
        if (tasks == null || tasks.isEmpty()) return Collections.emptyList();
        if (projectId == null || projectId.isEmpty()) return Collections.emptyList();
        final List<Task> result = new ArrayList<>();
        for (final Task task : tasks) {
            if (task.getProjectId().equals(projectId)) {
                result.add(task);
            }
        }
        return result;
    }
}
