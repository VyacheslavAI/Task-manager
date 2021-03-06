package ru.ivanov.todoproject.util;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.dto.Domain;

import javax.inject.Singleton;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.ivanov.todoproject.util.ConsoleHelper.print;

@Singleton
public class Serializer {

    private Domain saveDataToDomain(final ServiceLocator serviceLocator) {
        final Domain domain = new Domain();
        domain.setProjects(serviceLocator.getProjectService().findAllProject());
        domain.setTasks(serviceLocator.getTaskService().findAllTask());
        domain.setUsers(serviceLocator.getUserService().loadAllUser());
        return domain;
    }

    private void loadDataFromDomain(final Domain domain, final ServiceLocator serviceLocator) {
        serviceLocator.getUserService().addAllUser(domain.getUsers());
        serviceLocator.getProjectService().addAllProject(domain.getProjects());
        serviceLocator.getTaskService().addAllTask(domain.getTasks());
    }

    public void saveApplicationDataInBinary(final ServiceLocator serviceLocator) {
        try (final OutputStream outputStream = Files.newOutputStream(Paths.get("data.bin"));
             final ObjectOutput objectOutput = new ObjectOutputStream(outputStream)) {
            final Domain domain = saveDataToDomain(serviceLocator);
            objectOutput.writeObject(domain);
            print("Saving in binary file was successful");
        } catch (Throwable e) {
            print("Saving from binary file failed");
        }
    }

    public void loadApplicationDataFromBinary(final ServiceLocator serviceLocator) {
        try (final InputStream inputStream = Files.newInputStream(Paths.get("data.bin"));
             final ObjectInput objectInput = new ObjectInputStream(inputStream)) {
            final Domain domain = (Domain) objectInput.readObject();
            loadDataFromDomain(domain, serviceLocator);
            print("Loading from binary file was successful");
        } catch (Throwable e) {
            print("Loading from binary file was failed");
        }
    }
}
