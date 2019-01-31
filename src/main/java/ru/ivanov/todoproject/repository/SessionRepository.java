package ru.ivanov.todoproject.repository;

import ru.ivanov.todoproject.api.ISessionRepository;
import ru.ivanov.todoproject.entity.Session;

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
}
