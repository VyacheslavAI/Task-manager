package ru.ivanov.todoproject;

import ru.ivanov.todoproject.controller.Controller;

import java.io.IOException;
import java.text.ParseException;

public class App {

    public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException {
        Controller controller = new Controller();
        controller.goToMainMenu();
    }
}
