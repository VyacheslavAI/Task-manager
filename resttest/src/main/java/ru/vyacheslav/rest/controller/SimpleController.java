package ru.vyacheslav.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vyacheslav.rest.entity.User;

@RestController
public class SimpleController {

    @RequestMapping("/test")
    public User getUser() {
        User user = new User();
        user.setName("Slava");
        user.setAge(25);
        return user;
    }
}
