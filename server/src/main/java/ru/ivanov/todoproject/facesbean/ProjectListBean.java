package ru.ivanov.todoproject.facesbean;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@ManagedBean
@ViewScoped
@Component
@URLMapping(id = "project-list", pattern = "/project-list", viewId = "view/project-list.xhtml")
public class ProjectListBean {

    @Autowired
    private ServiceLocator serviceLocator;

    private List<Project> projectList;

    public String getCookieValue() throws UnsupportedEncodingException, ObjectNotFoundException, InvalidArgumentException {
//        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//        final Cookie cookie = (Cookie) context.getRequestCookieMap().get("userId");
//        if (cookie == null) throw new RuntimeException();
//        return URLDecoder.decode(cookie.getValue(), "UTF-8");

        final UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final User user = serviceLocator.getUserService().loadUserByLogin(details.getUsername());
        return user.getId();
    }

    public void updateProjectList() throws InvalidArgumentException, UnsupportedEncodingException, ObjectNotFoundException {
        final String userId = getCookieValue();
        projectList = serviceLocator.getProjectService().findAllUserProject(userId);
    }

    public void showProjects() throws IOException, InvalidArgumentException {
        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + "/project-list");
    }

    public void createProject() throws UnsupportedEncodingException, ObjectIsNotValidException, InvalidArgumentException, ObjectNotFoundException {
        final String userId = getCookieValue();
        final Project project = new Project();
        serviceLocator.getProjectService().createProject(userId, project);
        projectList.add(project);
        final FacesMessage message = new FacesMessage("Project Created", project.getId());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void deleteProject(final String id) throws ObjectNotFoundException, InvalidArgumentException, UnsupportedEncodingException {
        serviceLocator.getProjectService().deleteProject(id);
//        updateProjectList();
        Project project = null;
        for (Project test : projectList)
            if (test.getId().equals(id))
                project = test;
        projectList.remove(project);
        final FacesMessage message = new FacesMessage("Project Deleted", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onRowEdit(final RowEditEvent event) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException {
        final Project project = (Project) event.getObject();
        serviceLocator.getProjectService().updateProject(project);
        final FacesMessage message = new FacesMessage("Project Edited", ((Project) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onRowCancel(final RowEditEvent event) {
        final FacesMessage message = new FacesMessage("Edit Cancelled", ((Project) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
}