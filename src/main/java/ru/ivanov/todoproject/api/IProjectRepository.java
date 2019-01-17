package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;

import java.util.List;

public interface IProjectRepository extends IRepository<Project> {

    List<Project> findByName(final String name);
}
