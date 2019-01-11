package ru.ivanov.todoproject.dao;

import ru.ivanov.todoproject.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    public List<Task> getTasksByName(final String name) {
        final List<Task> result = new ArrayList<>();
        for (final Task task : tasks) {
            if (task.getName().equals(name)) {
                result.add(task);
            }
        }
        return result;
    }

    public Task createOrUpdateTask(final Task task) {
        if (task.getId().equals("0"))
            return createNewTask(task);
        else
            return updateTask(task);
    }

    private Task createNewTask(final Task task) {
        final Task persistentTask = new Task();
        persistentTask.setId(IdRepository.generateId());
        persistentTask.setName(task.getName());
        persistentTask.setCreated(task.getCreated());
        tasks.add(persistentTask);
        return persistentTask;
    }

    private Task updateTask(final Task task) {
        for (final Task persistentTask : tasks) {
            if (persistentTask.getId().equals(task.getId())) {
                persistentTask.setName(task.getName());
                persistentTask.setCreated(task.getCreated());
                return task;
            }
        }
        return null;
    }

    public Task deleteTask(final Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            final Task persistentTask = tasks.get(i);
            if (persistentTask.getId().equals(task.getId())) {
                tasks.remove(persistentTask);
                break;
            }
        }
        return task;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public void addAllTasks(final List<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    public void deleteAllTasks() {
        tasks.removeAll(tasks);
    }
}
