package ru.ivanov.todoproject.api;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-01-20T21:58:31.252+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://api.todoproject.ivanov.ru/", name = "IUserSOAPEndpoint")
@XmlSeeAlso({ObjectFactory.class})
public interface IUserSOAPEndpoint {

    @WebMethod
    @Action(input = "http://api.todoproject.ivanov.ru/IUserSOAPEndpoint/readUserRequest", output = "http://api.todoproject.ivanov.ru/IUserSOAPEndpoint/readUserResponse")
    @RequestWrapper(localName = "readUser", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "ReadUser")
    @ResponseWrapper(localName = "readUserResponse", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "ReadUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.ivanov.todoproject.api.User readUser(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://api.todoproject.ivanov.ru/IUserSOAPEndpoint/showUsersRequest", output = "http://api.todoproject.ivanov.ru/IUserSOAPEndpoint/showUsersResponse")
    @RequestWrapper(localName = "showUsers", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "ShowUsers")
    @ResponseWrapper(localName = "showUsersResponse", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "ShowUsersResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.ivanov.todoproject.api.User> showUsers();

    @WebMethod
    @Action(input = "http://api.todoproject.ivanov.ru/IUserSOAPEndpoint/createUserRequest", output = "http://api.todoproject.ivanov.ru/IUserSOAPEndpoint/createUserResponse")
    @RequestWrapper(localName = "createUser", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "CreateUser")
    @ResponseWrapper(localName = "createUserResponse", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "CreateUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.ivanov.todoproject.api.User createUser(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.ivanov.todoproject.api.User arg0
    );

    @WebMethod
    @Action(input = "http://api.todoproject.ivanov.ru/IUserSOAPEndpoint/updateUserRequest", output = "http://api.todoproject.ivanov.ru/IUserSOAPEndpoint/updateUserResponse")
    @RequestWrapper(localName = "updateUser", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "UpdateUser")
    @ResponseWrapper(localName = "updateUserResponse", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "UpdateUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.ivanov.todoproject.api.User updateUser(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.ivanov.todoproject.api.User arg0
    );

    @WebMethod
    @Action(input = "http://api.todoproject.ivanov.ru/IUserSOAPEndpoint/deleteUserRequest", output = "http://api.todoproject.ivanov.ru/IUserSOAPEndpoint/deleteUserResponse")
    @RequestWrapper(localName = "deleteUser", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "DeleteUser")
    @ResponseWrapper(localName = "deleteUserResponse", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "DeleteUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.ivanov.todoproject.api.User deleteUser(
        @WebParam(name = "arg0", targetNamespace = "")
        ru.ivanov.todoproject.api.User arg0
    );

    @WebMethod
    @Action(input = "http://api.todoproject.ivanov.ru/IUserSOAPEndpoint/getActiveUserRequest", output = "http://api.todoproject.ivanov.ru/IUserSOAPEndpoint/getActiveUserResponse")
    @RequestWrapper(localName = "getActiveUser", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "GetActiveUser")
    @ResponseWrapper(localName = "getActiveUserResponse", targetNamespace = "http://api.todoproject.ivanov.ru/", className = "GetActiveUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.ivanov.todoproject.api.User getActiveUser();
}
