package ru.ivanov.todoproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private ServiceLocator serviceLocator;

    @GetMapping("/add/{projectId}")
    public String createPage(@CookieValue(value = "cookie", defaultValue = "no") final String cookie,
                             @PathVariable("projectId") final String projectId, final Model model) throws ObjectIsNotValidException, InvalidArgumentException, ObjectNotFoundException {
        if ("no".equals(cookie)) return "error";
        final String userId = cookie;
        final Project project = serviceLocator.getProjectService().findProjectById(userId, projectId);
        final List<Task> taskList = serviceLocator.getTaskService().findAllTaskByProject(userId, projectId);
        model.addAttribute("taskList", taskList);
        model.addAttribute("project", project);
        return "task-create";
    }

    @GetMapping("/list/{id}")
    public String listPage(@CookieValue(value = "cookie", defaultValue = "no") final String cookie,
                           @PathVariable("id") final String projectId, final Model model) throws ObjectIsNotValidException, InvalidArgumentException, ObjectNotFoundException {
        if ("no".equals(cookie)) return "error";
        final String userId = cookie;
        final Project project = serviceLocator.getProjectService().findProjectById(userId, projectId);
        final List<Task> taskList = serviceLocator.getTaskService().findAllTaskByProject(userId, projectId);
        model.addAttribute("taskList", taskList);
        model.addAttribute("project", project);
        return "task-list";
    }

    @GetMapping("/update/{projectId}/{taskId}")
    public String updatePage(@CookieValue(value = "cookie", defaultValue = "no") final String cookie,
                             @PathVariable("projectId") final String projectId, @PathVariable("taskId") final String taskId, final Model model) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException {
        if ("no".equals(cookie)) return "error";
        final String userId = cookie;
        final Project project = serviceLocator.getProjectService().findProjectById(userId, projectId);
        final Task task = serviceLocator.getTaskService().findTaskByProject(userId, projectId, taskId);
        final List<Task> taskList = serviceLocator.getTaskService().findAllTaskByProject(userId, projectId);
        model.addAttribute("taskList", taskList);
        model.addAttribute("task", task);
        model.addAttribute("project", project);
        return "task-update";
    }

    @PostMapping("/create")
    public String createTask(@CookieValue(value = "cookie", defaultValue = "no") final String cookie,
                             final HttpServletRequest request) throws ObjectNotFoundException, InvalidArgumentException, ObjectIsNotValidException {
        if ("no".equals(cookie)) return "error";
        final String userId = cookie;
        final String projectId = request.getParameter("projectId");
        final String taskName = request.getParameter("taskName");
        final Task task = new Task();
        task.setProjectId(projectId);
        task.setName(taskName);
        serviceLocator.getTaskService().createTask(userId, task);
        return "redirect:list/" + projectId;
    }

    @PostMapping("/update")
    public String updateTask(@CookieValue(value = "cookie", defaultValue = "no") final String cookie,
                             @ModelAttribute final Task task) throws ObjectNotFoundException, InvalidArgumentException, ObjectIsNotValidException {
        if ("no".equals(cookie)) return "error";
        final String userId = cookie;
        final String projectId = task.getProjectId();
        serviceLocator.getTaskService().updateTask(task);
        return "redirect:list/" + projectId;
    }

    @GetMapping("/delete/{projectId}/{taskId}")
    public String deleteTask(@CookieValue(value = "cookie", defaultValue = "no") final String cookie,
                             @PathVariable("projectId") final String projectId, @PathVariable("taskId") final String taskId) throws ObjectNotFoundException, InvalidArgumentException {
        if ("no".equals(cookie)) return "error";
        serviceLocator.getTaskService().deleteTask(taskId);
        return "redirect:http://localhost:8080/task/list/" + projectId;
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}