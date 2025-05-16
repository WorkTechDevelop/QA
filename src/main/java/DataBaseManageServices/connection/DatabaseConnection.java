package DataBaseManageServices.connection;

import DataBaseManageServices.config.DbConfig;
import DataBaseManageServices.exception.MySqlException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.aeonbits.owner.ConfigFactory.create;

public class DatabaseConnection {

    private static volatile Connection connection;

    private DatabaseConnection() {}

    public static Connection getConnection() {
        DbConfig cfg = create(DbConfig.class);

        try {
            if (connection == null || connection.isClosed()) {
                synchronized (DatabaseConnection.class) {
                    if (connection == null || connection.isClosed()) {
                        Properties props = new Properties();
                        props.setProperty("user", cfg.username());
                        props.setProperty("password", cfg.password());
                        connection = DriverManager.getConnection(cfg.url(), props);
                    }
                }
            }
            return connection;
        } catch (SQLException e) {
            throw new MySqlException("Failed to establish DB connection", e);
        }
    }
}