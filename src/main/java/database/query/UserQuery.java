package database.query;

import database.connection.DbConnection;
import database.dto.UserDTO;
import database.exception.DbException;
import org.slf4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.slf4j.LoggerFactory.getLogger;

public class UserQuery extends DbConnection {

    private static final Logger log = getLogger(UserQuery.class);

    public void createRole(String userId, String roleName) {
        try {
            int rows = prepareInsertRoleForUser(userId, roleName).executeUpdate();
            if (rows > 0) {
                log.info("Роль пользователя создана успешно.");
            } else {
                log.warn("Ошибка создания роли пользователя.");
            }
        } catch (SQLException e) {
            log.error("Ошибка создания пользователя: {}", e.getMessage(), e);
            throw new DbException("Failed to create user", e);
        }
    }

    public void create(UserDTO userDTO) {
        try {
            int rows = prepareInsert(userDTO).executeUpdate();
            if (rows > 0) {
                log.info("Пользователь успешно создан.");
            } else {
                log.warn("Ошибка создания пользователя.");
            }
        } catch (SQLException e) {
            log.error("Ошибка создания пользователя: {}", e.getMessage(), e);
            throw new DbException("Failed to create user", e);
        }
    }

    public void deleteByEmail(String email) {
        try {
            prepareDeleteRoleForUser(email).executeUpdate();
            prepareDeleteRefreshTokenForUser(email).executeUpdate();
            int rows = prepareDeleteUser(email).executeUpdate();
            if (rows > 0) {
                log.info("Пользователь с email '{}' был удалён.", email);
            } else {
                log.warn("Пользователь с email '{}' не был удалён.", email);
            }
        } catch (SQLException e) {
            log.error("Ошибка при удалении пользователя с email '{}': {}", email, e.getMessage(), e);
            throw new DbException("Failed to delete user with email: " + email, e);
        }
    }

    public void closeConnection() throws SQLException {
        super.closeConnection();
    }

    private PreparedStatement prepareDeleteUser(String userEmail) throws SQLException {
        PreparedStatement stmt = initConnection().prepareStatement(
                "DELETE FROM users WHERE email = ?;");
        stmt.setString(1, userEmail);
        return stmt;
    }

    private PreparedStatement prepareDeleteRoleForUser(String userEmail) throws SQLException {
        PreparedStatement stmt = initConnection().prepareStatement(
                "DELETE FROM user_role WHERE user_id in (SELECT id FROM users WHERE email = ?); ");
        stmt.setString(1, userEmail);
        return stmt;
    }

    private PreparedStatement prepareDeleteRefreshTokenForUser(String userEmail) throws SQLException {
        PreparedStatement stmt = initConnection().prepareStatement(
                "DELETE FROM refresh_token WHERE user_id in (SELECT id FROM users WHERE email = ?); ");
        stmt.setString(1, userEmail);
        return stmt;
    }

    private PreparedStatement prepareInsertRoleForUser(String userId, String roleName) throws SQLException {
        PreparedStatement stmt = initConnection().prepareStatement(
                "INSERT INTO user_role (user_id, role_id) " +
                        "VALUES (?, (SELECT id FROM role WHERE name = ?));");
        stmt.setString(1, userId);
        stmt.setString(2, roleName);
        return stmt;
    }

    private PreparedStatement prepareInsert(UserDTO userDTO) throws SQLException {
        PreparedStatement stmt = initConnection().prepareStatement(
                "INSERT INTO users (" +
                        "id, is_active, birth_date, email, first_name, last_name, gender, middle_name, " +
                        "password, phone, confirmed_at, confirmation_token, last_project_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1, userDTO.getId());
        stmt.setBoolean(2, userDTO.is_active());
        stmt.setDate(3, userDTO.getBirth_date() == null ? null : userDTO.getBirth_date());
        stmt.setString(4, userDTO.getEmail());
        stmt.setString(5, userDTO.getFirst_name());
        stmt.setString(6, userDTO.getLast_name());
        stmt.setString(7, userDTO.getGender());
        stmt.setString(8, userDTO.getMiddle_name());
        stmt.setString(9, userDTO.getPassword());
        stmt.setString(10, userDTO.getPhone());
        stmt.setTimestamp(11, userDTO.getConfirmed_at());
        stmt.setString(12, userDTO.getConfirmation_token());
        stmt.setString(13, userDTO.getLast_project_id());
        return stmt;
    }
}