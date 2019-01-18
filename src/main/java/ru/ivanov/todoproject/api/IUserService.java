package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.entity.User;

import java.util.List;

public interface IUserService extends IService {

    User createOrUpdateUser(final User user);

    void addAllUser(final List<User> users);

    User loadById(final String id);

    User loadUserByLogin(final String login);

    List<User> loadAllUser();

    User deleteUser(final User user);

    void deleteAllUser();

    boolean hasUserAuthorized();

    void filterProjectsForActiveUser(final List<Project> projects);

    void filterTasksForActiveUser(final List<Task> tasks);

    void setActiveUser(User user);

    User getActiveUser();

    void adminInitialization();
}
