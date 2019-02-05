package ru.ivanov.todoproject.repository;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ru.ivanov.todoproject.api.ISessionRepository;
import ru.ivanov.todoproject.entity.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {

    @Override
    public Session createSession(Session session) {
        return null;
    }

    @Override
    public Session updateSession(Session session) {
        return null;
    }

    @Override
    public Session deleteSession(Session session) {
        return null;
    }

    @Override
    public boolean deleteAllSession() {
        return false;
    }

    @Override
    public void setConnection(Connection connection) {

    }

    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {

    }
}
