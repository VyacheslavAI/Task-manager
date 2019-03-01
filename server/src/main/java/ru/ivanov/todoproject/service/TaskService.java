package ru.ivanov.todoproject.service;

import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.todoproject.api.ITaskService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.repository.ITaskRepository;
import ru.ivanov.todoproject.util.Validator;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Transactional
public class TaskService implements ITaskService {

    @Inject
    private ITaskRepository taskRepository;

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private Validator validator;

    @Override
    public Task createTask(final String userId, final String projectId, final Task task) throws ObjectIsNotValidException, InvalidArgumentException, ObjectNotFoundException {
        if (!validator.isTaskValid(task)) throw new ObjectIsNotValidException();
        if (!Validator.isArgumentsValid(userId)) throw new InvalidArgumentException();
        final Project project = serviceLocator.getProjectService().findProjectById(userId, projectId);
        task.setUserId(userId);
        task.setProject(project);
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(final Task task) throws ObjectIsNotValidException {
        if (!validator.isTaskValid(task)) throw new ObjectIsNotValidException();
        final Task persistentTask = taskRepository.getOne(task.getId());
        persistentTask.setName(task.getName());
        persistentTask.setUserId(task.getUserId());
        persistentTask.setCreated(task.getCreated());
        return taskRepository.save(persistentTask);
    }

    @Override
    public boolean addAllTask(final List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) return false;
        tasks.removeAll(Collections.singleton(null));
        taskRepository.saveAll(tasks);
        return true;
    }

    @Override
    public Task findTaskById(final String taskId) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(taskId)) throw new InvalidArgumentException();
        if (!taskRepository.existsById(taskId)) throw new ObjectNotFoundException();
        return taskRepository.findTaskById(taskId);
    }

    @Override
    public Task findTaskByName(final String userId, final String name) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(userId, name)) throw new InvalidArgumentException();
        final Task task = taskRepository.findTaskByUserIdAndName(userId, name);
        if (task == null) throw new ObjectNotFoundException();
        return task;
    }

    @Override
    public List<Task> findAllTaskByProject(final String userId, final String projectId) throws ObjectIsNotValidException, InvalidArgumentException {
        if (!Validator.isArgumentsValid(userId, projectId)) throw new InvalidArgumentException();
        return taskRepository.findAllByUserIdAndProjectId(userId, projectId);
    }

    @Override
    public Task findTaskByProject(final String userId, final String projectId, final String taskId) throws InvalidArgumentException, ObjectIsNotValidException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(userId, projectId, taskId)) throw new InvalidArgumentException();
        final Task task = taskRepository.findTaskByUserIdAndProjectIdAndId(userId, projectId, taskId);
        if (task == null) throw new ObjectNotFoundException();
        return task;
    }

    @Override
    public List<Task> findAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findAllUserTask(final String userId) throws InvalidArgumentException {
        if (!Validator.isArgumentsValid(userId)) throw new InvalidArgumentException();
        return taskRepository.findAllByUserId(userId);
    }

    @Override
    public boolean deleteTask(final String taskId) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(taskId)) throw new InvalidArgumentException();
        taskRepository.deleteById(taskId);
        return true;
    }

    @Override
    public boolean deleteAllTask() {
        taskRepository.deleteAllInBatch();
        return true;
    }
}