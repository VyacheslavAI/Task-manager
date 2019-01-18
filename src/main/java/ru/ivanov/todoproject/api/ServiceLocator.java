package ru.ivanov.todoproject.api;

public interface ServiceLocator {

    IUserService getUserService();

    IProjectService getProjectService();

    ITaskService getTaskService();

    void setUserService(IUserService userService);

    void setProjectService(IProjectService projectService);

    void setTaskService(ITaskService taskService);
}
