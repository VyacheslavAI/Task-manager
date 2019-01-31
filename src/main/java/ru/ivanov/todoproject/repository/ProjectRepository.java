package ru.ivanov.todoproject.repository;

import ru.ivanov.todoproject.api.IProjectRepository;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.validator.Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    @Override
    public Project fetch(final ResultSet resultSet) throws SQLException {
        final Project result = new Project();
        final String id = resultSet.getString(1);
        final Date created = resultSet.getDate(2);
        final String projectName = resultSet.getString(3);
        final String userId = resultSet.getString(4);
        result.setId(id);
        result.setCreated(created);
        result.setName(projectName);
        result.setUserId(userId);
        return result;
    }

    @Override
    public Project createProject(final Project project) {
        final String id = project.getId();
        final Timestamp created = new Timestamp(project.getCreated().getTime());
        final String projectName = project.getName();
        final String userId = project.getUserId();
        final String query = "insert into project (id, created, name, user_id) values (?, ?, ?, ?)";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            statement.setDate(2, new java.sql.Date(created.getTime()));
            statement.setString(3, projectName);
            statement.setString(4, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public Project updateProject(final Project project) {
        final String projectId = project.getId();
        final java.sql.Date created = new java.sql.Date(project.getCreated().getTime());
        final String projectName = project.getName();
        final String userId = project.getUserId();
        final String queryUpdate = "update project set created = ?, user_id = ?, name = ? where id = ?";
        try (final PreparedStatement statement = connection.prepareStatement(queryUpdate)) {
            statement.setDate(1, created);
            statement.setString(2, userId);
            statement.setString(3, projectName);
            statement.setString(4, projectId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public Project delete(final Project project) {
        final String id = project.getId();
        final String queryDelete = "delete from project where id = ?";
        try (final PreparedStatement statement = connection.prepareStatement(queryDelete)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public boolean deleteAll() {
        final String query = "delete * from project";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Project findProjectByName(final String userId, final String name) {
        if (!Validator.isArgumentsValid(userId, name)) return null;
        final String query = "select * from project where user_id = ? and name = ?";
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
    public List<Project> findAllProjectByUserId(final String userId) {
        final List<Project> result = new ArrayList<>();
        if (!Validator.isArgumentsValid(userId)) return Collections.emptyList();
        final String query = "select * from project where user_id = ?";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(fetch(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Project> findAllProject() {
        final List<Project> result = new ArrayList<>();
        final String query = "select * from project;";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(fetch(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Project findProjectById(final String userId, final String projectId) {
        if (!Validator.isArgumentsValid(userId, projectId)) return null;
        final String query = "select * from project where user_id = ? and id = ?";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            statement.setString(2, projectId);
            final ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return fetch(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}