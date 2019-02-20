package ru.ivanov.todoproject.facesbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.security.SecurityServerManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@ManagedBean(name = "authenticationBean", eager = true)
@Component
@RequestScoped
public class AuthenticationBean {

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private SecurityServerManager securityServerManager;

    private String login;

    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public SecurityServerManager getSecurityServerManager() {
        return securityServerManager;
    }

    public void setSecurityServerManager(SecurityServerManager securityServerManager) {
        this.securityServerManager = securityServerManager;
    }

    public void authorization() throws ObjectNotFoundException, InvalidArgumentException, NoSuchAlgorithmException, IOException, ObjectIsNotValidException {
        final User user = serviceLocator.getUserService().loadUserByLogin(login);
        final String userPasswordHash = user.getPasswordHash();
        final String transferredPasswordHash = securityServerManager.getPasswordHash(password);
        if (!userPasswordHash.equals(transferredPasswordHash)) throw new RuntimeException();
        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        addCookieToResponse(user.getId());
        context.redirect(context.getRequestContextPath() + "/view/project-list.xhtml");
    }

    public void addCookieToResponse(final String cookieValue) throws UnsupportedEncodingException {
        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        final String name = "userId";
        final Map<String, Object> properties = new HashMap<>();
        properties.put("maxAge", 31536000);
        properties.put("path", "/");
        context.addResponseCookie(name, URLEncoder.encode(cookieValue, "UTF-8"), properties);
    }
}