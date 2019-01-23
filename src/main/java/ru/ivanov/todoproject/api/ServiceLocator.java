package ru.ivanov.todoproject.api;

public interface ServiceLocator {

    IUserService getUserService();

    IProjectService getProjectService();

    ITaskService getTaskService();

    ISessionService getSessionService();

    void setUserService(final IUserService userService);

    void setProjectService(final IProjectService projectService);

    void setTaskService(final ITaskService taskService);

    void setSessionService(final ISessionService ISessionService);
}
