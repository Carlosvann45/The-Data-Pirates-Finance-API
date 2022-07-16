package theDataPiratesFinanceAPI.constants;

/**
 * A class to represent constant string variables
 */
public class StringConstants {

  // Exceptions
  public static final String SERVICE_UNAVAILABLE = "503 Service Unavailable";
  public static final String BAD_REQUEST = "400 Bad Request";
  public static final String NOT_FOUND = "404 Not Found";
  public static final String CONFLICT = "409 Conflict";

  // Logger
  public static final String LOG_GET_CUSTOMER = "Request received for getCustomers.";
  public static final String LOG_AUTH_JWT_REQUEST = "Request received for authenticateJwtRequest.";
  public static final String LOG_CREATE_CUSTOMER = "Request received for createCustomer.";

  // Error Messages
  public static final String CUSTOMER_NOT_FOUND = "Customer with given username does not exist.";
  public static final String INVALID_LOGIN = "Invalid Credentials. Please try again.";
  public static final String BAD_TOKEN = "Invalid token.";
  public static final String USERNAME_CONFLICT = "Customer with username already exist.";
  public static final String USERNAME_MISMATCH = "Username in pathway does not match username from token";
}
