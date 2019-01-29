package ru.ivanov.todoproject.service;

import ru.ivanov.todoproject.api.ITaskRepository;
import ru.ivanov.todoproject.api.ITaskService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.repository.TaskRepository;
import ru.ivanov.todoproject.validator.Validator;

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
    public Task updateTask(final String userId, final Task task) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException {
        if (!validator.isTaskValid(task)) throw new ObjectIsNotValidException();
        if (userId == null || userId.isEmpty()) throw new InvalidArgumentException();
        final Task persistentTask = taskRepository.findTaskById(userId, task.getId());
        if (persistentTask == null) throw new ObjectNotFoundException();
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
        final Task persistentTask = taskRepository.findTaskById(userId, taskId);
        if (persistentTask == null) throw new ObjectNotFoundException();
        return persistentTask;
    }

    @Override
    public Task loadUserTaskByName(final String userId, final String name) throws InvalidArgumentException, ObjectNotFoundException {
        if (name == null || name.isEmpty()) throw new InvalidArgumentException();
        if (userId == null || userId.isEmpty()) throw new InvalidArgumentException();
        final Task task = taskRepository.findTaskByName(userId, name);
        if (task == null) throw new ObjectNotFoundException();
        return task;
    }

    @Override
    public List<Task> loadUserTaskByProject(final String userId, final Project project) throws ObjectIsNotValidException, InvalidArgumentException {
        if (!validator.isProjectValid(project)) throw new ObjectIsNotValidException();
        if (userId == null || userId.isEmpty()) throw new InvalidArgumentException();
        return taskRepository.findAllProjectTask(userId, project.getId());
    }

    @Override
    public List<Task> loadAllTask() {
        return taskRepository.findAllTask();
    }

    @Override
    public List<Task> loadAllUserTask(final String userId) throws InvalidArgumentException {
        if (userId == null || userId.isEmpty()) throw new InvalidArgumentException();
        return taskRepository.findAllTask(userId);
    }

    @Override
    public Task deleteTask(final String userId, final String taskName) throws ObjectNotFoundException, InvalidArgumentException {
        if (userId == null || userId.isEmpty()) throw new InvalidArgumentException();
        if (taskName == null || taskName.isEmpty()) throw new InvalidArgumentException();
        final Task task = taskRepository.findTaskByName(userId, taskName);
        if (task == null) throw new ObjectNotFoundException();
        return taskRepository.delete(task);
    }

    @Override
    public boolean deleteAllTask() {
        return taskRepository.deleteAll();
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