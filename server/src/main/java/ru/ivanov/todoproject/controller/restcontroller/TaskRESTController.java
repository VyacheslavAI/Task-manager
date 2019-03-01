package ru.ivanov.todoproject.controller.restcontroller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.dto.Result;
import ru.ivanov.todoproject.dto.TaskDTO;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.util.EntityBoundMapperFacade;

import java.io.IOException;

@RestController
@RequestMapping("/task-rest")
public class TaskRESTController {

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private EntityBoundMapperFacade entityBoundMapperFacade;

    @PostMapping("/taskcreate")
    public TaskDTO createTask(@RequestBody final Result result) throws ObjectIsNotValidException, InvalidArgumentException, ObjectNotFoundException {
        final String userId = (String) result.get("userId");
        final String projectId = (String) result.get("projectId");
        final String taskName = (String) result.get("taskName");
        final Task task = new Task();
        task.setName(taskName);
        final Task persistentTask = serviceLocator.getTaskService().createTask(userId, projectId, task);
        return entityBoundMapperFacade.getTaskDTO(persistentTask);
    }

    @PutMapping("/taskupdate")
    public TaskDTO updateProject(@RequestBody final Result result, final ObjectMapper objectMapper) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException, IOException {
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        final String taskString = objectMapper.writeValueAsString(result.get("task"));
        final Task task = objectMapper.readValue(taskString, Task.class);
        final Task persistentTask = serviceLocator.getTaskService().updateTask(task);
        return entityBoundMapperFacade.getTaskDTO(persistentTask);
    }

    @GetMapping("/taskread/{taskId}")
    public TaskDTO readProject(@PathVariable final String taskId) throws InvalidArgumentException, ObjectNotFoundException {
        final Task task = serviceLocator.getTaskService().findTaskById(taskId);
        return entityBoundMapperFacade.getTaskDTO(task);
    }

    @DeleteMapping("/taskdelete/{taskId}")
    public boolean deleteProject(@PathVariable final String taskId) throws ObjectNotFoundException, InvalidArgumentException {
        return serviceLocator.getTaskService().deleteTask(taskId);
    }
}