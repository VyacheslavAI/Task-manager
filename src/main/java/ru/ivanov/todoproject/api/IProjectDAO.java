package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;

import java.util.List;

public interface IProjectDAO {

    Project createOrUpdateProject(Project project);

    Project deleteProject(Project project);

    List<Project> getProjectsByName(String name);

    List<Project> getAllProjects();

    void deleteAllProjects();

    void addAllProjects(List<Project> projects);
}
