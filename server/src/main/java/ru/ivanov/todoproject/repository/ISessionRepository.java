package ru.ivanov.todoproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivanov.todoproject.entity.Session;

@Repository
public interface ISessionRepository extends JpaRepository<Session, String> {

}
