package ru.ivanov.todoproject.repository;

import ru.ivanov.todoproject.api.IProjectRepository;
import ru.ivanov.todoproject.entity.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    public List<Project> findByName(final String name) {
        if (name == null || name.isEmpty()) return null;
        final List<Project> result = new ArrayList<>();
        for (final Project project : entities.values()) {
            if (project.getName().equals(name)) {
                result.add(project);
            }
        }
        return result;
    }
}