package database.connection;

import database.config.DbConfig;
import database.exception.DbException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.aeonbits.owner.ConfigFactory.create;

public class DbConnection {

    private static volatile Connection connection;

    private Connection getConnection() {
        DbConfig cfg = create(DbConfig.class);

        try {
            synchronized (DbConnection.class) {
                if (connection == null || connection.isClosed()) {
                    Properties props = new Properties();
                    props.setProperty("user", cfg.username());
                    props.setProperty("password", cfg.password());
                    connection = DriverManager.getConnection(cfg.url(), props);
                }
            }
            return connection;
        } catch (SQLException e) {
            throw new DbException("Failed to establish DB connection", e);
        }
    }

    protected void closeConnection() throws SQLException {
        if (connection != null || !connection.isClosed()) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DbException("Failed to close DB connection", e);
            }
        }
    }

    protected Connection initConnection() throws DbException, SQLException {
        if (connection == null || connection.isClosed()) {
            connection = getConnection();
        }
        return connection;
    }
}