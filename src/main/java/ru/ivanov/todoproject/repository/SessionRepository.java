package ru.ivanov.todoproject.repository;

import ru.ivanov.todoproject.api.ISessionRepository;
import ru.ivanov.todoproject.entity.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {

    @Override
    public Session fetch(ResultSet resultSet) throws SQLException {
        final String id = resultSet.getString(1);
        final Date date = resultSet.getDate(2);
        final long timestamp = resultSet.getLong(3);
        final String userId = resultSet.getString(4);
        final String signature = resultSet.getString(5);
        final Session session = new Session();
        session.setId(id);
        session.setCreated(date);
        session.setTimestamp(timestamp);
        session.setUserId(userId);
        session.setSignature(signature);
        return session;
    }

    @Override
    public Session createSession(final Session session) {
        final String id = session.getId();
        final Date created = session.getCreated();
        final String userId = session.getUserId();
        final long timestamp = session.getTimestamp();
        final String signature = session.getSignature();
        final String query = "insert into session (id, created, timestamp, user_id, signature) values (?, ?, ?, ?, ?)";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            statement.setDate(2, new java.sql.Date(created.getTime()));
            statement.setLong(3, timestamp);
            statement.setString(4, userId);
            statement.setString(5, signature);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return session;
    }

    @Override
    public Session updateSession(final Session session) {
        final Date created = session.getCreated();
        final String userId = session.getUserId();
        final long timestamp = session.getTimestamp();
        final String signature = session.getSignature();
        final String query = "update session set created = ?, timestamp = ?, user_id = ?, signature = ?";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, new java.sql.Date(created.getTime()));
            statement.setLong(2, timestamp);
            statement.setString(3, userId);
            statement.setString(4, signature);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return session;
    }

    @Override
    public Session delete(final Session session) {
        final String id = session.getId();
        final String query = "delete from session where id = ?";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return session;
    }

    @Override
    public boolean deleteAll() {
        final String query = "delete * from session";
        try (final PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
