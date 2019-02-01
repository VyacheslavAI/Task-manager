package ru.ivanov.todoproject.config;

public class DatabaseConfig {

    private final String userName = "root";

    private final String password = "root";

    private final String driver = "com.mysql.jdbc.Driver";

    private final String connectionUrl = "jdbc:mysql://localhost:3306/taskmanager?useLegacyDatetimeCode=false&serverTimezone=UTC";

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public String getDriver() {
        return driver;
    }
}
