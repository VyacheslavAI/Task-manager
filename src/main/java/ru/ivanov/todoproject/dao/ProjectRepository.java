package ru.ivanov.todoproject.dao;

import ru.ivanov.todoproject.entity.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {

    private final List<Project> projects = new ArrayList<>();

    public List<Project> getProjectsByName(final String name) {
        final List<Project> result = new ArrayList<>();
        for (final Project project : projects) {
            if (project.getName().equals(name)) {
                result.add(project);
            }
        }
        return result;
    }

    public Project createOrUpdateProject(final Project project) {
        if (project.getId().equals("0"))
            return createNewProject(project);
        else
            return updateProject(project);
    }

    private Project createNewProject(final Project project) {
        final Project persistentProject = new Project();
        persistentProject.setId(IdRepository.generateId());
        persistentProject.setName(project.getName());
        persistentProject.setCreated(project.getCreated());
        projects.add(persistentProject);
        return persistentProject;
    }

    private Project updateProject(final Project project) {
        for (final Project persistentProject : projects) {
            if (persistentProject.getId().equals(project.getId())) {
                persistentProject.setName(project.getName());
                persistentProject.setCreated(project.getCreated());
                return project;
            }
        }
        return null;
    }

    public Project deleteProject(final Project project) {
        for (int i = 0; i < projects.size(); i++) {
            final Project persistentProject = projects.get(i);
            if (persistentProject.getId().equals(project.getId())) {
                projects.remove(persistentProject);
                break;
            }
        }
        return project;
    }

    public List<Project> getAllProjects() {
        return new ArrayList<>(projects);
    }

    public void addAllProjects(final List<Project> projects) {
        this.projects.addAll(projects);
    }

    public void deleteAllProjects() {
        projects.removeAll(projects);
    }
}
