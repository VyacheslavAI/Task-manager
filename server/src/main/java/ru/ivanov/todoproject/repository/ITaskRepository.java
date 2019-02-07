package ru.ivanov.todoproject.repository;

import org.apache.deltaspike.data.api.*;
import ru.ivanov.todoproject.entity.Task;

import java.util.List;

@Repository
public interface ITaskRepository extends EntityRepository<Task, String> {

    @Query(value = "from Task t where t.userId = ?1 and t.name = ?2", singleResult = SingleResultType.ANY)
    Task findTaskByName(String userId, String name);

    @Query(value = "from Task t where t.userId = ?1 and t.projectId = ?2 and t.name = ?3", singleResult = SingleResultType.ANY)
    Task findTaskByNameAndProject(String userId, String projectId, String name);

    @Query(value = "from Task t where t.userId = ?1", singleResult = SingleResultType.ANY)
    List<Task> findAllTask(String userId);

    @Query(value = "from Task t where t.userId = ?1 and t.projectId = ?2", singleResult = SingleResultType.ANY)
    List<Task> findAllProjectTask(String userId, String projectId);

    @Modifying
    @Query(value = "delete from Task t where t.userId = ?1 and t.projectId = ?2 and name = ?3", singleResult = SingleResultType.ANY)
    void deleteTask(String userId, String projectId, String taskName);

    @Modifying
    @Query(value = "delete from Task t", singleResult = SingleResultType.ANY)
    boolean deleteAllTask();
}
