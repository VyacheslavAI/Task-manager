package ru.ivanov.todoproject.controller.restcontroller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.dto.ProjectDTO;
import ru.ivanov.todoproject.dto.Result;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.util.EntityBoundMapperFacade;

import java.io.IOException;

@RestController
@RequestMapping("/project-rest")
public class ProjectRESTController {

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private EntityBoundMapperFacade entityBoundMapperFacade;

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

    @GetMapping("/projectread/{userId}/{projectId}")
    public ProjectDTO readProject(@PathVariable final String userId, @PathVariable final String projectId) throws InvalidArgumentException, ObjectNotFoundException {
        final Project project = serviceLocator.getProjectService().findProjectById(userId, projectId);
        return entityBoundMapperFacade.getProjectDTO(project);
    }

    @DeleteMapping("/projectdelete/{projectId}")
    public boolean deleteProject(@PathVariable final String projectId) throws ObjectNotFoundException, InvalidArgumentException {
        return serviceLocator.getProjectService().deleteProject(projectId);
    }
}