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
import java.util.List;

@RestController
@RequestMapping("/project-rest")
public class ProjectRESTController {

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private EntityBoundMapperFacade entityBoundMapperFacade;

    @GetMapping(value = "/projectlist/{userId}")
    public List<ProjectDTO> projectList(@PathVariable final String userId) throws InvalidArgumentException {
        final List<Project> projects = serviceLocator.getProjectService().findAllUserProject(userId);
        return entityBoundMapperFacade.getListProjectDTO(projects);
    }

    @PostMapping(value = "/projectcreate")
    public Project createProject(@RequestBody final Result result) throws ObjectIsNotValidException, InvalidArgumentException {
        final Project project = new Project();
        final String userId = (String) result.get("userId");
        final String projectName = (String) result.get("projectName");
        project.setName(projectName);
        return serviceLocator.getProjectService().createProject(userId, project);
    }

    @PutMapping("/projectupdate/{id}")
    public Project updateProject(@PathVariable final String id, @RequestBody final ProjectDTO result, final ObjectMapper objectMapper) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException, IOException {
        final Project persistentProject = serviceLocator.getProjectService().findProjectById(result.getUserId(), result.getId());
        persistentProject.setName(result.getName());
        persistentProject.setUserId(result.getUserId());
        return serviceLocator.getProjectService().updateProject(persistentProject);
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