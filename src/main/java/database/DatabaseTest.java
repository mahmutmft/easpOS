package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTest {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Connected to ESAP database!");

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM waiter");

            while (result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                String username = result.getString("username");

                System.out.println(id + " | " + name + " | " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
