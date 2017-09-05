package br.com.eduardo.util;

/**
 *
 * @author eduardo
 */
public class DatabaseException extends Exception {
    private final Exception originalException;
    
    
    public DatabaseException(Exception originalException, String message) {
        super(message);
        this.originalException = originalException;
    }
    
    public Exception getOriginalException() {
        return originalException;
    }
}
