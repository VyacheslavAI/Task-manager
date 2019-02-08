package ru.ivanov.todoproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivanov.todoproject.entity.Project;

import java.util.List;

@Repository
public interface IProjectRepository extends JpaRepository<Project, String> {

    Project findProjectByUserIdAndId(String userId, String id);

    Project findProjectByUserIdAndName(String userId, String name);

    List<Project> findAllByUserId(String userId);
}