package theDataPiratesFinanceAPI.constants;

/**
 * A class to represent constant string variables
 */
public class StringConstants {

  // Exceptions
  public static final String SERVICE_UNAVAILABLE = "503 Service Unavailable";
  public static final String BAD_REQUEST = "400 Bad Request";

  // Logger
  public static final String LOG_GET_CUSTOMERS = "Request received for getCustomers.";
  public static final String LOG_SAVE_CUSTOMER= "Request received for saveCustomer.";

  // Error Messages
  public static final String CUSTOMER_USERNAME_NULL_400 = "Username can not be empty.";
  public static final String CUSTOMER_PASSWORD_NULL_400 = "Password can not be empty.";
}
