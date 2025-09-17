package database.connection;

import database.config.DbConfig;
import database.exception.DbException;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static org.aeonbits.owner.ConfigFactory.create;

@UtilityClass
public class DbQueryPreparer {

    public PreparedStatement prepareStatement(String statement) {
        try {
            return ConnectionFactory.getConnection().prepareStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error preparing statement: " + statement, e);
        }
    }

    public static class ConnectionFactory {
        private static Connection connection = null;

        public static void closeConnection () {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                connection = null;
            }
        }

        private static Connection getConnection () {
            if (connection == null) {
                try {
                    connection = initConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return connection;
        }

        private static Connection initConnection () throws DbException, SQLException {
            DbConfig cfg = create(DbConfig.class);

            try {
                synchronized (DbQueryPreparer.class) {
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
    }

}