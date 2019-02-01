package ru.ivanov.todoproject.repository;

import org.apache.ibatis.session.SqlSession;
import ru.ivanov.todoproject.api.ISessionRepository;
import ru.ivanov.todoproject.entity.Session;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class SessionRepository extends AbstractRepository<Session> implements ISessionRepository {

    @Override
    public Session createSession(final Session session) {
        final String id = session.getId();
        final Date created = session.getCreated();
        final String userId = session.getUserId();
        final long timestamp = session.getTimestamp();
        final String signature = session.getSignature();
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.insert("createSession", session);
            sqlSession.commit();
        }
        return session;
    }

    @Override
    public Session updateSession(final Session session) {
        final Date created = session.getCreated();
        final String userId = session.getUserId();
        final long timestamp = session.getTimestamp();
        final String signature = session.getSignature();
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.update("updateSession", session);
            sqlSession.commit();
        }
        return session;
    }

    @Override
    public Session deleteSession(final Session session) {
        final String id = session.getId();
        final String query = "delete from session where id = ?";
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.delete("deleteSession", session);
            sqlSession.commit();
        }
        return session;
    }

    @Override
    public boolean deleteAllSession() {
        final String query = "delete * from session";
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.delete("deleteAllSession");
            sqlSession.commit();
            return true;
        }
    }
}
