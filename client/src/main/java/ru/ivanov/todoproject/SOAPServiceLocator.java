package ru.ivanov.todoproject;

import ru.ivanov.todoproject.endpoint.ProjectSOAPEndpointService;
import ru.ivanov.todoproject.endpoint.TaskSOAPEndpointService;
import ru.ivanov.todoproject.endpoint.UserSOAPEndpointService;

public interface SOAPServiceLocator {

    ProjectSOAPEndpointService getProjectSOAPEndpointService();

    void setProjectSOAPEndpointService(ProjectSOAPEndpointService projectSOAPEndpoint);

    TaskSOAPEndpointService getTaskSOAPEndpointService();

    void setTaskSOAPEndpointService(TaskSOAPEndpointService taskSOAPEndpoint);

    UserSOAPEndpointService getUserSOAPEndpointService();

    void setUserSOAPEndpointService(UserSOAPEndpointService userSOAPEndpoint);
}
