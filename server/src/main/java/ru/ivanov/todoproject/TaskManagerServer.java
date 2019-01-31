package ru.ivanov.todoproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class TaskManagerServer {

    public static void main(String[] args) throws JsonProcessingException, NoSuchAlgorithmException, ObjectIsNotValidException, InvalidArgumentException, SQLException, ClassNotFoundException {
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();
    }
}
