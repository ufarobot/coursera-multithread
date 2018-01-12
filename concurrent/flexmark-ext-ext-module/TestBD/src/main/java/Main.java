package main.java;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/loantest",
                "postgres",
                "123"
        );
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM public.client");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id") + " "
            + resultSet.getInt("id_user_data")
            + resultSet.getBoolean("vip"));
            connection.close();
        }
    }
}
