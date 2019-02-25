package ru.ivanov.todoproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

@RestController("/task-rest")
public class TaskRESTController {

    @Autowired
    private ServiceLocator serviceLocator;

    @PostMapping("/taskcreate")
    public Task createTask(final String userId, final String projectId, final String taskName) throws ObjectIsNotValidException, InvalidArgumentException, ObjectNotFoundException {
        final Task task = new Task();
        task.setName(taskName);
        return serviceLocator.getTaskService().createTask(userId, projectId, task);
    }

    @PutMapping("/taskupdate")
    public Task updateProject(@RequestBody final Task task) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException {
        return serviceLocator.getTaskService().updateTask(task);
    }

    @GetMapping("/taskread")
    public Task readProject(final String taskId) throws InvalidArgumentException, ObjectNotFoundException {
        return serviceLocator.getTaskService().findTaskById(taskId);
    }

    @DeleteMapping("/taskdelete")
    public boolean deleteProject(final String taskId) throws ObjectNotFoundException, InvalidArgumentException {
        return serviceLocator.getTaskService().deleteTask(taskId);
    }
}
