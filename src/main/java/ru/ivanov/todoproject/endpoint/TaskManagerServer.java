package ru.ivanov.todoproject.endpoint;

import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.bootstrap.Bootstrap;

import javax.xml.ws.Endpoint;

public class TaskManagerServer {

    public static void main(String[] args) {
        ServiceLocator locator = new Bootstrap();
        ProjectSOAPEndpoint projectSOAPEndpoint = new ProjectSOAPEndpoint();
        TaskSOAPEndpoint taskSOAPEndpoint = new TaskSOAPEndpoint();
        UserSOAPEndpoint userSOAPEndpoint = new UserSOAPEndpoint();
        SessionSOAPEndpoint sessionSOAPEndpoint = new SessionSOAPEndpoint();

        projectSOAPEndpoint.setServiceLocator(locator);
        taskSOAPEndpoint.setServiceLocator(locator);
        userSOAPEndpoint.setServiceLocator(locator);
        sessionSOAPEndpoint.setServiceLocator(locator);

        Endpoint.publish("http://localhost/8080/project", projectSOAPEndpoint);
        Endpoint.publish("http://localhost/8080/task", taskSOAPEndpoint);
        Endpoint.publish("http://localhost/8080/user", userSOAPEndpoint);
        Endpoint.publish("http://localhost/8080/session", sessionSOAPEndpoint);
    }
}
