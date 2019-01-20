package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface IUserSOAPEndpoint {

    @WebMethod
    User createUser(User user);

    @WebMethod
    User readUser(String login);

    @WebMethod
    User updateUser(User user);

    @WebMethod
    User deleteUser(User user);

    @WebMethod
    List<User> showUsers();

    @WebMethod
    User getActiveUser();
}
