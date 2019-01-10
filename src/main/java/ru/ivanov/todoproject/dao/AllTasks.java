package ru.ivanov.todoproject.dao;

import ru.ivanov.todoproject.entity.Task;

import java.util.ArrayList;
import java.util.List;

class AllTasks {

    private List<Task> tasks = new ArrayList<>();

    public List<Task> withName(String name) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getName().equals(name)) {
                result.add(task);
            }
        }
        return result;
    }

    public Task createOrUpdateTask(Task task) {
        if (task.getId().equals("0"))
            return createNewTask(task);
        else
            return updateTask(task);
    }

    private Task createNewTask(Task task) {
        Task persistentTask = new Task();
        persistentTask.setId(AllId.generateId());
        persistentTask.setName(task.getName());
        persistentTask.setCreated(task.getCreated());
        tasks.add(persistentTask);
        return persistentTask;
    }

    private Task updateTask(Task task) {
        for (Task persistentTask : tasks) {
            if (persistentTask.getId().equals(task.getId())) {
                persistentTask.setName(task.getName());
                persistentTask.setCreated(task.getCreated());
                return task;
            }
        }
        return null;
    }

    public Task deleteTask(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            Task persistentTask = tasks.get(i);
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

    public void addAll(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    public void clearAll() {
        tasks.removeAll(tasks);
    }
}
