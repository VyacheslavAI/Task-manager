package ru.ivanov.todoproject.dao;

import ru.ivanov.todoproject.api.ITaskRepository;
import ru.ivanov.todoproject.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    public List<Task> findByName(final String name) {
        final List<Task> result = new ArrayList<>();
        for (final Task task : entities.values()) {
            if (task.getName().equals(name)) {
                result.add(task);
            }
        }
        return result;
    }
}
