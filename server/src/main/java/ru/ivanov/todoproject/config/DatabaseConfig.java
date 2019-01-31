package ru.ivanov.todoproject.config;

public class DatabaseConfig {

    final String userName = "root";

    final String password = "root";

    final String connectionUrl = "jdbc:mysql://localhost:3306/taskmanager?useLegacyDatetimeCode=false&serverTimezone=UTC";

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }
}
