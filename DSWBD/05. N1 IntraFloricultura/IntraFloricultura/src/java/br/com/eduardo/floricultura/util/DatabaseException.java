package br.com.eduardo.floricultura.util;

/**
 *
 * @author eduardo
 */
public class DatabaseException extends Exception {

    private Exception originalException = null;

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(Exception originalException, String message) {
        super(message);
        this.originalException = originalException;
    }

    public Exception getOriginalException() {
        return originalException;
    }
}
