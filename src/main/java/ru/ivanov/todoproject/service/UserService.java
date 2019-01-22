package ru.ivanov.todoproject.service;

import ru.ivanov.todoproject.api.IUserRepository;
import ru.ivanov.todoproject.api.IUserService;
import ru.ivanov.todoproject.dao.UserRepository;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.entity.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserService implements IUserService {

    private final IUserRepository userRepository = new UserRepository();

    private User activeUser;

    @Override
    public User createOrUpdateUser(final User user) {
        if (user == null) return null;
        return userRepository.merge(user);
    }

    @Override
    public User loadById(final String id) {
        if (id == null || id.isEmpty()) return null;
        return userRepository.findById(id);
    }

    @Override
    public User loadUserByLogin(final String login) {
        if (login == null || login.isEmpty()) return null;
        return userRepository.findByLogin(login);
    }

    @Override
    public List<User> loadAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void addAllUser(final List<User> users) {
        if (users == null || users.isEmpty()) return;
        userRepository.addAll(users);
    }

    @Override
    public User deleteUser(final User user) {
        if (user == null) return null;
        return userRepository.delete(user);
    }

    @Override
    public void deleteAllUser() {
        userRepository.deleteAll();
    }

    public void filterProjectsForUser(final Session session, final List<Project> projects) {
        final String userId = session.getUserId();
        final Iterator<Project> iterator = projects.iterator();
        while (iterator.hasNext()) {
            final Project project = iterator.next();
            if (!project.getUserId().equals(userId)) {
                iterator.remove();
            }
        }
    }

    public void filterTasksForUser(final Session session, final List<Task> tasks) {
        final String userId = session.getUserId();
        final Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            final Task task = iterator.next();
            if (!task.getUserId().equals(userId)) {
                iterator.remove();
            }
        }
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(final User activeUser) {
        this.activeUser = activeUser;
    }

    public boolean hasUserAuthorized() {
        return activeUser != null;
    }

    public void adminInitialization() {
        User admin = loadUserByLogin("admin");
        if (admin == null) {
            admin = new User();
            admin.setLogin("admin");
            admin.setPassword("admin");
            createOrUpdateUser(admin);
        }
    }
}
