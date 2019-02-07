package ru.ivanov.todoproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivanov.todoproject.entity.Task;

import java.util.List;

@Repository
public interface ITaskRepository extends JpaRepository<Task, String> {

    Task findTaskByName(String userId, String name);

    Task findTaskByNameAndProject(String userId, String projectId, String name);

    List<Task> findAllTask(String userId);

    List<Task> findAllProjectTask(String userId, String projectId);
}
