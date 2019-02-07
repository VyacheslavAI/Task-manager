package ru.ivanov.todoproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class TaskManagerServer {

    public static void main(String[] args) throws JsonProcessingException, NoSuchAlgorithmException, ObjectIsNotValidException, InvalidArgumentException, SQLException, ClassNotFoundException, ObjectNotFoundException {
        final ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("config.xml");
        applicationContext.refresh();
        final Bootstrap bootstrap = applicationContext.getBean(Bootstrap.class);
        bootstrap.run();
    }
}
