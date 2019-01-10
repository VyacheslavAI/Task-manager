package ru.ivanov.todoproject.dao;

import ru.ivanov.todoproject.entity.Project;

import java.util.ArrayList;
import java.util.List;

class AllProjects {

    private List<Project> projects = new ArrayList<>();

    public List<Project> withName(String name) {
        List<Project> result = new ArrayList<>();
        for (Project project : projects) {
            if (project.getName().equals(name)) {
                result.add(project);
            }
        }
        return result;
    }

    public Project createOrUpdateProject(Project project) {
        if (project.getId().equals("0"))
            return createNewProject(project);
        else
            return updateProject(project);
    }

    private Project createNewProject(Project project) {
        Project persistentProject = new Project();
        persistentProject.setId(AllId.generateId());
        persistentProject.setName(project.getName());
        persistentProject.setCreated(project.getCreated());
        projects.add(persistentProject);
        return persistentProject;
    }

    private Project updateProject(Project project) {
        for (Project persistentProject : projects) {
            if (persistentProject.getId().equals(project.getId())) {
                persistentProject.setName(project.getName());
                persistentProject.setCreated(project.getCreated());
                return project;
            }
        }
        return null;
    }

    public Project deleteProject(Project project) {
        for (int i = 0; i < projects.size(); i++) {
            Project persistentProject = projects.get(i);
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

    public void addAll(List<Project> projects) {
        this.projects.addAll(projects);
    }

    public void clearAll() {
        projects.removeAll(projects);
    }
}
