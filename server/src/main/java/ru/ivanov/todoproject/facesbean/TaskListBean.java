package ru.ivanov.todoproject.facesbean;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.entity.User;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@ManagedBean
@ViewScoped
@Component
@URLMapping(id = "task-list", pattern = "/task-list", viewId = "view/task-list.xhtml")
public class TaskListBean {

    @Autowired
    private ServiceLocator serviceLocator;

    private List<Task> taskList;

    private String projectId;

    public String getCookieValue() throws UnsupportedEncodingException, ObjectNotFoundException, InvalidArgumentException {
//        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//        final Cookie cookie = (Cookie) context.getRequestCookieMap().get("userId");
//        if (cookie == null) throw new RuntimeException();
//        return URLDecoder.decode(cookie.getValue(), "UTF-8");

        final UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final User user = serviceLocator.getUserService().loadUserByLogin(details.getUsername());
        return user.getId();
    }

    public void updateTaskList(final String projectId) throws InvalidArgumentException, UnsupportedEncodingException, ObjectIsNotValidException, ObjectNotFoundException {
        final String userId = getCookieValue();
        taskList = serviceLocator.getTaskService().findAllTaskByProject(userId, projectId);
    }

    public void showTasks(final String projectId) throws ObjectIsNotValidException, IOException, InvalidArgumentException, ObjectNotFoundException {
        this.projectId = projectId;
        updateTaskList(projectId);
        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + "/task-list");
    }

    public void createTask() throws UnsupportedEncodingException, ObjectIsNotValidException, InvalidArgumentException, ObjectNotFoundException {
        final String userId = getCookieValue();
        final Task task = new Task();
        serviceLocator.getTaskService().createTask(userId, projectId, task);
        taskList.add(task);
        final FacesMessage message = new FacesMessage("Task Created", task.getId());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void deleteTask(final String id) throws ObjectNotFoundException, InvalidArgumentException, UnsupportedEncodingException, ObjectIsNotValidException {
        serviceLocator.getTaskService().deleteTask(id);
        updateTaskList(projectId);
        final FacesMessage message = new FacesMessage("Task Deleted", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onRowEdit(final RowEditEvent event) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException {
        final Task task = (Task) event.getObject();
        serviceLocator.getTaskService().updateTask(task);
        final FacesMessage message = new FacesMessage("Task Edited", ((Task) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onRowCancel(final RowEditEvent event) {
        final FacesMessage message = new FacesMessage("Edit Cancelled", ((Task) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
