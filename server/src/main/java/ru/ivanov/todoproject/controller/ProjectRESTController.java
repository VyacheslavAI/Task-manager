package ru.ivanov.todoproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.dto.Result;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/project-rest")
public class ProjectRESTController {

    @Autowired
    private ServiceLocator serviceLocator;

    @RequestMapping(path = "/test2")
    public Project test() {
        return new Project();
    }

    @PostMapping(value = "/projectcreate")
    public Project createProject(@RequestBody final Result result) throws ObjectIsNotValidException, InvalidArgumentException {
        final Project project = new Project();
        final String userId = result.get("userId");
        final String projectName = result.get("projectName");
        project.setName(projectName);
        return serviceLocator.getProjectService().createProject(userId, project);
    }

    @PutMapping("/projectupdate")
    public Project updateProject(@RequestBody final Result result) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException {
        
        return serviceLocator.getProjectService().updateProject(project);
    }

    @GetMapping("/projectread")
    public Project readProject(final String userId, final String projectId) throws InvalidArgumentException, ObjectNotFoundException {
        return serviceLocator.getProjectService().findProjectById(userId, projectId);
    }

    @DeleteMapping("/projectdelete")
    public boolean deleteProject(final String projectId) throws ObjectNotFoundException, InvalidArgumentException {
        return serviceLocator.getProjectService().deleteProject(projectId);
    }
}