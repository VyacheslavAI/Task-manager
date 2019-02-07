package ru.ivanov.todoproject.repository;

import org.apache.deltaspike.data.api.*;
import ru.ivanov.todoproject.entity.Session;

@Repository
public interface ISessionRepository extends EntityRepository<Session, String> {

    @Modifying
    @Query(value = "delete from Session s", singleResult = SingleResultType.ANY)
    boolean deleteAllSession();
}
