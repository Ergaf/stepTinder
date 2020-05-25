package server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/?useSSL=false", "root", "12346");
//        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/?useSSL=false", "postgres", "12346");
    }
}
