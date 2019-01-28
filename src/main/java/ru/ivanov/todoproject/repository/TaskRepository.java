package ru.ivanov.todoproject.repository;

import ru.ivanov.todoproject.api.ITaskRepository;
import ru.ivanov.todoproject.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Override
    public List<Task> findByName(final String name) {
        if (name == null || name.isEmpty()) return null;
        final List<Task> result = new ArrayList<>();
        for (final Task task : entities.values()) {
            if (task.getName().equals(name)) {
                result.add(task);
            }
        }
        return result;
    }
}
