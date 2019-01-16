package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;

import java.util.List;

public interface IProjectService {

    Project createOrUpdateProject(final Project project);

    void addAllProject(List<Project> projects);

    List<Project> loadProjectByName(final String name);

    List<Project> loadAllProject();

    Project deleteProject(Project project);

    void deleteAllProject();
}
