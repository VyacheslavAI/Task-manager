package ru.ivanov.todoproject.dao;

import ru.ivanov.todoproject.api.ITaskDAO;
import ru.ivanov.todoproject.entity.Task;

import java.util.List;

public class TaskDAO implements ITaskDAO {

    private AllTasks allTasks = new AllTasks();

    @Override
    public Task createOrUpdateTask(Task task) {
        return allTasks.createOrUpdateTask(task);
    }

    @Override
    public Task deleteTask(Task task) {
        return allTasks.deleteTask(task);
    }

    @Override
    public List<Task> getTasksByName(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException();

        return allTasks.withName(name);
    }

    @Override
    public List<Task> getAllTasks() {
        return allTasks.getAllTasks();
    }

    @Override
    public void deleteAllTasks() {
        allTasks.clearAll();
    }

    @Override
    public void addAllTasks(List<Task> tasks) {
        allTasks.addAll(tasks);
    }
}
