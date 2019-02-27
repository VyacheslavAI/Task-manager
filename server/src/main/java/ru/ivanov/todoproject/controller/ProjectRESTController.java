package ru.ivanov.todoproject.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.dto.Result;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import java.io.IOException;

@RestController
@RequestMapping("/project-rest")
public class ProjectRESTController {

    @Autowired
    private ServiceLocator serviceLocator;

    @PostMapping(value = "/projectcreate")
    public Project createProject(@RequestBody final Result result) throws ObjectIsNotValidException, InvalidArgumentException {
        final Project project = new Project();
        final String userId = (String) result.get("userId");
        final String projectName = (String) result.get("projectName");
        project.setName(projectName);
        return serviceLocator.getProjectService().createProject(userId, project);
    }

    @PutMapping("/projectupdate")
    public Project updateProject(@RequestBody final Result result, final ObjectMapper objectMapper) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException, IOException {
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        final String projectString = objectMapper.writeValueAsString(result.get("project"));
        final Project project = objectMapper.readValue(projectString, Project.class);
        return serviceLocator.getProjectService().updateProject(project);
    }

    @GetMapping("/projectread")
    public Project readProject(@RequestBody final Result result) throws InvalidArgumentException, ObjectNotFoundException {
        final String userId = (String) result.get("userId");
        final String projectId = (String) result.get("projectId");
        return serviceLocator.getProjectService().findProjectById(userId, projectId);
    }

    @DeleteMapping("/projectdelete")
    public boolean deleteProject(@RequestBody final Result result) throws ObjectNotFoundException, InvalidArgumentException {
        final String projectId = (String) result.get("projectId");
        return serviceLocator.getProjectService().deleteProject(projectId);
    }
}