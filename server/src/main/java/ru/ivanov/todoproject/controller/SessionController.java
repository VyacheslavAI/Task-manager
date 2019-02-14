package ru.ivanov.todoproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.security.SecurityServerManager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

@Controller
public class SessionController {

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private SecurityServerManager securityServerManager;

    @GetMapping(value = {"/", "/login"})
    public String loginPage() {
        return "login";
    }

    @PostMapping(value = "/authentication")
    public String authentication(final HttpServletRequest request, final HttpServletResponse response) throws NoSuchAlgorithmException, ObjectNotFoundException, InvalidArgumentException {
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");
        final User user = serviceLocator.getUserService().loadUserByLogin(login);
        final String userPasswordHash = user.getPasswordHash();
        final String transferredPasswordHash = securityServerManager.getPasswordHash(password);
        if (!userPasswordHash.equals(transferredPasswordHash)) return "error";
        final Cookie cookie = new Cookie("cookie", user.getId());
        response.addCookie(cookie);
        return "redirect:project/list";
    }
}
