package io.thedatapirates.financeapi.exceptions;

/**
 * A custom exception for if a request causes a conflict
 */
public class Conflict extends RuntimeException {
    public Conflict() {
    }

    public Conflict(String message) {
        super(message);
    }
}
