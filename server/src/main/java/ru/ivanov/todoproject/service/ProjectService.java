package ru.ivanov.todoproject.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.ivanov.todoproject.api.IProjectRepository;
import ru.ivanov.todoproject.api.IProjectService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.validator.Validator;

import java.util.Collections;
import java.util.List;


public class ProjectService implements IProjectService {

    private IProjectRepository projectRepository;

    private ServiceLocator serviceLocator;

    private Validator validator;

    private SessionFactory sessionFactory;

    @Override
    public Project createProject(final String userId, final Project project) throws ObjectIsNotValidException, InvalidArgumentException {
        if (!validator.isProjectValid(project)) throw new ObjectIsNotValidException();
        if (!Validator.isArgumentsValid(userId)) throw new InvalidArgumentException();
//        project.setUserId(userId);
//                return projectRepository.createProject(project);

        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            project.setUserId(userId);
            session.save(project);
            session.getTransaction().commit();
        }
        return project;
    }

    @Override
    public Project updateProject(final String userId, final Project project) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException {
        if (!validator.isProjectValid(project)) throw new ObjectIsNotValidException();
        if (!Validator.isArgumentsValid(userId)) throw new InvalidArgumentException();
//        final Project persistentProject = projectRepository.findProjectById(userId, project.getId());
//        if (persistentProject == null) throw new ObjectNotFoundException();
//        return projectRepository.updateProject(project);

        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            project.setUserId(userId);
            session.update(project);
            session.getTransaction().commit();
        }
        return project;
    }

    @Override
    public boolean addAllProject(final List<Project> projects) {
        if (projects == null || projects.isEmpty()) return false;
        projects.removeAll(Collections.singleton(null));
//        return projectRepository.addAll(projects);

        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            for (final Project project : projects) {
                session.save(project);
            }
            session.getTransaction().commit();
        }
        return true;
    }

    @Override
    public Project loadUserProjectById(final String userId, final String projectId) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(userId, projectId)) throw new InvalidArgumentException();
//        final Project persistentProject = projectRepository.findProjectById(userId, projectId);
//        if (persistentProject == null) throw new ObjectNotFoundException();
//        return persistentProject;

        try (final Session session = sessionFactory.openSession()) {
            final Project project = session.get(Project.class, projectId);
            if (project == null) throw new ObjectNotFoundException();
            return project;
        }
    }

    @Override
    public Project loadUserProjectByName(final String userId, final String projectName) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(userId, projectName)) throw new InvalidArgumentException();
//        final Project project = projectRepository.findProjectByName(userId, projectName);
//        if (project == null) throw new ObjectNotFoundException();
//        return project;

        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            final Query query = session.createQuery("from Project where name = :projectName and userId = :userId");
            query.setParameter("projectName", projectName);
            query.setParameter("userId", userId);
            final Project project = (Project) query.uniqueResult();
            session.getTransaction().commit();
            if (project == null) throw new ObjectNotFoundException();
            return project;
        }
    }

    @Override
    public List<Project> loadAllUserProject(final String userId) throws InvalidArgumentException {
        if (!Validator.isArgumentsValid(userId)) throw new InvalidArgumentException();
//        return projectRepository.findAllProjectByUserId(userId);

        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            final Query query = session.createQuery("from Project where userId = :userId");
            query.setParameter("userId", userId);
            final List<Project> projects = (List<Project>) query.getResultList();
            session.getTransaction().commit();
            return projects;
        }
    }

    @Override
    public List<Project> loadAllProject() {
//        return projectRepository.findAllProject();

        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            final Query query = session.createQuery("from Project");
            final List<Project> projects = query.getResultList();
            session.getTransaction().commit();
            return projects;
        }
    }

    @Override
    public Project deleteProject(final String userId, final String projectName) throws ObjectNotFoundException, InvalidArgumentException {
        if (!Validator.isArgumentsValid(userId, projectName)) throw new InvalidArgumentException();
//        final Project project = projectRepository.findProjectByName(userId, projectName);
//        if (project == null) throw new ObjectNotFoundException();
//        return projectRepository.deleteProject(project);

        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            final Query query = session.createQuery("from Project where userId = :userId and name = :projectName");
            query.setParameter("userId", userId);
            query.setParameter("projectName", projectName);
            final Project project = (Project) query.uniqueResult();
            session.delete(project);
            session.getTransaction().commit();
            if (project == null) throw new ObjectNotFoundException();
            return project;
        }
    }

    @Override
    public boolean deleteAllProject() {
//        return projectRepository.deleteAllProject();
        return false;
    }

    @Override
    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void setProjectRepository(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}