package io.thedatapirates.financeapi.constants;

/**
 * A class to represent constant string variables
 */
public class StringConstants {
  // JWT
  public static final String BEARER_BEGINNING = "Bearer ";
  public static final String USERNAME_PARAM_NAME = "username";
  public static final String PASSWORD_PARAM_NAME = "password";

  // Exceptions
  public static final String SERVICE_UNAVAILABLE = "503 Service Unavailable";
  public static final String BAD_REQUEST = "400 Bad Request";
  public static final String NOT_FOUND = "404 Not Found";
  public static final String CONFLICT = "409 Conflict";

  // Logger
  public static final String LOG_GET_CUSTOMER = "Request received for getCustomers.";
  public static final String LOG_REFRESH_CUSTOMER_TOKEN = "Request received for refreshCustomerToken.";
  public static final String LOG_CREATE_CUSTOMER = "Request received for createCustomer.";
  public static final String LOG_GET_CATEGORIES_CUSTOMER = "Request received for getCategoriesByCustomer.";
  public static final String LOG_CREATE_CATEGORIES_CUSTOMER = "Request received for createCategoryForCustomer.";
  public static final String LOG_UPDATE_CATEGORIES_CUSTOMER = "Request received for updateCategoryForCustomer.";
  public static final String LOG_DELETE_CATEGORIES_CUSTOMER = "Request received for deleteCategoryForCustomer.";
  public static final String LOG_GET_FREQUENCIES = "Request received for getFrequencies.";
  public static final String LOG_GET_FREQUENCIES_ID = "Request received for getFrequencyById.";

  // Error Messages
  public static final String CUSTOMER_NOT_FOUND = "Customer with given username does not exist.";
  public static final String INVALID_LOGIN = "Invalid Credentials. Please try again.";
  public static final String BAD_TOKEN = "Invalid token.";
  public static final String USERNAME_CONFLICT = "Username is a required field.";
  public static final String USERNAME_MISMATCH = "Username in pathway does not match username from token";
  public static final String JWT_ERROR_BEGINNING = "Jwt token error: ";
  public static final String JWT_CREDENTIAL_BEGINNING = "Bad credentials error: ";
  public static final String USERNAME_NULL = "Username is a required field.";
  public static final String USERNAME_BAD_EMAIL = "Username for customer must follow proper email format.";
  public static final String PASSWORD_NULL = "Password for customer can not be null or empty.";
  public static final String PASSWORD_BAD_SIZE = "Password must be between 8 and 20 characters long.";
  public static final String CATEGORY_NAME_CONFLICT = "Category with name already exist.";
  public static final String CATEGORY_NOT_FOUND = "Category with given id does not exist.";
  public static final String CATEGORY_DIFF_CUSTOMER = "The supplied category id does not belong to customer from token";
  public static final String NAME_REQUIRED = "Name is a required field.";
  public static final String NAME_MIN = "Name must be at least 3 characters long.";
  public static final String FREQUENCY_NOT_FOUND = "Frequency with given id does not exist.";

  // Misc
  public static final String EMPTY_STRING = " ";
  public static final String STAR = "*";
  public static final String CUSTOMER_ID = "customer_id";
  public static final String EXCEPTION_RESOLVER = "handlerExceptionResolver";

}
