package ru.ivanov.todoproject;

import ru.ivanov.todoproject.controller.Controller;

public class TaskManager {

    public static void main(String[] args) {
        final Controller controller = new Controller();
        controller.run();
    }
}
