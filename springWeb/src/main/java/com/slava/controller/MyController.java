package com.slava.controller;

import com.slava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/home"})
public class MyController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/first", "/third"}, method = RequestMethod.GET, params = "!myParam")
//    @GetMapping
    @ResponseBody
    public String home() {
        return userRepository.getUser().getName();
    }

//    @RequestMapping(value = "/second", method = RequestMethod.POST)
    @PostMapping
    public String home2() {
        return "home";
    }
}
