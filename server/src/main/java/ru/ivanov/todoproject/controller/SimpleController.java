package ru.ivanov.todoproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String doGet() {
        return "Hello world";
    }
}
