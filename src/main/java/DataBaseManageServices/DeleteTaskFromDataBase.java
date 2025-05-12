package DataBaseManageServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.System.out;
import static java.sql.DriverManager.getConnection;

public class DeleteTaskFromDataBase {
    private static final String URL = "jdbc:mysql://91.211.249.37:32500/wt_backend_test";
    private static final String USER = "backend_test";
    private static final String PASSWORD = "d2343&^2dsjsds";

    public void deleteTaskByTaskId(String taskId) {
        String sqlCommandForDeleteTask = "DELETE FROM task_model WHERE id = ?";

        try (Connection connection = getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(sqlCommandForDeleteTask)) {

            stmt.setString(1, taskId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                out.println("Задача с названием \"" + taskId + "\" успешно удалена.");
            } else {
                out.println(" Задача с названием \"" + taskId + "\" не найдена.");
            }

        } catch (SQLException e) {
            out.println("Ошибка при удалении задачи: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
//    public void getTaskId(String title) {
//        String sqlCommandForGetTitleTask = "Select taskId FROM users WHERE title = ?";
//
//        try (Connection connection = getConnection(URL, USER, PASSWORD);
//             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommandForGetTitleTask)) {
//
//            preparedStatement.setString(1, title);
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            if (rowsAffected > 0) {
//                out.println("Задача с title " + title + " был найден.");
//            } else {
//                out.println("Задача с title " + title + " не найден.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}