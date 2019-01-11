package ru.ivanov.todoproject;

import ru.ivanov.todoproject.controller.Controller;

public class App {

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.goToMainMenu();
    }
}
