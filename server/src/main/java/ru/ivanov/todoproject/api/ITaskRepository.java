package ru.ivanov.todoproject.api;

import org.apache.ibatis.annotations.*;
import ru.ivanov.todoproject.entity.Task;

import java.util.List;

public interface ITaskRepository extends IRepository<Task> {

    @Insert("insert into task (id, name, created, project_id, user_id) " +
            "values (#{id}, #{name}, #{created}, #{projectId}, #{userId})")
    Task createTask(Task task);

    @Update("update task set name = #{name}, created = #{created}, project_id = #{projectId}, user_id = #{userId} where id = #{id}")
    Task updateTask(Task task);

    //    @Select("select * from task where user_id = #{userId} and name = #{name}")
    Task findTaskByName(String userId, String name);

    @Select("select * from task where user_id = #{userId} and project_id = #{projectId} and name = #{name}")
    @Results({
            @Result(column = "project_id", property = "projectId"),
            @Result(column = "user_id", property = "userId")})
    Task findTaskByName(String userId, String projectId, String name);

    @Select("select * from task where user_id = #{userId} and id = #{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "project_id", property = "projectId"),
            @Result(column = "user_id", property = "userId")})
    Task findTaskById(String userId, String id);

    @Select("select * from task where user_id = #{userId}")
    List<Task> findAllTask(String userId);

    //    @Select("select * from task")
    List<Task> findAllTask();

    @Select("select * from task where project_id = #{projectId}")
    List<Task> findAllProjectTask(String userId, String projectId);

    @Delete("delete from task where id = #{id}")
    Task deleteTask(Task task);

    @Delete("delete * task project")
    boolean deleteAllTask();
}
