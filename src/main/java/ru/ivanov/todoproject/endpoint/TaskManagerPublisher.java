package ru.ivanov.todoproject.endpoint;

import javax.xml.ws.Endpoint;

public class TaskManagerPublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost/8080/project", new ProjectSOAPEndpoint());
        Endpoint.publish("http://localhost/8080/task", new TaskSOAPEndpoint());
        Endpoint.publish("http://localhost/8080/user", new UserSOAPEndpoint());
    }
}
