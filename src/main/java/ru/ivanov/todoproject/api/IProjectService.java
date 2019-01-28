package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import java.util.List;

public interface IProjectService {

    Project createProject(String userId, Project project) throws ObjectIsNotValidException, InvalidArgumentException;

    Project updateProject(Project project) throws ObjectIsNotValidException, InvalidArgumentException, ObjectNotFoundException;

    boolean addAllProject(List<Project> projects);

    Project loadUserProjectById(String userId, String projectId) throws ObjectNotFoundException, InvalidArgumentException;

    List<Project> loadUserProjectByName(String userId, String name) throws InvalidArgumentException;

    List<Project> loadAllUserProject(String userId) throws InvalidArgumentException;

    List<Project> loadAllProject();

    Project deleteProject(Project project) throws ObjectIsNotValidException, ObjectNotFoundException;

    boolean deleteAllProject();

    ServiceLocator getServiceLocator();

    void setServiceLocator(ServiceLocator serviceLocator);
}
