package ru.ivanov.todoproject.service;

import ru.ivanov.todoproject.api.ITaskRepository;
import ru.ivanov.todoproject.api.ITaskService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.repository.TaskRepository;
import ru.ivanov.todoproject.security.SecurityServerManager;
import ru.ivanov.todoproject.validator.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskService implements ITaskService {

    private final ITaskRepository taskRepository = new TaskRepository();

    private ServiceLocator serviceLocator;

    private Validator validator;

    @Override
    public Task createTask(final String userId, final Task task) throws ObjectIsNotValidException, InvalidArgumentException {
        if (!validator.isTaskValid(task)) throw new ObjectIsNotValidException();
        if (userId == null || userId.isEmpty()) throw new InvalidArgumentException();
        task.setUserId(userId);
        return taskRepository.merge(task);
    }

    @Override
    public Task updateTask(final Task task) throws ObjectIsNotValidException, ObjectNotFoundException {
        if (!validator.isTaskValid(task)) throw new ObjectIsNotValidException();
        final Task persistentTask = taskRepository.findById(task.getId());
        if (persistentTask == null ) throw new ObjectNotFoundException();
        if (!persistentTask.getUserId().equals(task.getUserId())) throw new ObjectNotFoundException();
        return taskRepository.merge(task);
    }

    @Override
    public boolean addAllTask(final List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) return false;
        tasks.removeAll(Collections.singleton(null));
        return taskRepository.addAll(tasks);
    }

    @Override
    public Task loadTaskById(final String userId, final String taskId) throws InvalidArgumentException, ObjectNotFoundException {
        if (taskId == null || taskId.isEmpty()) throw new InvalidArgumentException();
        if (userId == null || userId.isEmpty()) throw new InvalidArgumentException();
        final Task persistentTask = taskRepository.findById(taskId);
        if (persistentTask == null) throw new ObjectNotFoundException();
        if (!persistentTask.getUserId().equals(userId)) throw new ObjectNotFoundException();
        return persistentTask;
    }

    @Override
    public List<Task> loadUserTaskByName(final String userId, final String name) throws InvalidArgumentException {
        if (name == null || name.isEmpty()) return Collections.emptyList();
        if (userId == null || userId.isEmpty()) throw new InvalidArgumentException();
        final List<Task> tasks = taskRepository.findByName(name);
        return filterTasksByUserId(tasks, userId);
    }

    @Override
    public List<Task> loadUserTaskByProject(final String userId, final Project project) throws ObjectIsNotValidException, InvalidArgumentException {
        if (!validator.isProjectValid(project)) throw new ObjectIsNotValidException();
        if (userId == null || userId.isEmpty()) throw new InvalidArgumentException();
        final List<Task> tasks = taskRepository.findAll();
        List<Task> projectTasks = filterTasksByProjectId(tasks, project.getId());
        return filterTasksByUserId(projectTasks, userId);
    }

    @Override
    public List<Task> loadAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> loadAllUserTask(final String userId) throws InvalidArgumentException {
        if (userId == null || userId.isEmpty()) throw new InvalidArgumentException();
        final List<Task> allTask = taskRepository.findAll();
        return filterTasksByUserId(allTask, userId);
    }

    @Override
    public Task deleteTask(final Task task) throws ObjectIsNotValidException, ObjectNotFoundException {
        if (!validator.isTaskValid(task)) throw new ObjectIsNotValidException();
        final Task persistentTask = taskRepository.findById(task.getId());
        if (persistentTask == null) throw new ObjectNotFoundException();
        if (!persistentTask.getUserId().equals(task.getUserId())) throw new ObjectNotFoundException();
        return taskRepository.delete(task);
    }

    @Override
    public boolean deleteAllTask() {
        return taskRepository.deleteAll();
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

    @Override
    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}