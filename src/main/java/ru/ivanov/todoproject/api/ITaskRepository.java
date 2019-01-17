package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Task;

import java.util.List;

public interface ITaskRepository extends IRepository<Task> {

    List<Task> findByName(final String name);
}
