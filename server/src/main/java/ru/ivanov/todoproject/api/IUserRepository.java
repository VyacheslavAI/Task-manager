package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.User;

public interface IUserRepository extends IRepository<User> {

    User findByLogin(final String login);

    User findBySession(String sessionId);

    User createUser(User user);

    User updateUser(User user);
}
