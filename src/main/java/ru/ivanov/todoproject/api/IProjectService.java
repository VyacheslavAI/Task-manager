package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;

import java.util.List;

public interface IProjectService {
    Project createOrUpdateProject(Project project) throws ObjectIsNotValidException;

    boolean addAllProject(List<Project> projects);

    Project loadProjectById(String id);

    List<Project> loadProjectByName(String name);

    List<Project> loadAllProject();

    List<Project> loadAllProjectByUser(User user) throws ObjectIsNotValidException;

    Project deleteProject(Project project) throws ObjectIsNotValidException;

    boolean deleteAllProject();

    ServiceLocator getServiceLocator();

    void setServiceLocator(ServiceLocator serviceLocator);
}
