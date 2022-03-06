package pl.piotrgrochowiecki.entity;

public class DaoException extends RuntimeException {
    public DaoException(String message, Exception cause) {
        super(message, cause);
    }
}
