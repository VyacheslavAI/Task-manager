package ru.ivanov.todoproject.api;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ru.ivanov.todoproject.entity.Project;

import java.util.List;

public interface IProjectRepository extends IRepository<Project> {

    @Insert("insert into project (id, created, name, user_id) " +
            "values (#{id}, #{created}, #{name}, #{userId})")
    Project createProject(Project project);

    @Update("update project set created = #{created}, name = #{name}, user_id = #{userId}")
    Project updateProject(Project project);

    @Select("select * from project where user_id = #{userId} and name = #{name}")
    Project findProjectByName(String userId, String name);

    @Select("select * from project where user_id = #{userId}")
    List<Project> findAllProjectByUserId(String userId);

    @Select("select * from project")
    List<Project> findAllProject();

    @Select("select * from project where user_id = #{userId} and id = #{projectId}")
    Project findProjectById(String userId, String projectId);

    @Delete("delete from project where id = #{id}")
    Project deleteProject(Project project);

    @Delete("delete * from project")
    boolean deleteAllProject();
}
