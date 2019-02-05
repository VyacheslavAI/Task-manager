package ru.ivanov.todoproject.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.ivanov.todoproject.api.ITaskRepository;
import ru.ivanov.todoproject.api.ITaskService;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.validator.Validator;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class TaskService implements ITaskService {

    @Inject
    private ITaskRepository taskRepository;

    @Inject
    private ServiceLocator serviceLocator;

    @Inject
    private Validator validator;

    @Inject
    private SessionFactory sessionFactory;

    @Override
    public Task createTask(final String userId, final Task task) throws ObjectIsNotValidException, InvalidArgumentException {
        if (!validator.isTaskValid(task)) throw new ObjectIsNotValidException();
        if (!Validator.isArgumentsValid(userId)) throw new InvalidArgumentException();
//        task.setUserId(userId);
//        return taskRepository.createTask(task);

        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            task.setUserId(userId);
            session.save(task);
            session.getTransaction().commit();
        }
        return task;
    }

    @Override
    public Task updateTask(final String userId, final Task task) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException {
        if (!validator.isTaskValid(task)) throw new ObjectIsNotValidException();
        if (!Validator.isArgumentsValid(userId)) throw new InvalidArgumentException();
//        final Task persistentTask = taskRepository.findTaskById(userId, task.getId());
//        System.out.println(persistentTask);
//        if (persistentTask == null) throw new ObjectNotFoundException();
//        return taskRepository.updateTask(task);

        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            task.setUserId(userId);
            session.update(task);
            session.getTransaction().commit();
        }
        return task;
    }

    @Override
    public boolean addAllTask(final List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) return false;
//        tasks.removeAll(Collections.singleton(null));
//        return taskRepository.addAll(tasks);

        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            for (final Task task : tasks) {
                session.save(task);
            }
            session.getTransaction().commit();
        }
        return true;
    }

    @Override
    public Task loadTaskById(final String userId, final String taskId) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(userId, taskId)) throw new InvalidArgumentException();
//        final Task persistentTask = taskRepository.findTaskById(userId, taskId);
//        if (persistentTask == null) throw new ObjectNotFoundException();
//        return persistentTask;

        try (final Session session = sessionFactory.openSession()) {
            final Task task = session.get(Task.class, taskId);
            if (task == null) throw new ObjectNotFoundException();
            return task;
        }
    }

    @Override
    public Task loadUserTaskByName(final String userId, final String name) throws InvalidArgumentException, ObjectNotFoundException {
        if (!Validator.isArgumentsValid(userId, name)) throw new InvalidArgumentException();
//        final Task task = taskRepository.findTaskByName(userId, name);
//        if (task == null) throw new ObjectNotFoundException();
//        return task;

        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            final Query query = session.createQuery("from Task where name = :name and userId = :userId");
            query.setParameter("name", name);
            query.setParameter("userId", userId);
            final Task task = (Task) query.uniqueResult();
            session.getTransaction().commit();
            if (task == null) throw new ObjectNotFoundException();
            return task;
        }
    }

    @Override
    public List<Task> loadAllUserTaskByProject(final String userId, final Project project) throws ObjectIsNotValidException, InvalidArgumentException {
        if (!validator.isProjectValid(project)) throw new ObjectIsNotValidException();
        if (!Validator.isArgumentsValid(userId)) throw new InvalidArgumentException();
//        return taskRepository.findAllProjectTask(userId, project.getId());

        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            final Query query = session.createQuery("from Task where userId = :userId and projectId = :projectId");
            query.setParameter("userId", userId);
            query.setParameter("projectId", project.getId());
            final List<Task> tasks = query.getResultList();
            session.getTransaction().commit();
            return tasks;
        }
    }

    @Override
    public Task loadTaskByProject(final String userId, final Project project, final String taskName) throws InvalidArgumentException, ObjectIsNotValidException, ObjectNotFoundException {
        if (!validator.isProjectValid(project)) throw new ObjectIsNotValidException();
        if (!Validator.isArgumentsValid(userId, taskName)) throw new InvalidArgumentException();
//        final Task task = taskRepository.findTaskByName(userId, project.getId(), taskName);
//        if (task == null) throw new ObjectNotFoundException();
//        return task;

        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            final Query query = session.createQuery("from Task where userId = :userId and projectId = :projectId and name = :taskName");
            query.setParameter("userId", userId);
            query.setParameter("projectId", project.getId());
            query.setParameter("taskName", taskName);
            final Task task = (Task) query.uniqueResult();
            session.getTransaction().commit();
            return task;
        }
    }

    @Override
    public List<Task> loadAllTask() {
//        return taskRepository.findAllTask();

        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            final Query query = session.createQuery("from Task");
            final List<Task> tasks = query.getResultList();
            session.getTransaction().commit();
            return tasks;
        }
    }

    @Override
    public List<Task> loadAllUserTask(final String userId) throws InvalidArgumentException {
        if (!Validator.isArgumentsValid(userId)) throw new InvalidArgumentException();
//        return taskRepository.findAllTask(userId);
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            final Query query = session.createQuery("from Task where userId = :userId");
            query.setParameter("userId", userId);
            final List<Task> tasks = query.getResultList();
            session.getTransaction().commit();
            return tasks;
        }
    }

    @Override
    public Task deleteTask(final String userId, final String projectId, final String taskName) throws ObjectNotFoundException, InvalidArgumentException {
        if (userId == null || userId.isEmpty()) throw new InvalidArgumentException();
        if (!Validator.isArgumentsValid(userId, taskName)) throw new InvalidArgumentException();

        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            final Query query = session.createQuery("from Task where userId = :userId and projectId = :projectId and name = :taskName");
            query.setParameter("userId", userId);
            query.setParameter("projectId", projectId);
            query.setParameter("taskName", taskName);
            final Task task = (Task) query.uniqueResult();
            session.delete(task);
            session.getTransaction().commit();
            if (task == null) throw new ObjectNotFoundException();
            return task;
        }
    }

    @Override
    public boolean deleteAllTask() {
//        return taskRepository.deleteAllTask();
        return false;
    }
}