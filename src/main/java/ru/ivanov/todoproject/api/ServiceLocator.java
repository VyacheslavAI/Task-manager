package ru.ivanov.todoproject.api;

public interface ServiceLocator {

    IUserService getUserService();

    IProjectService getProjectService();

    ITaskService getTaskService();

    ISessionService getSessionService();
}
