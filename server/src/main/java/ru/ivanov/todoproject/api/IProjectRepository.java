package ru.ivanov.todoproject.api;

import org.apache.ibatis.annotations.*;
import ru.ivanov.todoproject.entity.Project;

import java.util.List;

public interface IProjectRepository extends IRepository<Project> {

    @Insert("insert into project (id, created, name, user_id) " +
            "values (#{id}, #{created}, #{name}, #{userId})")
    Project createProject(Project project);

    @Update("update project set created = #{created}, name = #{name}, user_id = #{userId} where id = #{id}")
    Project updateProject(Project project);

    @Select("select * from project where user_id = #{userId} and name = #{name}")
    @Results({
            @Result(column = "user_id", property = "userId")})
    Project findProjectByName(String userId, String name);

    @Select("select * from project where user_id = #{userId}")
    List<Project> findAllProjectByUserId(String userId);

    @Select("select id, created, name, user_id from project")
    List<Project> findAllProject();

    @Select("select * from project where user_id = #{userId} and id = #{projectId}")
    Project findProjectById(String userId, String projectId);

    @Delete("delete from project where id = #{id}")
    Project deleteProject(Project project);

    @Delete("delete * from project")
    boolean deleteAllProject();
}
