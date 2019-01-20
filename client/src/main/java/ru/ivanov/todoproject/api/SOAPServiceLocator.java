package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.endpoint.IProjectSOAPEndpoint;
import ru.ivanov.todoproject.endpoint.ITaskSOAPEndpoint;
import ru.ivanov.todoproject.endpoint.IUserSOAPEndpoint;

public interface SOAPServiceLocator {

    IProjectSOAPEndpoint getProjectSOAPEndpoint();

    void setProjectSOAPEndpoint(IProjectSOAPEndpoint projectSOAPEndpoint);

    ITaskSOAPEndpoint getTaskSOAPEndpoint();

    void setTaskSOAPEndpoint(ITaskSOAPEndpoint taskSOAPEndpoint);

    IUserSOAPEndpoint getUserSOAPEndpoint();

    void setUserSOAPEndpoint(IUserSOAPEndpoint userSOAPEndpoint);
}
