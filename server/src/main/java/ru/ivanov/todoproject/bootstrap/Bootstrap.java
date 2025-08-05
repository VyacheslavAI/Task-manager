package ru.ivanov.todoproject.bootstrap;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.ivanov.todoproject.api.*;
import ru.ivanov.todoproject.util.Serializer;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;

public class Bootstrap implements ServiceLocator {

    @Inject
    private IProjectService projectService;

    @Inject
    private ITaskService taskService;

    @Inject
    private IUserService userService;

    @Inject
    private Serializer serializer;

    @PostConstruct
    private void userInitialization() throws InvalidArgumentException, NoSuchAlgorithmException, ObjectIsNotValidException, JsonProcessingException, ObjectNotFoundException {
        userService.userInitialize("admin", "admin");
        userService.userInitialize("root", "root");
        
        // Demonstrate Cyrillic text support by creating a project with Cyrillic name
        initializeCyrillicDemo();
    }
    
    private void initializeCyrillicDemo() {
        try {
            // Create a demo project with Cyrillic name "цвфц"
            ru.ivanov.todoproject.entity.Project cyrillicProject = new ru.ivanov.todoproject.entity.Project();
            cyrillicProject.setName("цвфц");
            cyrillicProject.setUserId("admin");
            
            projectService.createProject("admin", cyrillicProject);
            
            System.out.println("Successfully created demo project with Cyrillic name: цвфц");
        } catch (Exception e) {
            System.err.println("Failed to create Cyrillic demo project: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public IUserService getUserService() {
        return userService;
    }

    @Override
    public IProjectService getProjectService() {
        return projectService;
    }

    @Override
    public ITaskService getTaskService() {
        return taskService;
    }
}