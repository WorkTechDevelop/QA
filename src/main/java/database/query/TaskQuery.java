package database.query;

import database.exception.DbException;
import org.slf4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static database.connection.DbQueryPreparer.prepareStatement;
import static org.slf4j.LoggerFactory.getLogger;


public class TaskQuery {
    private static final Logger log = getLogger(TaskQuery.class);

    public void deleteTaskByTaskId(String taskId) {
        try{
            prepareDelete(taskId).execute();
        } catch (SQLException e) {
            log.error("Ошибка при удалении задачи с id '{}': {}", taskId, e.getMessage(), e);
            throw new DbException("Failed to delete task with id: " + taskId, e);
        }
    }

    private PreparedStatement prepareDelete(String taskId) throws SQLException {
        PreparedStatement stmt = prepareStatement("DELETE FROM task_model WHERE id = ?;");
        stmt.setString(1, taskId);
        return stmt;
    }
}