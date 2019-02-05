package ru.ivanov.todoproject.repository;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ru.ivanov.todoproject.api.ITaskRepository;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.validator.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Override
    public Task createTask(Task task) {
        return null;
    }

    @Override
    public Task updateTask(Task task) {
        return null;
    }

    @Override
    public Task findTaskByName(String userId, String name) {
        return null;
    }

    @Override
    public Task findTaskByName(String userId, String projectId, String name) {
        return null;
    }

    @Override
    public Task findTaskById(String userId, String id) {
        return null;
    }

    @Override
    public List<Task> findAllTask(String userId) {
        return null;
    }

    @Override
    public List<Task> findAllTask() {
        return null;
    }

    @Override
    public List<Task> findAllProjectTask(String userId, String projectId) {
        return null;
    }

    @Override
    public Task deleteTask(Task task) {
        return null;
    }

    @Override
    public boolean deleteAllTask() {
        return false;
    }

    @Override
    public void setConnection(Connection connection) {

    }

    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {

    }
}
