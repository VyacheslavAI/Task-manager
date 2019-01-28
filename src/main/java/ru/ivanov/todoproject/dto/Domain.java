package ru.ivanov.todoproject.dto;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Session;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.entity.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static ru.ivanov.todoproject.util.ConsoleHelper.print;

public class Domain implements Serializable {

    private List<Project> projects;

    private List<Task> tasks;

    private List<User> users;

    private List<Session> sessions;

    private List<Project> getProjects() {
        return projects;
    }

    private List<Task> getTasks() {
        return tasks;
    }

    private void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    private void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    private List<User> getUsers() {
        return users;
    }

    private void setUsers(List<User> users) {
        this.users = users;
    }

    private List<Session> getSessions() {
        return sessions;
    }

    private void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    private Domain() {
    }

    private static Domain saveDataToDomain(final ServiceLocator serviceLocator) {
        final Domain domain = new Domain();
        domain.setProjects(serviceLocator.getProjectService().loadAllProject());
        domain.setTasks(serviceLocator.getTaskService().loadAllTask());
        domain.setUsers(serviceLocator.getUserService().loadAllUser());
        domain.setSessions(serviceLocator.getSessionService().loadAllSession());
        return domain;
    }

    private void loadDataFromDomain(final ServiceLocator serviceLocator) {
        serviceLocator.getUserService().addAllUser(getUsers());
        serviceLocator.getProjectService().addAllProject(getProjects());
        serviceLocator.getTaskService().addAllTask(getTasks());
        serviceLocator.getSessionService().addAllSession(getSessions());
    }

    public static void saveApplicationDataInBinary(final ServiceLocator serviceLocator) {
        try (final OutputStream outputStream = Files.newOutputStream(Paths.get("data.bin"));
             final ObjectOutput objectOutput = new ObjectOutputStream(outputStream)) {
            final Domain domain = saveDataToDomain(serviceLocator);
            objectOutput.writeObject(domain);
            print("Saving in binary file was successful");
        } catch (Throwable e) {
            print("Saving from binary file failed");
        }
    }

    public static void loadApplicationDataFromBinary(final ServiceLocator serviceLocator) {
        try (final InputStream inputStream = Files.newInputStream(Paths.get("data.bin"));
             final ObjectInput objectInput = new ObjectInputStream(inputStream)) {
            final Domain domain = (Domain) objectInput.readObject();
            domain.loadDataFromDomain(serviceLocator);
            print("Loading from binary file was successful");
        } catch (Throwable e) {
            print("Loading from binary file was failed");
        }
    }
}