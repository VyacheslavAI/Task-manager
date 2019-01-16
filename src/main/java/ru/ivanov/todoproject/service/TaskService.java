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

    private ITaskRepository taskRepo = new TaskRepository();

    public Task createOrUpdateTask(final Task task) {
        if (task == null)
            return null;

        return taskRepo.merge(task);
    }

    public void addAllTask(final List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) return;

        taskRepo.addAllEntity(tasks);
    }

    public List<Task> loadTaskByName(final String name) {
        if (name == null || name.isEmpty())
            return Collections.emptyList();

        return taskRepo.getEntitiesByName(name);
    }

    public List<Task> loadTasksByProject(final Project project) {
        if (project == null)
            return null;

        final List<Task> tasks = taskRepo.getAllEntity();
        final List<Task> result = new ArrayList<>();
        for (final Task task : tasks) {
            if (project.getId().equals(task.getProjectId())) {
                result.add(task);
            }
        }
        return result;
    }

    public List<Task> loadAllTask() {
        return taskRepo.getAllEntity();
    }

    public Task deleteTask(final Task task) {
        if (task == null)
            return null;

        return taskRepo.deleteEntity(task);
    }

    public void deleteAllTask() {
        taskRepo.deleteAllEntity();
    }
}
