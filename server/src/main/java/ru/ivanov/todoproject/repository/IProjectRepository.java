package ru.ivanov.todoproject.repository;

import org.apache.deltaspike.data.api.*;
import ru.ivanov.todoproject.entity.Project;

import java.util.List;

@Repository
public interface IProjectRepository extends EntityRepository<Project, String> {

    @Query(value = "from Project p where p.userId = ?1 and p.name = ?2", singleResult = SingleResultType.ANY)
    Project findProjectByName(String userId, String name);

    @Query(value = "from Project p where p.userId = ?1", singleResult = SingleResultType.ANY)
    List<Project> findAllProjectByUserId(String userId);

    @Modifying
    @Query(value = "delete from Project p where p.userId = ?1 and p.name = ?2", singleResult = SingleResultType.ANY)
    void deleteProject(String userId, String name);

    @Modifying
    @Query(value = "delete from Project p", singleResult = SingleResultType.ANY)
    boolean deleteAllProject();
}