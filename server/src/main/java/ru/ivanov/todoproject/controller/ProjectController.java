package ru.ivanov.todoproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ServiceLocator serviceLocator;

    @GetMapping("/add")
    public String createPage(@CookieValue(value = "cookie", defaultValue = "no") final String cookie,
                             final Model model) throws InvalidArgumentException {
        if ("no".equals(cookie)) return "error";
        final String userId = cookie;
        final List<Project> projectList = serviceLocator.getProjectService().findAllUserProject(userId);
        model.addAttribute("projectList", projectList);
        return "project-create";
    }

    @GetMapping("/list")
    public String listPage(@CookieValue(value = "cookie", defaultValue = "no") final String cookie,
                           final Model model) throws InvalidArgumentException {
        if ("no".equals(cookie)) return "error";
        final String userId = cookie;
        final List<Project> projectList = serviceLocator.getProjectService().findAllUserProject(userId);
        model.addAttribute("projectlist", projectList);
        return "project-list";
    }

    @GetMapping("/update/{id}")
    public String updatePage(@CookieValue(value = "cookie", defaultValue = "no") final String cookie,
                             @PathVariable("id") final String projectId, final Model model) throws InvalidArgumentException, ObjectNotFoundException {
        if ("no".equals(cookie)) return "error";
        final String userId = cookie;
        final Project project = serviceLocator.getProjectService().findProjectById(userId, projectId);
        final List<Project> projectList = serviceLocator.getProjectService().findAllUserProject(userId);
        model.addAttribute("project", project);
        model.addAttribute("projectList", projectList);
        return "project-update";
    }

    @PostMapping("/create")
    public String createProject(@CookieValue(value = "cookie", defaultValue = "no") final String cookie,
                                final HttpServletRequest request) throws ObjectIsNotValidException, InvalidArgumentException {
        if ("no".equals(cookie)) return "error";
        final String userId = cookie;
        final String projectName = request.getParameter("projectName");
        final Project project = new Project();
        project.setName(projectName);
        serviceLocator.getProjectService().createProject(userId, project);
        return "redirect:list";
    }

    @PostMapping("/update")
    public String updateProject(@CookieValue(value = "cookie", defaultValue = "no") final String cookie,
                              @ModelAttribute final Project project) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException {
        if ("no".equals(cookie)) return "error";
        serviceLocator.getProjectService().updateProject(project);
        return "redirect:list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@CookieValue(value = "cookie", defaultValue = "no") final String cookie,
                                @PathVariable("id") final String projectId) throws InvalidArgumentException, ObjectNotFoundException {
        if ("no".equals(cookie)) return "error";
        serviceLocator.getProjectService().deleteProject(projectId);
        return "redirect:http://localhost:8080/project/list";
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}