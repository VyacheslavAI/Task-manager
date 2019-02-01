package ru.ivanov.todoproject.repository;

import org.apache.ibatis.session.SqlSession;
import ru.ivanov.todoproject.api.IProjectRepository;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.validator.Validator;

import java.sql.Timestamp;
import java.util.*;

public class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    @Override
    public Project createProject(final Project project) {
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            final String id = project.getId();
            final Timestamp created = new Timestamp(project.getCreated().getTime());
            final String projectName = project.getName();
            final String userId = project.getUserId();
            sqlSession.insert("createProject", project);
            sqlSession.commit();
        }
        return project;
    }

    @Override
    public Project updateProject(final Project project) {
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            final String projectId = project.getId();
            final java.sql.Date created = new java.sql.Date(project.getCreated().getTime());
            final String projectName = project.getName();
            final String userId = project.getUserId();
            sqlSession.update("updateProject", project);
            sqlSession.commit();
        }
        return project;
    }

    @Override
    public Project deleteProject(final Project project) {
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.delete("deleteProject", project);
            sqlSession.commit();
        }
        return project;
    }

    @Override
    public boolean deleteAllProject() {
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.delete("deleteAllProject");
            sqlSession.commit();
            return true;
        }
    }

    @Override
    public Project findProjectByName(final String userId, final String name) {
        if (!Validator.isArgumentsValid(userId, name)) return null;
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            final Map<String, String> parameters = new HashMap<>();
            parameters.put("userId", userId);
            parameters.put("name", name);
            return sqlSession.selectOne("findProjectByName", parameters);
        }
    }

    @Override
    public List<Project> findAllProjectByUserId(final String userId) {
        if (!Validator.isArgumentsValid(userId)) return Collections.emptyList();
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            final List<Project> result = new ArrayList<>();
            return sqlSession.selectList("findAllProjectByUserId", userId);
        }
    }

    @Override
    public List<Project> findAllProject() {
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectOne("findAllProject");
        }
    }

    @Override
    public Project findProjectById(final String userId, final String projectId) {
        if (!Validator.isArgumentsValid(userId, projectId)) return null;
        try (final SqlSession sqlSession = sqlSessionFactory.openSession()) {
            final Map<String, String> parameters = new HashMap<>();
            parameters.put("userId", userId);
            parameters.put("projectId", projectId);
            return sqlSession.selectOne("findProjectById", parameters);
        }
    }
}