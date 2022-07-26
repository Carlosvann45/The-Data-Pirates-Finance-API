package io.thedatapirates.financeapi.exceptions;

import java.util.Date;
import java.util.List;

/**
 * A class to represent an object to hold error information that the server will return to clients
 */
public class ExceptionResponseMessage {

  private Date timestamp;
  private String errorMessage;
  private List<String> errors;

  public ExceptionResponseMessage() {
  }

  public ExceptionResponseMessage(List<String> errors, Date timestamp, String errorMessage) {
    this.errors = errors;
    this.timestamp = timestamp;
    this.errorMessage = errorMessage;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public List<String> getErrors() {
    return errors;
  }

  public void setErrors(List<String> errors) {
    this.errors = errors;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
