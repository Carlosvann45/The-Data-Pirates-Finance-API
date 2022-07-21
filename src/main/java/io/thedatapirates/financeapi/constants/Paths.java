package io.thedatapirates.financeapi.constants;

/**
 * Constant variables for api paths
 */
public class Paths {
  // Customers
  public static final String CUSTOMERS_PATH = "/customers";
  public static final String REFRESH_TOKEN_PATH = "/refresh/token";
  public static final String LOGIN_PATH = "/login";

  // Categories
  public static final String CATEGORY_PATH = "/categories";
  public static final String CATEGORY_ID = "{categoryId}";

  // Frequencies
  public static final String FREQUENCY_PATH = "/frequencies";
  public static final String FREQUENCY_ID = "{frequencyId}";

  // Misc
  public static final String CREATE_PATH = "/create";
  public static final String ALL_EXTENSIONS = "/**";
  public static final String USERNAME_PATH = "/{username}";
}
