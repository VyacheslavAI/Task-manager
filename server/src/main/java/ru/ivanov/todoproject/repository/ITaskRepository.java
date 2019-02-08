package ru.ivanov.todoproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivanov.todoproject.entity.Task;

import java.util.List;

@Repository
public interface ITaskRepository extends JpaRepository<Task, String> {

    Task findTaskByUserIdAndName(String userId, String name);

    Task findTaskByUserIdAndProjectIdAndName(String userId, String projectId, String name);

    List<Task> findAllByUserId(String userId);

    List<Task> findAllByUserIdAndProjectId(String userId, String projectId);
}
