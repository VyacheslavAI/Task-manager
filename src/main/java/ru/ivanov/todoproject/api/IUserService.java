package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.entity.User;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public interface IUserService extends IService {

    User createOrUpdateUser(final User user);

    void addAllUser(final List<User> users);

    User loadById(final String id);

    User loadUserByLogin(final String login);

    List<User> loadAllUser();

    User deleteUser(final User user);

    void deleteAllUser();

    boolean hasUserAuthorized();

    void setActiveUser(User user);

    User getActiveUser();

    void adminInitialization();

    void filterProjectsForUser(final List<Project> projects);

    void filterTasksForUser(final List<Task> tasks);

    Map<User, List<Session>> getAuthorizedUsers();

    void setAuthorizedUsers(Map<User, List<Session>> authorizedUsers);

    User getUserBySession(final Session session);
}
