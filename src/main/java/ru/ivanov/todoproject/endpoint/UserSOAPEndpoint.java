package ru.ivanov.todoproject.endpoint;

import ru.ivanov.todoproject.api.IUserSOAPEndpoint;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.User;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.ivanov.todoproject.api.IUserSOAPEndpoint")
public class UserSOAPEndpoint implements IUserSOAPEndpoint {

    private ServiceLocator serviceLocator;

    @Override
    public User createUser(final User user) {
        return serviceLocator.getUserService().createOrUpdateUser(user);
    }

    @Override
    public User readUser(final String login) {
        return serviceLocator.getUserService().loadUserByLogin(login);
    }

    @Override
    public User updateUser(final User user) {
        return serviceLocator.getUserService().createOrUpdateUser(user);
    }

    @Override
    public User deleteUser(final User user) {
        return serviceLocator.getUserService().deleteUser(user);
    }

    @Override
    public List<User> showUsers() {
        return serviceLocator.getUserService().loadAllUser();
    }

    @Override
    public User getActiveUser() {
        return serviceLocator.getUserService().getActiveUser();
    }
}
