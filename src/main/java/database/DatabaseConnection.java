package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException{
        String url = "jdbc:postgresql://localhost:5432/esap";
        String username = "postgres";
        String password = System.getenv("ESAP_DB_PASSWORD");

        return DriverManager.getConnection(url, username, password);

    }
}
