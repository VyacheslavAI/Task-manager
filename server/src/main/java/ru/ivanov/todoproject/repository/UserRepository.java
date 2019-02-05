package ru.ivanov.todoproject.repository;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ru.ivanov.todoproject.api.IUserRepository;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.validator.Validator;

import java.sql.Connection;
import java.util.Date;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public User findByLogin(String login) {
        return null;
    }

    @Override
    public User findBySession(String sessionId) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User deleteUser(User user) {
        return null;
    }

    @Override
    public boolean deleteAllUser() {
        return false;
    }

    @Override
    public void setConnection(Connection connection) {

    }

    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {

    }
}
