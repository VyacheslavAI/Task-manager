package ru.ivanov.todoproject.repository;

import ru.ivanov.todoproject.api.IUserRepository;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.validator.Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public User fetch(final ResultSet resultSet) throws SQLException {
        final String id = resultSet.getString(1);
        final String login = resultSet.getString(2);
        final String passwordHash = resultSet.getString(3);
        final Date created = resultSet.getDate(4);
        final User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setPasswordHash(passwordHash);
        user.setCreated(created);
        return user;
    }

    @Override
    public User findByLogin(final String login) {
        if (!Validator.isArgumentsValid(login)) return null;
        final String query = "select * from user where login = ?";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            final ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return fetch(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findBySession(final String sessionId) {
        return null;
    }

    @Override
    public User createUser(final User user) {
        final String id = user.getId();
        final String login = user.getLogin();
        final String passwordHash = user.getPasswordHash();
        final Date created = user.getCreated();
        final String query = "insert into user (id, login, passwordhash, created) values(?, ?, ?, ?)";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            statement.setString(2, login);
            statement.setString(3, passwordHash);
            statement.setDate(4, new java.sql.Date(created.getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User updateUser(final User user) {
        final String userId = user.getId();
        final String login = user.getLogin();
        final String passwordHash = user.getPasswordHash();
        final Date created = user.getCreated();
        final String query = "update user set login = ?, passwordhash = ?, created = ? where id = ?";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            statement.setString(2, passwordHash);
            statement.setDate(3, new java.sql.Date(created.getTime()));
            statement.setString(4, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User delete(final User user) {
        final String id = user.getId();
        final String query = "delete from user where id = ?";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean deleteAll() {
        final String query = "delete * from user";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
