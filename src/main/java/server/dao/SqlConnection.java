package server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://176.115.100.162:5556/?useSSL=false", "Ergaf", "12346");
//        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/?useSSL=false", "postgres", "12346");
    }
}
