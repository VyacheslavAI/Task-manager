package ru.ivanov.todoproject.repository;

import ru.ivanov.todoproject.api.ITaskRepository;
import ru.ivanov.todoproject.entity.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Override
    public Task findTaskByName(final String userId, final String name) {
        if (userId == null || userId.isEmpty()) return null;
        if (name == null || name.isEmpty()) return null;
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
    public Task findTaskById(final String userId, final String taskId) {
        if (userId == null || userId.isEmpty()) return null;
        if (taskId == null || taskId.isEmpty()) return null;
        final Task task = super.findById(taskId);
        if (task == null) return null;
        if (!userId.equals(task.getUserId())) return null;
        return task;
    }

    @Override
    public List<Task> findAllTask(final String userId) {
        if (userId == null || userId.isEmpty()) return Collections.emptyList();
        final List<Task> tasks = findAll();
        return filterTasksByUserId(tasks, userId);
    }

    @Override
    public List<Task> findAllTask() {
        return findAll();
    }

    @Override
    public List<Task> findAllProjectTask(final String userId, final String projectId) {
        if (userId == null || userId.isEmpty()) return Collections.emptyList();
        if (projectId == null || projectId.isEmpty()) return Collections.emptyList();
        final List<Task> userTasks = findAllTask(userId);
        return filterTasksByProjectId(userTasks, projectId);
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
