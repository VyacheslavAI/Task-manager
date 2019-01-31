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
        final String query = "select * from user where 'login' = ?";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            final ResultSet resultSet = statement.executeQuery();
            return fetch(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
