package database.query;

import database.connection.DbConnection;
import database.exception.DbException;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.slf4j.LoggerFactory.getLogger;

public class GetTaskCodeById extends DbConnection {

    private static final Logger log = getLogger(GetTaskCodeById.class);

    public String getTaskCode(String taskId) {
        Connection connection;
        try {
            connection = initConnection();
        } catch (SQLException e) {
            throw new DbException("Cannot connect to database", e);
        }
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT code FROM task_model WHERE id = ?");
            stmt.setString(1, taskId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String taskCode = rs.getString("code");
                log.info("Task code '{}' найден для taskId '{}'.", taskCode, taskId);
                return taskCode;
            } else {
                throw new DbException("Task code не найден для taskId: " + taskId);
            }
        } catch (SQLException e) {
            log.error("Ошибка при получении taskCode для taskId '{}': {}", taskId, e.getMessage(), e);
            throw new DbException("Ошибка при получении taskCode по taskId: " + taskId, e);
        }
    }
}
