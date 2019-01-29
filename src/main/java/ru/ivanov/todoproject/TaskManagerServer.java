package ru.ivanov.todoproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;

import java.security.NoSuchAlgorithmException;

public class TaskManagerServer {

    public static void main(String[] args) throws JsonProcessingException, NoSuchAlgorithmException, ObjectIsNotValidException, InvalidArgumentException {
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();
    }
}
