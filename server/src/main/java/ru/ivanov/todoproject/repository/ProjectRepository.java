package ru.ivanov.todoproject.repository;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ru.ivanov.todoproject.api.IProjectRepository;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.validator.Validator;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.*;

public class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    @Override
    public Project createProject(Project project) {
        return null;
    }

    @Override
    public Project updateProject(Project project) {
        return null;
    }

    @Override
    public Project findProjectByName(String userId, String name) {
        return null;
    }

    @Override
    public List<Project> findAllProjectByUserId(String userId) {
        return null;
    }

    @Override
    public List<Project> findAllProject() {
        return null;
    }

    @Override
    public Project findProjectById(String userId, String projectId) {
        return null;
    }

    @Override
    public Project deleteProject(Project project) {
        return null;
    }

    @Override
    public boolean deleteAllProject() {
        return false;
    }

    @Override
    public void setConnection(Connection connection) {

    }

    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {

    }
}