package ru.ivanov.todoproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ivanov.todoproject.api.ServiceLocator;
import ru.ivanov.todoproject.entity.Project;

import java.util.List;

@Controller
@RequestMapping("/project/")
public class ProjectController {

    @Autowired
    private ServiceLocator serviceLocator;

    @GetMapping("/create")
    public String createProject(final Model model) {
        return "project-create";
    }

    @GetMapping("/list")
    public String showAllProject(final Model model) {
        final List<Project> projectList = serviceLocator.getProjectService().findAllProject();
        model.addAttribute("list", projectList);
        return "project-list";
    }

    @GetMapping("/update")
    public String updateProject(final Model model) {
        return "project-update";
    }

    @GetMapping("/delete")
    public String deleteProject(final Model model) {
        return "project-delete";
    }
}
