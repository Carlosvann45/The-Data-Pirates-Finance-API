package theDataPiratesFinanceAPI.exceptions;

import java.util.Date;

/**
 * A class to represent an object to hold error information that the server will return to clients
 */
public class ExceptionResponseMessage {

  private Date timestamp;
  private String error;
  private String errorMessage;

  public ExceptionResponseMessage() { }

  public ExceptionResponseMessage(String error, Date timestamp, String errorMessage) {
    this.error = error;
    this.timestamp = timestamp;
    this.errorMessage = errorMessage;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
