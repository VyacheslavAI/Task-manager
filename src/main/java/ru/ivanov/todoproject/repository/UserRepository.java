package ru.ivanov.todoproject.repository;

import ru.ivanov.todoproject.api.IUserRepository;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.validator.Validator;

import java.util.Map;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public User findByLogin(final String login) {
        if (!Validator.isArgumentsValid(login)) return null;
        for (final Map.Entry<String, User> entry : entities.entrySet()) {
            final User user = entry.getValue();
            final String userLogin = user.getLogin();
            if (userLogin.equals(login)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User findBySession(final String sessionUserId) {
        if (!Validator.isArgumentsValid(sessionUserId)) return null;
        for (final User user : entities.values()) {
            if (user.getId().equals(sessionUserId)) {
                return user;
            }
        }
        return null;
    }
}
