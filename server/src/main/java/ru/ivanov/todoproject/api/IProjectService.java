package ru.ivanov.todoproject.api;

import org.hibernate.SessionFactory;
import ru.ivanov.todoproject.entity.Project;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.exception.ObjectNotFoundException;
import ru.ivanov.todoproject.validator.Validator;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

@XmlJavaTypeAdapter(ServiceLocator.ProjectServiceAdapter.class)
public interface IProjectService {

    Project createProject(String userId, Project project) throws ObjectIsNotValidException, InvalidArgumentException;

    Project updateProject(String userId, Project project) throws ObjectIsNotValidException, ObjectNotFoundException, InvalidArgumentException;

    boolean addAllProject(List<Project> projects);

    Project loadUserProjectById(String userId, String projectId) throws ObjectNotFoundException, InvalidArgumentException;

    Project loadUserProjectByName(String userId, String name) throws InvalidArgumentException, ObjectNotFoundException;

    List<Project> loadAllUserProject(String userId) throws InvalidArgumentException;

    List<Project> loadAllProject();

    Project deleteProject(String userId, String projectName) throws ObjectNotFoundException, InvalidArgumentException;

    boolean deleteAllProject();

    void setServiceLocator(ServiceLocator serviceLocator);

    void setValidator(Validator validator);

    void setProjectRepository(IProjectRepository projectRepository);

    void setSessionFactory(SessionFactory sessionFactory);
}
