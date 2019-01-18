package ru.ivanov.todoproject.endpoint;

import ru.ivanov.todoproject.api.ClientInterface;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class ClientServiceRemote implements ClientInterface {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/hello", new ClientServiceRemote());
    }

    @Override
    @WebMethod
    public Project createProject() {
        return null;
    }

    @Override
    @WebMethod
    public Project updateProject() {
        return null;
    }

    @Override
    @WebMethod
    public Project readProject() {
        return null;
    }

    @Override
    @WebMethod
    public Project deleteProject() {
        return null;
    }

    @Override
    @WebMethod
    public Task createTask() {
        return null;
    }

    @Override
    @WebMethod
    public Task updateTask() {
        return null;
    }

    @Override
    @WebMethod
    public Task readTask() {
        return null;
    }

    @Override
    @WebMethod
    public Task deleteTask() {
        return null;
    }

    @Override
    @WebMethod
    public User createUser() {
        return null;
    }

    @Override
    @WebMethod
    public User updateUser() {
        return null;
    }

    @Override
    @WebMethod
    public User readUser() {
        return null;
    }

    @Override
    @WebMethod
    public User deleteUser() {
        return null;
    }
}
