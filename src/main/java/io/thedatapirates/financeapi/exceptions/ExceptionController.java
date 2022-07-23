package io.thedatapirates.financeapi.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.thedatapirates.financeapi.constants.StringConstants;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This allows yous you to use the same exception handling techniques but apply them across the
 * whole application, Not just to an individual controller.
 */
@ControllerAdvice
public class ExceptionController {

    private final Logger logger = LogManager.getLogger();

    /**
     * Handles any exceptions that are thrown with the service unavailable class
     *
     * @param exception exception that was thrown
     * @return new response entity the represents the error response
     */
    @ExceptionHandler(ServerUnavailable.class)
    protected ResponseEntity<ExceptionResponseMessage> serverUnavailable(ServerUnavailable exception) {
        List<String> errors = new ArrayList<>();

        errors.add(exception.getMessage());

        ExceptionResponseMessage response = new ExceptionResponseMessage(errors, new Date(), StringConstants.SERVICE_UNAVAILABLE);

        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * Handles any exceptions that are thrown with the not found class
     *
     * @param exception exception that was thrown
     * @return new response entity the represents the error response
     */
    @ExceptionHandler(NotFound.class)
    protected ResponseEntity<ExceptionResponseMessage> notFound(NotFound exception) {
        List<String> errors = new ArrayList<>();

        errors.add(exception.getMessage());

        ExceptionResponseMessage response = new ExceptionResponseMessage(errors, new Date(), StringConstants.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles any exceptions that are thrown with the conflict class
     *
     * @param exception exception that was thrown
     * @return new response entity the represents the error response
     */
    @ExceptionHandler(Conflict.class)
    protected ResponseEntity<ExceptionResponseMessage> conflict(Conflict exception) {
        List<String> errors = new ArrayList<>();

        errors.add(exception.getMessage());

        ExceptionResponseMessage response = new ExceptionResponseMessage(errors, new Date(), StringConstants.CONFLICT);

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    /**
     * Handles any exceptions that are thrown with the bad request class
     *
     * @param exception exception that was thrown
     * @return new response entity the represents the error response
     */
    @ExceptionHandler(BadRequest.class)
    protected ResponseEntity<ExceptionResponseMessage> badRequest(BadRequest exception) {
        List<String> errors = new ArrayList<>();

        errors.add(exception.getMessage());

        ExceptionResponseMessage response = new ExceptionResponseMessage(errors, new Date(), StringConstants.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all exceptions thrown for validation annotation's
     *
     * @param exception exception to get messages from
     * @return new response based from validation exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ExceptionResponseMessage> validationException(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> errors.add(error.getDefaultMessage()));

        ExceptionResponseMessage response = new ExceptionResponseMessage(errors, new Date(), StringConstants.BAD_REQUEST);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
