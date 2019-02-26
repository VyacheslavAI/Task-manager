package ru.vyacheslav.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vyacheslav.rest.dto.Result;
import ru.vyacheslav.rest.entity.User;

import java.util.List;

@RestController
public class SimpleController {

    @RequestMapping("/test")
    public User getUser() {
        final User user = new User();
        user.setName("Slava");
        user.setAge(25);
        return user;
    }

    @RequestMapping("/res")
    public Result getResult() {
        final Result result = new Result();
        result.getList().add("one");
        result.getList().add("two");
        return result;
    }

    @PostMapping("/create")
    public User getUserPost(@RequestBody Result result) {
        final User user = new User();
        final List<String> list = result.getList();
        final String str1 = list.get(0);
        final String srt2 = list.get(1);
        System.out.println(str1);
        System.out.println(srt2);
        user.setAge(20);
        return user;
    }
}