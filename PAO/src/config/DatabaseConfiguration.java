package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfiguration {
    private final Connection databaseConnection;

    public DatabaseConfiguration(){
        try {
            String url = "jdbc:mysql://localhost:3306/mysql";
            String user = "root";
            String password = "acum1234/";
            databaseConnection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection(){
        return databaseConnection;
    }
}
