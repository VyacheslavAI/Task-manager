package ru.ivanov.todoproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MyController {

    @GetMapping("/")
    public String index(final Model model, final Principal principal) {
        model.addAttribute("message", "You are logged in as " + principal.getName());
        return "index";
    }
}
