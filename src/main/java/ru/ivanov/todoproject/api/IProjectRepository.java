package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;

import java.util.List;

public interface IProjectRepository extends IRepository<Project> {

    Project findProjectByName(String userId, String name);

    List<Project> findAllProjectByUserId(String userId);

    List<Project> findAllProject();

    Project findProjectById(String userId, String projectId);
}
