package ru.ivanov.todoproject.repository;

import ru.ivanov.todoproject.api.ITaskRepository;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.validator.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Override
    public Task findTaskByName(final String userId, final String name) {
        if (!Validator.isArgumentsValid(userId, name)) return null;
        for (final Task task : entities.values()) {
            final String taskName = task.getName();
            final String taskUserId = task.getUserId();
            if (taskUserId.equals(userId) && taskName.equals(name)) {
                return task;
            }
        }
        return null;
    }

    @Override
    public Task findTaskByName(final String userId, final String projectId, final String taskName) {
        if (!Validator.isArgumentsValid(userId, projectId, taskName)) return null;
        for (final Task task : entities.values()) {
            final String persistentTaskName = task.getName();
            final String persistentTaskUserId = task.getUserId();
            final String persistentTaskProjectId = task.getProjectId();
            if (persistentTaskUserId.equals(userId) &&
                    persistentTaskName.equals(taskName) &&
                    persistentTaskProjectId.equals(projectId)) {
                return task;
            }
        }
        return null;
    }

    @Override
    public Task findTaskById(final String userId, final String taskId) {
        if (!Validator.isArgumentsValid(userId, taskId)) return null;
        final Task task = super.findById(taskId);
        if (task == null) return null;
        if (!userId.equals(task.getUserId())) return null;
        return task;
    }

    @Override
    public List<Task> findAllTask(final String userId) {
        if (!Validator.isArgumentsValid(userId)) return Collections.emptyList();
        final List<Task> tasks = findAll();
        return filterTasksByUserId(tasks, userId);
    }

    @Override
    public List<Task> findAllTask() {
        return findAll();
    }


    @Override
    public List<Task> findAllProjectTask(final String userId, final String projectId) {
        if (!Validator.isArgumentsValid(userId, projectId)) return Collections.emptyList();
        final List<Task> userTasks = findAllTask(userId);
        return filterTasksByProjectId(userTasks, projectId);
    }

    private List<Task> filterTasksByUserId(final List<Task> tasks, final String userId) {
        if (tasks == null || tasks.isEmpty()) return Collections.emptyList();
        if (!Validator.isArgumentsValid(userId)) return Collections.emptyList();
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
        if (!Validator.isArgumentsValid(projectId)) return Collections.emptyList();
        final List<Task> result = new ArrayList<>();
        for (final Task task : tasks) {
            if (task.getProjectId().equals(projectId)) {
                result.add(task);
            }
        }
        return result;
    }
}
