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

    // Priority Levels
    public static final String PRIORITY_LEVEL_PATH = "/priorityLevels";
    public static final String PRIORITY_LEVEL_ID = "{priorityLevelId}";

    // Investments
    public static final String INVESTMENTS_PATH = "/investments";
    public static final String INVESTMENT_ID = "{investmentId}";

    // Cash Flow
    public static final String CASH_FLOW_PATH = "/cashFlows";
    public static final String CASH_FLOW_ID = "{cashFlowId}";
    public static final String DEPOSIT_TO_CASH_FLOW = "/deposit/to/";

    // Reminder
    public static final String REMINDER_PATH = "/reminders";
    public static final String REMINDER_ID = "{reminderId}";

    // Expense
    public static final String EXPENSE_PATH = "/expenses";
    public static final String EXPENSE_ID = "{expenseId}";
    public static final String WITHDRAWAL_FOR_EXPENSE = "/withdrawal/for/";

    // Verification
    public static final String VERIFICATION_PATH = "/verifications";
    public static final String FORGOT_PASS_PATH = "/password/forgot/{email}";
    public static final String CHANGE_PASS_PATH = "/password/reset";

    // Misc
    public static final String CREATE_PATH = "/create";
    public static final String ALL_EXTENSIONS = "/**";
    public static final String USERNAME_PATH = "/{username}";
}
