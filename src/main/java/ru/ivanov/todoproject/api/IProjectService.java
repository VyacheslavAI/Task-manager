package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.User;

import java.util.List;

public interface IProjectService {
    Project createOrUpdateProject(Project project);

    void addAllProject(List<Project> projects);

    Project loadProjectById(String id);

    List<Project> loadProjectByName(String name);

    List<Project> loadAllProject();

    List<Project> loadAllProjectByUser(User user);

    Project deleteProject(Project project);

    void deleteAllProject();

    ServiceLocator getServiceLocator();

    void setServiceLocator(ServiceLocator serviceLocator);
}
