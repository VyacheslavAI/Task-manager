package ru.ivanov.todoproject.bootstrap;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.ivanov.todoproject.api.*;
import ru.ivanov.todoproject.config.DatabaseConfig;
import ru.ivanov.todoproject.config.MyDataSourceFactory;
import ru.ivanov.todoproject.dto.Serializer;
import ru.ivanov.todoproject.endpoint.ProjectSOAPEndpoint;
import ru.ivanov.todoproject.endpoint.SessionSOAPEndpoint;
import ru.ivanov.todoproject.endpoint.TaskSOAPEndpoint;
import ru.ivanov.todoproject.endpoint.UserSOAPEndpoint;
import ru.ivanov.todoproject.exception.InvalidArgumentException;
import ru.ivanov.todoproject.exception.ObjectIsNotValidException;
import ru.ivanov.todoproject.repository.ProjectRepository;
import ru.ivanov.todoproject.repository.SessionRepository;
import ru.ivanov.todoproject.repository.TaskRepository;
import ru.ivanov.todoproject.repository.UserRepository;
import ru.ivanov.todoproject.security.SecurityServerManager;
import ru.ivanov.todoproject.service.ProjectService;
import ru.ivanov.todoproject.service.SessionService;
import ru.ivanov.todoproject.service.TaskService;
import ru.ivanov.todoproject.service.UserService;
import ru.ivanov.todoproject.validator.Validator;

import javax.sql.DataSource;
import javax.xml.ws.Endpoint;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static ru.ivanov.todoproject.util.ConsoleHelper.print;

public class Bootstrap implements ServiceLocator {

    private IProjectRepository projectRepository = new ProjectRepository();

    private ITaskRepository taskRepository = new TaskRepository();

    private IUserRepository userRepository = new UserRepository();

    private ISessionRepository sessionRepository = new SessionRepository();

    private IProjectService projectService = new ProjectService();

    private ITaskService taskService = new TaskService();

    private IUserService userService = new UserService();

    private ISessionService sessionService = new SessionService();

    private IProjectSOAPEndpoint projectSOAPEndpoint = new ProjectSOAPEndpoint();

    private ITaskSOAPEndpoint taskSOAPEndpoint = new TaskSOAPEndpoint();

    private IUserSOAPEndpoint userSOAPEndpoint = new UserSOAPEndpoint();

    private ISessionSOAPEndpoint sessionSOAPEndpoint = new SessionSOAPEndpoint();

    private SecurityServerManager securityServerManager = new SecurityServerManager();

    private Validator validator = new Validator();

    private Serializer serializer = new Serializer();

    private DatabaseConfig databaseConfig = new DatabaseConfig();

    private Connection connection;

    private SqlSessionFactory sqlSessionFactory;

    private SessionFactory sessionFactory;

    {
        projectService.setProjectRepository(projectRepository);
        taskService.setTaskRepository(taskRepository);
        userService.setUserRepository(userRepository);
        sessionService.setSessionRepository(sessionRepository);

        projectService.setServiceLocator(this);
        taskService.setServiceLocator(this);
        userService.setServiceLocator(this);
        sessionService.setServiceLocator(this);
        projectSOAPEndpoint.setServiceLocator(this);
        taskSOAPEndpoint.setServiceLocator(this);
        userSOAPEndpoint.setServiceLocator(this);
        sessionSOAPEndpoint.setServiceLocator(this);

        userService.setSecurityServerManager(securityServerManager);
        projectSOAPEndpoint.setSecurityServerManager(securityServerManager);
        taskSOAPEndpoint.setSecurityServerManager(securityServerManager);
        userSOAPEndpoint.setSecurityServerManager(securityServerManager);
        sessionSOAPEndpoint.setSecurityServerManager(securityServerManager);

        projectService.setValidator(validator);
        taskService.setValidator(validator);
        userService.setValidator(validator);
        sessionService.setValidator(validator);
        securityServerManager.setValidator(validator);
    }

    public void run() throws JsonProcessingException, NoSuchAlgorithmException, ObjectIsNotValidException, InvalidArgumentException, SQLException, ClassNotFoundException {
        createHibernateSessionFactory();
        userInitialization();
        Endpoint.publish("http://localhost/8080/project", projectSOAPEndpoint);
        Endpoint.publish("http://localhost/8080/task", taskSOAPEndpoint);
        Endpoint.publish("http://localhost/8080/user", userSOAPEndpoint);
        Endpoint.publish("http://localhost/8080/session", sessionSOAPEndpoint);
        print("Server started successfully");
    }

    private void userInitialization() throws InvalidArgumentException, NoSuchAlgorithmException, ObjectIsNotValidException, JsonProcessingException {
        userService.userInitialize("admin", "admin");
        userService.userInitialize("root", "root");
        print("Users initialized successfully");
    }

    private void createConnection() throws SQLException, ClassNotFoundException {
        final String connectionUrl = databaseConfig.getConnectionUrl();
        final String userName = databaseConfig.getUserName();
        final String password = databaseConfig.getPassword();
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(connectionUrl, userName, password);
        projectRepository.setConnection(connection);
        taskRepository.setConnection(connection);
        userRepository.setConnection(connection);
        sessionRepository.setConnection(connection);
        print("Connection created successfully");
    }

    private void createSqlSessionFactory() {
        final Properties properties = new Properties();
        properties.setProperty("url", databaseConfig.getConnectionUrl());
        properties.setProperty("username", databaseConfig.getUserName());
        properties.setProperty("password", databaseConfig.getPassword());
        properties.setProperty("driver", databaseConfig.getDriver());
        final MyDataSourceFactory dataSourceFactory = new MyDataSourceFactory();
        dataSourceFactory.setProperties(properties);
        final DataSource dataSource = dataSourceFactory.getDataSource();
        final TransactionFactory transactionFactory = new JdbcTransactionFactory();
        final Environment environment = new Environment("development", transactionFactory, dataSource);
        final Configuration config = new Configuration(environment);
        config.addMapper(IProjectRepository.class);
        config.addMapper(ITaskRepository.class);
        config.addMapper(ISessionRepository.class);
        config.addMapper(IUserRepository.class);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
        projectRepository.setSqlSessionFactory(sqlSessionFactory);
        taskRepository.setSqlSessionFactory(sqlSessionFactory);
        userRepository.setSqlSessionFactory(sqlSessionFactory);
        sessionRepository.setSqlSessionFactory(sqlSessionFactory);
        print("SqlSessionFactory created successfully");
    }

    private void createHibernateSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        final SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        setSessionFactory(sessionFactory);
        projectService.setSessionFactory(sessionFactory);
        taskService.setSessionFactory(sessionFactory);
        userService.setSessionFactory(sessionFactory);
        sessionService.setSessionFactory(sessionFactory);
        print("Hibernate session factory created successfully");
    }

    @Override
    public IProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public ITaskService getTaskService() {
        return taskService;
    }

    public void setTaskService(ITaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public ISessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(ISessionService ISessionService) {
        this.sessionService = ISessionService;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public DatabaseConfig getDatabaseConfig() {
        return databaseConfig;
    }

    public void setDatabaseConfig(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}