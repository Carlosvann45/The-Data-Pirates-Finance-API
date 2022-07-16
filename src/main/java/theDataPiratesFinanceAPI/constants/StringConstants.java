package theDataPiratesFinanceAPI.constants;

/**
 * A class to represent constant string variables
 */
public class StringConstants {

  // Exceptions
  public static final String SERVICE_UNAVAILABLE = "503 Service Unavailable";
  public static final String BAD_REQUEST = "400 Bad Request";
  public static final String NOT_FOUND = "404 Not Found";

  // Logger
  public static final String LOG_GET_CUSTOMER = "Request received for getCustomers.";
  public static final String LOG_AUTH_JWT_REQUEST = "Request received for authenticateJwtRequest.";

  // Error
  public static final String CUSTOMER_NOT_FOUND = "Customer with given username does not exist.";
}
