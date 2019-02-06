package ru.ivanov.todoproject.api;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import ru.ivanov.todoproject.entity.Project;

import java.util.List;

@Repository
public interface IProjectRepository extends EntityRepository<Project, String> {

    Project updateProject(Project project);

    @Query("from Project p where p.userId = ?1 and p.name = ?2")
    Project findProjectByName(String userId, String name);

    @Query("from Project p where p.userId = ?1")
    List<Project> findAllProjectByUserId(String userId);

    @Query("SELECT p from Project p")
    List<Project> findAllProject();

    @Query("from Project p where p.userId = ?1 and p.projectId = ?2")
    Project findProjectById(String userId, String projectId);

    @Query("delete from Project p where p.userId = ?1 and p.name = ?2")
    void deleteProject(String userId, String name);

    @Query("delete from Project p")
    boolean deleteAllProject();
}
