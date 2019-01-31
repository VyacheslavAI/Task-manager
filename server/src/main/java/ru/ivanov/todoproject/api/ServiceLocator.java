package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.service.ProjectService;
import ru.ivanov.todoproject.service.SessionService;
import ru.ivanov.todoproject.service.TaskService;
import ru.ivanov.todoproject.service.UserService;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(ServiceLocator.BootstrapAdapter.class)
public interface ServiceLocator {

    IUserService getUserService();

    IProjectService getProjectService();

    ITaskService getTaskService();

    ISessionService getSessionService();

    class BootstrapAdapter extends XmlAdapter<Bootstrap, ServiceLocator> {
        @Override
        public ServiceLocator unmarshal(Bootstrap v) throws Exception {
            return v;
        }

        @Override
        public Bootstrap marshal(ServiceLocator v) throws Exception {
            return (Bootstrap) v;
        }
    }

    class ProjectServiceAdapter extends XmlAdapter<ProjectService, IProjectService> {
        @Override
        public IProjectService unmarshal(ProjectService v) throws Exception {
            return v;
        }

        @Override
        public ProjectService marshal(IProjectService v) throws Exception {
            return (ProjectService) v;
        }
    }

    class TaskServiceAdapter extends XmlAdapter<TaskService, ITaskService> {
        @Override
        public ITaskService unmarshal(TaskService v) throws Exception {
            return v;
        }

        @Override
        public TaskService marshal(ITaskService v) throws Exception {
            return (TaskService) v;
        }
    }

    class UserServiceAdapter extends XmlAdapter<UserService, IUserService> {
        @Override
        public IUserService unmarshal(UserService v) throws Exception {
            return v;
        }

        @Override
        public UserService marshal(IUserService v) throws Exception {
            return (UserService) v;
        }
    }

    class SessionServiceAdapter extends XmlAdapter<SessionService, ISessionService> {
        @Override
        public ISessionService unmarshal(SessionService v) throws Exception {
            return v;
        }

        @Override
        public SessionService marshal(ISessionService v) throws Exception {
            return (SessionService) v;
        }
    }
}

