package ru.ivanov.todoproject.repository;

import org.apache.deltaspike.data.api.*;
import ru.ivanov.todoproject.entity.User;

@Repository
public interface IUserRepository extends EntityRepository<User, String> {

    @Query(value = "from User u where u.login = ?1", singleResult = SingleResultType.ANY)
    User findByLogin(final String login);

    @Query(value = "from User u where u.login = ?1", singleResult = SingleResultType.ANY)
    User findBySession(String sessionId);

    @Modifying
    @Query(value = "delete from User u", singleResult = SingleResultType.ANY)
    boolean deleteAllUser();
}
