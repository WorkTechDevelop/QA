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

    private DbConnection() {}

    public static Connection getConnection() {
        DbConfig cfg = create(DbConfig.class);

        try {
            if (connection == null || connection.isClosed()) {
                synchronized (DbConnection.class) {
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
            throw new DbException("Failed to establish DB connection", e);
        }
    }
}