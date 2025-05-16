package DataBaseManageServices.query;

import DataBaseManageServices.exception.MySqlException;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DataBaseManageServices.connection.DatabaseConnection.getConnection;
import static DataBaseManageServices.util.SqlLoaderQueryFromResources.load;
import static org.slf4j.LoggerFactory.getLogger;

public class GetTaskCodeById {
    private static final Logger log = getLogger(GetTaskCodeById.class);
    private static final String SQL_GET_TASK_CODE = load("get_task_code_by_id");

    public String getTaskCode(String taskId) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_GET_TASK_CODE)) {

            stmt.setString(1, taskId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String taskCode = rs.getString("code");
                    log.info("Task code '{}' найден для taskId '{}'.", taskCode, taskId);
                    return taskCode;
                } else {
                    throw new MySqlException("Task code не найден для taskId: " + taskId);
                }
            }

        } catch (SQLException e) {
            log.error("Ошибка при получении taskCode для taskId '{}': {}", taskId, e.getMessage(), e);
            throw new MySqlException("Ошибка при получении taskCode по taskId: " + taskId, e);
        }
    }
}