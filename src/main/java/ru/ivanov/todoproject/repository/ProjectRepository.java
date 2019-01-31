package ru.ivanov.todoproject.repository;

import ru.ivanov.todoproject.api.IProjectRepository;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.validator.Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    @Override
    public Project fetch(final ResultSet resultSet) throws SQLException {
        final String id = resultSet.getString(1);
        final Date created = resultSet.getDate(2);
        final String projectName = resultSet.getString(3);
        final String userId = resultSet.getString(4);
        final Project project = new Project();
        project.setId(id);
        project.setCreated(created);
        project.setName(projectName);
        project.setUserId(userId);
        return project;
    }


    @Override
    public Project findProjectByName(final String userId, final String name) {
        if (!Validator.isArgumentsValid(userId, name)) return null;
        final String query = "select * from 'project' where 'userId' = ? and 'name' = ?";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            statement.setString(2, name);
            final ResultSet resultSet = statement.executeQuery();
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
        final String query = "select * from 'project' where 'userId' = ?";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            final ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
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
        final String query = "select * from 'project';";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            final ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
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
        final String query = "select * from 'project' where 'userId' = ? and 'id' = ?";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            statement.setString(2, projectId);
            final ResultSet resultSet = statement.executeQuery();
            return fetch(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}