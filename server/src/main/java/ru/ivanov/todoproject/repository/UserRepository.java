package ru.ivanov.todoproject.repository;

import org.apache.ibatis.session.SqlSession;
import ru.ivanov.todoproject.api.IUserRepository;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.validator.Validator;

import java.util.Date;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public User findByLogin(final String login) {
        if (!Validator.isArgumentsValid(login)) return null;
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectOne("findByLogin", login);
        }
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
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.insert("createUser", user);
            sqlSession.commit();
        }
        return user;
    }

    @Override
    public User updateUser(final User user) {
        final String userId = user.getId();
        final String login = user.getLogin();
        final String passwordHash = user.getPasswordHash();
        final Date created = user.getCreated();
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.update("updateUser", user);
            sqlSession.commit();
        }
        return user;
    }

    @Override
    public User deleteUser(final User user) {
        final String id = user.getId();
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.delete("deleteUser", user.getId());
            sqlSession.commit();
        }
        return user;
    }

    @Override
    public boolean deleteAllUser() {
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.delete("deleteAllUser");
            sqlSession.commit();
        }
        return true;
    }
}
