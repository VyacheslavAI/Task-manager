package ru.ivanov.todoproject.repository;

import ru.ivanov.todoproject.api.ITaskRepository;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.validator.Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Override
    public Task fetch(final ResultSet resultSet) throws SQLException {
        final String id = resultSet.getString(1);
        final String taskName = resultSet.getString(2);
        final Date created = resultSet.getDate(3);
        final String projectId = resultSet.getString(4);
        final String userId = resultSet.getString(5);
        final Task task = new Task();
        task.setId(id);
        task.setName(taskName);
        task.setCreated(created);
        task.setProjectId(projectId);
        task.setUserId(userId);
        return task;
    }

    @Override
    public Task createTask(final Task task) {
        final String id = task.getId();
        final String taskName = task.getName();
        final Date created = task.getCreated();
        final String projectId = task.getProjectId();
        final String userId = task.getUserId();
        final String query = "insert into task (id, name, created, project_id, user_id) values (?, ?, ?, ?, ?)";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            statement.setString(2, taskName);
            statement.setDate(3, new java.sql.Date(created.getTime()));
            statement.setString(4, projectId);
            statement.setString(5, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, taskName);
            statement.setDate(2, new java.sql.Date(created.getTime()));
            statement.setString(3, projectId);
            statement.setString(4, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public Task delete(final Task task) {
        final String taskId = task.getId();
        final String query = "delete from task where id = ?";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, taskId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public Task findTaskByName(final String userId, final String name) {
        if (!Validator.isArgumentsValid(userId, name)) return null;
        final String query = "select * from task where user_id = ? and name = ?";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            statement.setString(2, name);
            final ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return fetch(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Task findTaskByName(final String userId, final String projectId, final String taskName) {
        if (!Validator.isArgumentsValid(userId, projectId, taskName)) return null;
        final String query = "select * from task where user_id = ? and project_id = ? and name = ?";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            statement.setString(2, projectId);
            statement.setString(3, taskName);
            final ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return fetch(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Task findTaskById(final String userId, final String taskId) {
        if (!Validator.isArgumentsValid(userId, taskId)) return null;
        final String query = "select * from task where user_id =  ? and id = ?";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            statement.setString(2, taskId);
            final ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return fetch(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Task> findAllTask(final String userId) {
        if (!Validator.isArgumentsValid(userId)) return Collections.emptyList();
        final List<Task> tasks = new ArrayList<>();
        final String query = "select * from task where user_id = ?";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tasks.add(fetch(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public List<Task> findAllTask() {
        final List<Task> tasks = new ArrayList<>();
        final String query = "select * from task";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tasks.add(fetch(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public List<Task> findAllProjectTask(final String userId, final String projectId) {
        if (!Validator.isArgumentsValid(userId, projectId)) return Collections.emptyList();
        final List<Task> tasks = new ArrayList<>();
        final String query = "select * from task where user_id = ? and project_id = ?";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            statement.setString(2, projectId);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tasks.add(fetch(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
