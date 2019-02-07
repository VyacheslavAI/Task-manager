package ru.ivanov.todoproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivanov.todoproject.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {

    User updateUser(String id);

    User findByLogin(final String login);

    User findBySession(String sessionId);

    boolean deleteAllUser();
}
