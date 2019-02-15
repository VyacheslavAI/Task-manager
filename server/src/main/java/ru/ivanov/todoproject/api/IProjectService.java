package ru.ivanov.todoproject.api;

import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

public interface IProjectService {

    Project createProject(String userId, Project project) throws ObjectIsNotValidException, InvalidArgumentException;

    Project updateProject(Project project) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException;

    boolean addAllProject(List<Project> projects);

    Project findProjectById(String userId, String projectId) throws ObjectNotFoundException, InvalidArgumentException;

    Project findProjectByName(String userId, String name) throws InvalidArgumentException, ObjectNotFoundException;

    List<Project> findAllUserProject(String userId) throws InvalidArgumentException;

    List<Project> findAllProject();

    boolean deleteProject(String projectId) throws InvalidArgumentException, ObjectNotFoundException;

    boolean deleteAllProject();
}
