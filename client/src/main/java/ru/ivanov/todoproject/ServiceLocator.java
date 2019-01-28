package ru.ivanov.todoproject;

import ru.ivanov.todoproject.api.IProjectSOAPEndpoint;
import ru.ivanov.todoproject.api.ISessionSOAPEndpoint;
import ru.ivanov.todoproject.api.ITaskSOAPEndpoint;
import ru.ivanov.todoproject.api.IUserSOAPEndpoint;

public interface ServiceLocator {
    IProjectSOAPEndpoint getProjectSOAPEndpoint();

    void setProjectSOAPEndpoint(IProjectSOAPEndpoint projectSOAPEndpoint);

    ITaskSOAPEndpoint getTaskSOAPEndpoint();

    void setTaskSOAPEndpoint(ITaskSOAPEndpoint taskSOAPEndpoint);

    IUserSOAPEndpoint getUserSOAPEndpoint();

    void setUserSOAPEndpoint(IUserSOAPEndpoint userSOAPEndpoint);

    ISessionSOAPEndpoint getSessionSOAPEndpoint();

    void setSessionSOAPEndpoint(ISessionSOAPEndpoint sessionSOAPEndpoint);
}
