package ru.ivanov.todoproject.repository;

import ru.ivanov.todoproject.api.IProjectRepository;
import ru.ivanov.todoproject.entity.Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    @Override
    public Project findProjectByName(final String userId, final String name) {
        if (name == null || name.isEmpty()) return null;
        if (userId == null || userId.isEmpty()) return null;
        for (final Project project : entities.values()) {
            final String projectName = project.getName();
            final String projectUserId = project.getUserId();
            if (projectUserId.equals(userId) && projectName.equals(name)) {
                return project;
            }
        }
        return null;
    }

    @Override
    public List<Project> findAllProject(final String userId) {
        if (userId == null || userId.isEmpty()) return Collections.emptyList();
        final List<Project> allProjects = findAll();
        return filterProjectsByUserId(allProjects, userId);
    }

    @Override
    public List<Project> findAllProject() {
        return findAll();
    }

    @Override
    public Project findProjectById(final String userId, final String projectId) {
        if (userId == null || userId.isEmpty()) return null;
        if (projectId == null || projectId.isEmpty()) return null;
        final Project project = super.findById(projectId);
        if (project == null) return null;
        if (!project.getUserId().equals(userId)) return null;
        return project;
    }

    public List<Project> filterProjectsByUserId(final List<Project> projects, final String userId) {
        if (projects == null || projects.isEmpty()) return Collections.emptyList();
        if (userId == null || userId.isEmpty()) return Collections.emptyList();
        final List<Project> result = new ArrayList<>();
        for (final Project project : projects) {
            if (project.getUserId().equals(userId)) {
                result.add(project);
            }
        }
        return result;
    }
}