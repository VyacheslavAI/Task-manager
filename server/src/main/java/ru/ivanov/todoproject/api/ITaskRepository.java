package ru.ivanov.todoproject.api;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;

import java.util.List;

public interface ITaskRepository extends IRepository<Task> {

    @Insert("insert into task (id, name, created, project_id, user_id) " +
            "values (#{id}, #{name}, #{created}, #{projectId}, #{userId})")
    Task createTask(Task task);

    @Update("update task set name = #{name}, created = #{created}, project_id = #{projectId}, user_id = #{userId}")
    Task updateTask(Task task);

    @Select("select * from task where user_id = #{userId} and name = #{name}")
    Task findTaskByName(String userId, String name);

    @Select("select * from task where user_id = #{userId} and project_id = #{projectId} and name = #{name}")
    Task findTaskByName(String userId, String projectId, String name);

    @Select("select * from task where user_id = #{userId} and name = #{name}")
    Task findTaskById(String userId, String name);

    @Select("select * from task where user_id = #{userId}")
    List<Task> findAllTask(String userId);

    @Select("select * from task")
    List<Task> findAllTask();

    @Select("select * from project where project_id = #{projectId}")
    List<Task> findAllProjectTask(String userId, String projectId);

    @Delete("delete from project where id = #{id}")
    Task deleteTask(Task task);

    @Delete("delete * from project")
    boolean deleteAllTask();
}
