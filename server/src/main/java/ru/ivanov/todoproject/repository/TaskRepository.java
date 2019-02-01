package ru.ivanov.todoproject.repository;

import org.apache.ibatis.session.SqlSession;
import ru.ivanov.todoproject.api.ITaskRepository;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.validator.Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Override
    public Task createTask(final Task task) {
        final String id = task.getId();
        final String taskName = task.getName();
        final Date created = task.getCreated();
        final String projectId = task.getProjectId();
        final String userId = task.getUserId();
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.insert("createTask", task);
            sqlSession.commit();
        }
        return task;
    }

    @Override
    public Task updateTask(final Task task) {
        final String taskName = task.getName();
        final Date created = task.getCreated();
        final String projectId = task.getProjectId();
        final String userId = task.getUserId();
        final String query = "update task set name = ?, created = ?, project_id = ?, user_id = ?";
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.update("updateTask", task);
            sqlSession.commit();
        }
        return task;
    }

    @Override
    public Task deleteTask(final Task task) {
        final String taskId = task.getId();
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.delete("deleteTask", task);
            sqlSession.commit();
        }
        return task;
    }

    @Override
    public boolean deleteAllTask() {
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.delete("deleteAllTask");
            sqlSession.commit();
            return true;
        }
    }

    @Override
    public Task findTaskByName(final String userId, final String name) {
        if (!Validator.isArgumentsValid(userId, name)) return null;
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            final Map<String, String> parameters = new HashMap<>();
            parameters.put("userId", userId);
            parameters.put("name", name);
            return sqlSession.selectOne("findTaskByName", parameters);
        }
    }

    @Override
    public Task findTaskByName(final String userId, final String projectId, final String taskName) {
        if (!Validator.isArgumentsValid(userId, projectId, taskName)) return null;
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            final Map<String, String> parameters = new HashMap<>();
            parameters.put("userId", userId);
            parameters.put("projectId", projectId);
            parameters.put("name", taskName);
            return sqlSession.selectOne("findTaskByName", parameters);
        }
    }

    @Override
    public Task findTaskById(final String userId, final String taskId) {
        if (!Validator.isArgumentsValid(userId, taskId)) return null;
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            final Map<String, String> parameters = new HashMap<>();
            parameters.put("userId", userId);
            parameters.put("id", taskId);
            return sqlSession.selectOne("findTaskById", parameters);
        }
    }

    @Override
    public List<Task> findAllTask(final String userId) {
        if (!Validator.isArgumentsValid(userId)) return Collections.emptyList();
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            final Map<String, String> parameters = new HashMap<>();
            parameters.put("userId", userId);
            return sqlSession.selectList("findAllTask", parameters);
        }
    }

    @Override
    public List<Task> findAllTask() {
        final List<Task> tasks = new ArrayList<>();
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectList("findAllTask");
        }
    }

    @Override
    public List<Task> findAllProjectTask(final String userId, final String projectId) {
        if (!Validator.isArgumentsValid(userId, projectId)) return Collections.emptyList();
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            final Map<String, String> parameters = new HashMap<>();
            parameters.put("userId", userId);
            parameters.put("projectId", projectId);
            return sqlSession.selectList("findAllProjectTask", parameters);
        }
    }
}
