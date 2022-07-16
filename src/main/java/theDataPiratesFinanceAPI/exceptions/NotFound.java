package theDataPiratesFinanceAPI.exceptions;

/**
 * A custom exception for if something is not found
 */
public class NotFound extends RuntimeException {

    public NotFound() { }

    public NotFound(String message) { super(message); }
}
