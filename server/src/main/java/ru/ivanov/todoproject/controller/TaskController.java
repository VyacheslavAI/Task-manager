package ru.ivanov.todoproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.entity.Task;
import ru.ivanov.todoproject.exception.InvalidArgumentException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private ServiceLocator serviceLocator;

    @GetMapping("/menutask")
    public String menuPage() {
        return "taskmenu";
    }

    @GetMapping("/createtask")
    public String createPage() {
        return "task-create";
    }

    @GetMapping("/listtask")
    public String listPage() {
        return "redirect:tlist";
    }

    @GetMapping("/updatetask")
    public String updatePage() {
        return "task-update";
    }

    @GetMapping("/deletetask")
    public String deletePage() {
        return "task-delete";
    }

    @PostMapping("/create")
    public String createProject(@CookieValue(value = "cookie", defaultValue = "no") final String cookie,
                                final HttpServletRequest request) {
        if ("no".equals(cookie)) return "error";
        final String userId = cookie;
        final String projectName = request.getParameter("projectName");
        final String taskName = request.getParameter("taskName");
        try {
            final Project project = serviceLocator.getProjectService().findProjectByName(userId, projectName);
            final Task task = new Task();
            task.setProjectId(project.getId());
            task.setName(taskName);
            serviceLocator.getTaskService().createTask(userId, task);
            return "redirect:tlist";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/tlist")
    public String showAllProject(@CookieValue(value = "cookie", defaultValue = "no") final String cookie,
                                 final Model model) {
        if ("no".equals(cookie)) return "error";
        final String userId = cookie;
        final List<Task> taskList;
        try {
            taskList = serviceLocator.getTaskService().findAllUserTask(userId);
        } catch (InvalidArgumentException e) {
            return "error";
        }
        model.addAttribute("taskList", taskList);
        return "task-list";
    }

    @PostMapping("/update")
    public String updateProject(@CookieValue(value = "cookie", defaultValue = "no") final String cookie,
                                final HttpServletRequest request) {
        if ("no".equals(cookie)) return "error";
        final String userId = cookie;
        try {
            final String projectName = request.getParameter("projectName");
            final Project project = serviceLocator.getProjectService().findProjectByName(userId, projectName);
            final String taskName = request.getParameter("taskName");
            final String newTaskName = request.getParameter("newTaskName");
            final Task task = serviceLocator.getTaskService().findTaskByProject(userId, project, taskName);
            task.setName(newTaskName);
            serviceLocator.getTaskService().updateTask(task);
            return "redirect:tlist";
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/delete")
    public String deleteProject(@CookieValue(value = "cookie", defaultValue = "no") final String cookie,
                                final HttpServletRequest request) {
        try {
            if ("no".equals(cookie)) return "error";
            final String userId = cookie;
            final String projectName = request.getParameter("projectName");
            final String taskName = request.getParameter("taskName");
            final Project project = serviceLocator.getProjectService().findProjectByName(userId, projectName);
            serviceLocator.getTaskService().deleteTask(userId, project.getId(), taskName);
            return "redirect:tlist";
        } catch (Exception e) {
            return "error";
        }
    }
}
