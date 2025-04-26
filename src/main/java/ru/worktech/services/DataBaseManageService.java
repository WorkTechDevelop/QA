package ru.worktech.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseManageService {

    private static final String URL = "jdbc:mysql://91.211.249.37:32500/wt_backend_test";
    private static final String USER = "backend_test";
    private static final String PASSWORD = "d2343&^2dsjsds";

    // Метод для удаления пользователя по email
    public void deleteUser(String email) {
        String sql = "DELETE FROM users WHERE email = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Пользователь с email " + email + " был удален.");
            } else {
                System.out.println("Пользователь с email " + email + " не найден.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}