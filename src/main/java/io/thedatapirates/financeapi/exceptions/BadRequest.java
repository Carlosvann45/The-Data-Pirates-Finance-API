package io.thedatapirates.financeapi.exceptions;

/**
 * A custom exception for if a request causes a bad request
 */
public class BadRequest extends RuntimeException {

    public BadRequest() {
    }

    public BadRequest(String message) {
        super(message);
    }
}
