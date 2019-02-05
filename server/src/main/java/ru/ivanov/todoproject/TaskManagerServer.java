package ru.ivanov.todoproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import ru.ivanov.todoproject.bootstrap.Bootstrap;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class TaskManagerServer {

    public static void main(String[] args) throws JsonProcessingException, NoSuchAlgorithmException, ObjectIsNotValidException, InvalidArgumentException, SQLException, ClassNotFoundException, ObjectNotFoundException {
        final Weld weld = new Weld();
        final WeldContainer weldContainer = weld.initialize();
        final Bootstrap bootstrap = weldContainer.instance().select(Bootstrap.class).get();
        bootstrap.run();
        weld.shutdown();
    }
}
