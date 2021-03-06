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
    public static final String LOG_GET_PRIORITY_LEVELS = "Request received for getPriorityLevels.";
    public static final String LOG_GET_PRIORItY_LEVEL_ID = "Request received for getPriorityLevelById.";
    public static final String LOG_GET_INVESTMENTS_CUSTOMER = "Request received for getInvestmentsByCustomer.";
    public static final String LOG_CREATE_INVESTMENTS_CUSTOMER = "Request received for createInvestmentForCustomer.";
    public static final String LOG_UPDATE_INVESTMENT_CUSTOMER = "Request received for updateInvestmentForCustomer.";
    public static final String LOG_DELETE_INVESTMENT_CUSTOMER = "Request received for deleteInvestmentForCustomer.";
    public static final String LOG_GET_CASH_FLOW_CUSTOMER = "Request received for getCashFlowByCustomer.";
    public static final String LOG_CREATE_CASH_FLOW_CUSTOMER = "Request received for createCashFlowForCustomer.";
    public static final String LOG_UPDATE_CASH_FLOW_CUSTOMER = "Request received for updateCashFlowForCustomer.";
    public static final String LOG_DELETE_CASH_FLOW_CUSTOMER = "Request received for deleteCashFlowForCustomer.";
    public static final String LOG_GET_REMINDER_CUSTOMER = "Request received for getReminderByCustomer.";
    public static final String LOG_DELETE_REMINDER_CUSTOMER = "Request received for deleteReminderForCustomer.";
    public static final String LOG_CREATE_REMINDER_CUSTOMER = "Request received for createReminderForCustomer.";
    public static final String LOG_UPDATE_REMINDER_CUSTOMER = "Request received for updateReminderForCustomer.";
    public static final String LOG_GET_EXPENSE_CUSTOMER = "Request received for getExpenseByCustomer.";
    public static final String LOG_DELETE_EXPENSE_CUSTOMER = "Request received for deleteExpenseForCustomer.";
    public static final String LOG_UPDATE_EXPENSE_CUSTOMER = "Request received for updateReminderForCustomer.";
    public static final String LOG_CREATE_EXPENSE_CUSTOMER = "Request received for createExpenseForCustomer.";


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
    public static final String CATEGORY_DIFF_CUSTOMER = "The supplied category id does not belong to customer from the token";
    public static final String NAME_REQUIRED = "Name is a required field.";
    public static final String NAME_MIN = "Name must be at least 3 characters long.";
    public static final String FREQUENCY_NOT_FOUND = "Frequency with given id does not exist.";
    public static final String PRIORITY_LEVEL_NOT_FOUND = "Priority Level with given id does not exist.";
    public static final String INVESTMENT_NOT_FOUND = "Investment with given id does not exist.";
    public static final String INVESTMENT_DIFF_CUSTOMER = "The supplied investment id does not belong to customer from the token";
    public static final String INVESTMENT_NAME_CONFLICT = "Investment with name already exist.";
    public static final String CASH_FLOW_NOT_FOUND = "Cash Flow with given id does not exist.";
    public static final String CASH_FLOW_DIFF_CUSTOMER = "The supplied cash flow id does not belong to customer from the token";
    public static final String CASH_FLOW_NAME_CONFLICT = "Cash flow with given name already exist.";
    public static final String BAD_FREQUENCY = "Frequency with given id does not exist.";
    public static final String REMINDER_NOT_FOUND = "Reminder with given id does not exist.";
    public static final String REMINDER_DIFF_CUSTOMER = "The supplied reminder id does not belong to customer from the token";
    public static final String REMINDER_NAME_CONFLICT = "Reminder with given name already exist.";
    public static final String DESCRIPTION_REQUIRED = "Description is a required field.";
    public static final String DESCRIPTION_MIN = "Description must be at least 3 characters long.";
    public static final String EXPENSE_BAD_ID = "Expense with the given id does not exist";
    public static final String EXPENSE_NOT_FOUND = "Expense with given id does not exist.";
    public static final String EXPENSE_DIFF_CUSTOMER = "The supplied expense id does not belong to customer from the token";
    public static final String EXPENSE_NAME_CONFLICT = "Expense with given name already exist.";
    public static final String BAD_CATEGORY = "Category with given id does not exist.";
    public static final String BAD_PRIORITY_LEVEL = "Priority Level with given id does not exist.";
    public static final String EXPENSE_HAS_REMINDER = "Expense already contains a reminder.";
    public static final String FIRST_NAME_NULL = "First name for customer can not be null or empty.";
    public static final String LAST_NAME_NULL = "Last name for customer can not be null or empty.";

    // Misc
    public static final String EMPTY_STRING = " ";
    public static final String STAR = "*";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String CUSTOMER = "customer";
    public static final String FREQUENCY_ID = "frequency_id";
    public static final String FREQUENCY = "frequency";
    public static final String CATEGORY_ID = "category_id";
    public static final String CATEGORY = "category";
    public static final String REMINDER = "reminder";
    public static final String REMINDER_ID = "reminder_id";
    public static final String PRIORITY_LEVEL = "priorityLevel";
    public static final String PRIORITY_LEVEL_ID = "priority_level_id";
    public static final String EXPENSE_ID = "expense_id";
    public static final String EXPENSE = "expense";
    public static final String ID = "id";
    public static final String EXCEPTION_RESOLVER = "handlerExceptionResolver";
    public static final String STOCK = "Stock";
    public static final String HEADER = "header";
    public static final String ACCESS_TOKEN = "Access Token";


}
