package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;

import java.util.List;

public interface IProjectService extends IService {

    Project createOrUpdateProject(final Project project);

    Project loadById(final String id);

    void addAllProject(final List<Project> projects);

    List<Project> loadProjectByName(final String name);

    List<Project> loadAllProject();

    Project deleteProject(final Project project);

    void deleteAllProject();
}
