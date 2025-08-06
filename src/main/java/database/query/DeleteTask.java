package database.query;

import database.connection.DbConnection;
import database.exception.DbException;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static database.util.SqlLoaderQueryFromResources.load;
import static org.slf4j.LoggerFactory.getLogger;

public class DeleteTask extends DbConnection {
    private static final Logger log = getLogger(DeleteTask.class);

    private static final String SQL_DELETE_TASK = load("delete_task_by_id");

    public void deleteTaskByTaskId(String taskId) {
        try (Connection conn = initConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_DELETE_TASK)) {

            stmt.setString(1, taskId);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                log.info("Задача с id '{}' была удалена.", taskId);
            } else {
                log.warn("Задача с id '{}' не найдена.", taskId);
            }

        } catch (SQLException e) {
            log.error("Ошибка при удалении задачи с id '{}': {}", taskId, e.getMessage(), e);
            throw new DbException("Failed to delete task with id: " + taskId, e);
        }
    }
}