package DataBaseManageServices.query;

import DataBaseManageServices.exception.MySqlException;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static DataBaseManageServices.connection.DatabaseConnection.getConnection;
import static DataBaseManageServices.util.SqlLoaderQueryFromResources.*;
import static org.slf4j.LoggerFactory.getLogger;

public class DeleteUserFromDataBase {

    private static final Logger log = getLogger(DeleteUserFromDataBase.class);

    private static final String SQL_DELETE_USER = load("delete_user_by_email");

    public void deleteUserByEmail(String email) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_DELETE_USER)) {
            stmt.setString(1, email);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                log.info("Пользователь с email '{}' был удалён.", email);
            } else {
                log.warn("Пользователь с email '{}' не найден.", email);
            }
        } catch (SQLException e) {
            log.error("Ошибка при удалении пользователя с email '{}': {}", email, e.getMessage(), e);
            throw new MySqlException("Failed to delete user with email: " + email, e);
        }
    }
}