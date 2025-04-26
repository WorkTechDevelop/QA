package ru.worktech.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseManageService {

    private static final String URL = "jdbc:mysql://localhost:3306/your_database"; // Замените на ваш URL
    private static final String USER = "your_username"; // Замените на ваше имя пользователя
    private static final String PASSWORD = "your_password"; // Замените на ваш пароль

    // Метод для удаления пользователя по email
    public void deleteUser(String email) {
        String sql = "DELETE FROM users WHERE email = ?"; // Замените 'users' на название вашей таблицы

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

//    // Метод для получения ID пользователя по имени
//    public int getUserId(String email) {
//        String sql = "SELECT id FROM users WHERE username = ?"; // Замените 'username' на ваше поле
//
//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//            preparedStatement.setString(1, email);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                return resultSet.getInt("id");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return -1; // Возвращаем -1, если пользователь не найден
//    }
}

