package ru.ivanov.todoproject.service;

import ru.ivanov.todoproject.api.ITaskRepository;
import ru.ivanov.todoproject.api.ITaskService;
import ru.ivanov.todoproject.dao.TaskRepository;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskService implements ITaskService {

    private final ITaskRepository taskRepository = new TaskRepository();

    public Task createOrUpdateTask(final Task task) {
        if (task == null) return null;
        return taskRepository.merge(task);
    }

    public void addAllTask(final List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) return;
        taskRepository.addAll(tasks);
    }

    @Override
    public Task loadById(final String id) {
        if (id == null || id.isEmpty()) return null;
        return taskRepository.findById(id);
    }

    public List<Task> loadTasksByName(final String name) {
        if (name == null || name.isEmpty()) return Collections.emptyList();
        return taskRepository.findByName(name);
    }

    public List<Task> loadTasksByProject(final Project project) {
        if (project == null) return null;
        final List<Task> tasks = taskRepository.findAll();
        final List<Task> result = new ArrayList<>();
        for (final Task task : tasks) {
            if (project.getId().equals(task.getProjectId())) {
                result.add(task);
            }
        }
        return result;
    }

    public List<Task> loadAllTask() {
        return taskRepository.findAll();
    }

    public Task deleteTask(final Task task) {
        if (task == null) return null;
        return taskRepository.delete(task);
    }

    public void deleteAllTask() {
        taskRepository.deleteAll();
    }
}
