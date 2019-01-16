package ru.ivanov.todoproject.command;

import ru.ivanov.todoproject.controller.Controller;

public interface Command {

    void execute(Controller controller);
}
