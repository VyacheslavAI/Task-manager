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
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ServiceLocator serviceLocator;

    @GetMapping("/menuproject")
    public String menuPage() {
        return "projectmenu";
    }

    @GetMapping("/createproject")
    public String createPage() {
        return "project-create";
    }

    @GetMapping("/listproject")
    public String listPage() {
        return "redirect:list";
    }

    @GetMapping("/updateproject")
    public String updatePage() {
        return "project-update";
    }

    @GetMapping("/deleteproject")
    public String deletePage() {
        return "project-delete";
    }

    @PostMapping("/create")
    public String createProject(@CookieValue(value = "cookie", defaultValue = "no") final String cookie,
                                final HttpServletRequest request) {
        if ("no".equals(cookie)) return "error";
        final String userId = cookie;
        final String projectName = request.getParameter("projectName");
        final Project project = new Project();
        project.setName(projectName);
        try {
            serviceLocator.getProjectService().createProject(userId, project);
        } catch (ObjectIsNotValidException | InvalidArgumentException e) {
            return "error";
        }
        return "redirect:list";
    }

    @GetMapping("/list")
    public String showAllProject(@CookieValue(value = "cookie", defaultValue = "no") final String cookie,
                                 final Model model) {
        if ("no".equals(cookie)) return "error";
        final String userId = cookie;
        final List<Project> projectList;
        try {
            projectList = serviceLocator.getProjectService().findAllUserProject(userId);
        } catch (InvalidArgumentException e) {
            return "error";
        }
        model.addAttribute("projectlist", projectList);
        return "project-list";
    }

    @PostMapping("/update")
    public String updateProject(@CookieValue(value = "cookie", defaultValue = "no") final String cookie,
                                final HttpServletRequest request) {
        if ("no".equals(cookie)) return "error";
        final String userId = cookie;
        final String projectName = request.getParameter("projectName");
        final String newProjectName = request.getParameter("newProjectName");
        try {
            final Project project = serviceLocator.getProjectService().findProjectByName(userId, projectName);
            project.setName(newProjectName);
            serviceLocator.getProjectService().updateProject(project);
            return "redirect:list";
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/delete")
    public String deleteProject(@CookieValue(value = "cookie", defaultValue = "no") final String cookie,
                                final HttpServletRequest request) {
        if ("no".equals(cookie)) return "error";
        final String userId = cookie;
        final String projectName = request.getParameter("projectName");
        try {
            serviceLocator.getProjectService().deleteProject(userId, projectName);
        } catch (ObjectNotFoundException | InvalidArgumentException e) {
            return "error";
        }
        return "redirect:list";
    }
}
