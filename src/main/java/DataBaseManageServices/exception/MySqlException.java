package DataBaseManageServices.exception;

public class MySqlException extends RuntimeException {

    public MySqlException(String message, Throwable cause) {
        super(message, cause);
    }

    public MySqlException(String message) {
        super(message);
    }
}