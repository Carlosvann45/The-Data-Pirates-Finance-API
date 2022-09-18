package io.thedatapirates.financeapi.utility;


import io.thedatapirates.financeapi.domains.cashflows.CashFlow;
import io.thedatapirates.financeapi.domains.cashflows.RequestCashFlowDTO;
import io.thedatapirates.financeapi.domains.cashflows.ResponseCashFlowDTO;
import io.thedatapirates.financeapi.domains.categories.Category;
import io.thedatapirates.financeapi.domains.categories.CategoryDTO;
import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.customers.RequestCustomerDTO;
import io.thedatapirates.financeapi.domains.customers.ResponseCustomerDTO;
import io.thedatapirates.financeapi.domains.deposits.Deposit;
import io.thedatapirates.financeapi.domains.deposits.DepositDTO;
import io.thedatapirates.financeapi.domains.expenses.Expense;
import io.thedatapirates.financeapi.domains.expenses.RequestExpenseDTO;
import io.thedatapirates.financeapi.domains.expenses.ResponseExpenseDTO;
import io.thedatapirates.financeapi.domains.frequencies.Frequency;
import io.thedatapirates.financeapi.domains.frequencies.FrequencyDTO;
import io.thedatapirates.financeapi.domains.investments.Investment;
import io.thedatapirates.financeapi.domains.investments.InvestmentDTO;
import io.thedatapirates.financeapi.domains.prioritylevels.PriorityLevel;
import io.thedatapirates.financeapi.domains.prioritylevels.PriorityLevelDTO;
import io.thedatapirates.financeapi.domains.reminders.Reminder;
import io.thedatapirates.financeapi.domains.reminders.RequestReminderDTO;
import io.thedatapirates.financeapi.domains.reminders.ResponseReminderDTO;
import io.thedatapirates.financeapi.domains.withdrawals.Withdrawal;
import io.thedatapirates.financeapi.domains.withdrawals.WithdrawalDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Extension methods to map entities
 */
public class MapperExtensions {

    /**
     * Maps a frequency to frequency DTO
     *
     * @param frequencyDTO frequency DTO to map
     * @return newly created frequency
     */
    public static Frequency mapDTOToFrequency(FrequencyDTO frequencyDTO) {
        Frequency frequency = new Frequency();

        frequency.setId(frequencyDTO.getId());
        frequency.setDateCreated(frequencyDTO.getDateCreated());
        frequency.setDateUpdated(frequencyDTO.getDateUpdated());
        frequency.setName(frequencyDTO.getName());

        return frequency;
    }

    /**
     * Maps a frequency DTO to frequency
     *
     * @param frequency frequency to map
     * @return newly created frequency DTO
     */
    public static FrequencyDTO mapFrequencyToDTO(Frequency frequency) {
        FrequencyDTO frequencyDTO = new FrequencyDTO();

        frequencyDTO.setId(frequency.getId());
        frequencyDTO.setDateCreated(frequency.getDateCreated());
        frequencyDTO.setDateUpdated(frequency.getDateUpdated());
        frequencyDTO.setName(frequency.getName());

        return frequencyDTO;
    }

    /**
     * Maps a priority level DTO to priority level
     *
     * @param priorityLevelDTO priority level dto to map
     * @return newly created priority level
     */
    public static PriorityLevel mapDTOToPriorityLevel(PriorityLevelDTO priorityLevelDTO) {
        PriorityLevel priorityLevel = new PriorityLevel();

        priorityLevel.setId(priorityLevelDTO.getId());
        priorityLevel.setDateCreated(priorityLevelDTO.getDateCreated());
        priorityLevel.setDateUpdated(priorityLevelDTO.getDateUpdated());
        priorityLevel.setLevel(priorityLevelDTO.getLevel());
        priorityLevel.setDescription(priorityLevelDTO.getDescription());

        return priorityLevel;
    }

    /**
     * Maps a priority level to priority level DTO
     *
     * @param priorityLevel priority level to map
     * @return newly created priority level dto
     */
    public static PriorityLevelDTO mapPriorityLevelToDTO(PriorityLevel priorityLevel) {
        PriorityLevelDTO priorityLevelDTO = new PriorityLevelDTO();

        priorityLevelDTO.setId(priorityLevel.getId());
        priorityLevelDTO.setDateCreated(priorityLevel.getDateCreated());
        priorityLevelDTO.setDateUpdated(priorityLevel.getDateUpdated());
        priorityLevelDTO.setLevel(priorityLevel.getLevel());
        priorityLevelDTO.setDescription(priorityLevel.getDescription());

        return priorityLevelDTO;
    }

    /**
     * Maps an investment to an investment dto
     *
     * @param investment investment to map
     * @return newly created investment dto
     */
    public static InvestmentDTO mapInvestmentToDTO(Investment investment) {
        InvestmentDTO investmentDTO = new InvestmentDTO();

        investmentDTO.setId(investment.getId());
        investmentDTO.setDateCreated(investment.getDateCreated());
        investmentDTO.setDateUpdated(investment.getDateUpdated());
        investmentDTO.setInvestmentType(investment.getInvestmentType());
        investmentDTO.setName(investment.getName());
        investmentDTO.setSector(investment.getSector());
        investmentDTO.setAmount(investment.getAmount());
        investmentDTO.setBuyPrice(investment.getBuyPrice());

        return investmentDTO;
    }

    /**
     * Maps an investment dto to an investment dto
     *
     * @param investmentDTO investment dto to map
     * @return newly created investment
     */
    public static Investment mapDTOToInvestment(InvestmentDTO investmentDTO) {
        Investment investment = new Investment();

        investment.setId(investmentDTO.getId());
        investment.setDateCreated(investmentDTO.getDateCreated());
        investment.setDateUpdated(investmentDTO.getDateUpdated());
        investment.setInvestmentType(investmentDTO.getInvestmentType());
        investment.setName(investmentDTO.getName());
        investment.setSector(investmentDTO.getSector());
        investment.setAmount(investmentDTO.getAmount());
        investment.setBuyPrice(investmentDTO.getBuyPrice());

        return investment;
    }

    /**
     * Maps category to category dto
     *
     * @param category category to map
     * @return newly create category dto
     */
    public static CategoryDTO mapCategoryToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setDateCreated(category.getDateCreated());
        categoryDTO.setDateUpdated(category.getDateUpdated());
        categoryDTO.setName(category.getName());

        return categoryDTO;
    }

    /**
     * Maps category dto to category
     *
     * @param categoryDTO category dto to map
     * @return newly create category
     */
    public static Category mapDTOToCategory(CategoryDTO categoryDTO) {
        Category category = new Category();

        category.setId(categoryDTO.getId());
        category.setDateCreated(categoryDTO.getDateCreated());
        category.setDateUpdated(categoryDTO.getDateUpdated());
        category.setName(categoryDTO.getName());

        return category;
    }

    /**
     * Maps cash flow to cash flow dto
     *
     * @param cashFlow cash flow to map
     * @return newly created cash flow dto
     */
    public static ResponseCashFlowDTO mapCashFlowToDTO(CashFlow cashFlow) {
        ResponseCashFlowDTO cashFlowDTO = new ResponseCashFlowDTO();

        cashFlowDTO.setId(cashFlow.getId());
        cashFlowDTO.setStartDate(cashFlow.getStartDate());
        cashFlowDTO.setEndDate(cashFlowDTO.getEndDate());
        cashFlowDTO.setDateCreated(cashFlow.getDateCreated());
        cashFlowDTO.setDateUpdated(cashFlow.getDateUpdated());
        cashFlowDTO.setName(cashFlow.getName());
        cashFlowDTO.setFrequency(mapFrequencyToDTO(cashFlow.getFrequency()));

        List<DepositDTO> deposits = cashFlow.getDeposits()
                .stream()
                .map(MapperExtensions::mapDepositToDTO)
                .collect(Collectors.toList());

        cashFlowDTO.setDeposits(deposits);

        return cashFlowDTO;
    }

    /**
     * Maps cash flow dto to cash flow
     *
     * @param cashFlowDTO cash flow dto to map
     * @return newly created cash flow
     */
    public static CashFlow mapDTOToCashFlow(RequestCashFlowDTO cashFlowDTO) {
        CashFlow cashFlow = new CashFlow();

        cashFlow.setId(cashFlowDTO.getId());
        cashFlow.setStartDate(cashFlowDTO.getStartDate());
        cashFlow.setEndDate(cashFlowDTO.getEndDate());
        cashFlow.setId(cashFlowDTO.getId());
        cashFlow.setDateCreated(cashFlowDTO.getDateCreated());
        cashFlow.setDateUpdated(cashFlowDTO.getDateUpdated());
        cashFlow.setName(cashFlowDTO.getName());

        return cashFlow;
    }

    /**
     * Maps reminder to reminder dto
     *
     * @param reminder reminder to map
     * @return newly created reminder dto
     */
    public static ResponseReminderDTO mapReminderToDTO(Reminder reminder) {
        ResponseReminderDTO reminderDTO = new ResponseReminderDTO();

        reminderDTO.setId(reminder.getId());
        reminderDTO.setDateCreated(reminder.getDateCreated());
        reminderDTO.setDateUpdated(reminder.getDateUpdated());
        reminderDTO.setName(reminder.getName());
        reminderDTO.setDescription(reminder.getDescription());
        reminderDTO.setReminderTime(reminder.getReminderTime());
        reminderDTO.setFrequency(mapFrequencyToDTO(reminder.getFrequency()));

        return reminderDTO;
    }

    /**
     * Maps reminder dto to reminder
     *
     * @param reminderDTO reminder dto to map
     * @return newly created reminder
     */
    public static Reminder mapDTOToReminder(RequestReminderDTO reminderDTO) {
        Reminder reminder = new Reminder();

        reminder.setId(reminderDTO.getId());
        reminder.setDateCreated(reminderDTO.getDateCreated());
        reminder.setDateUpdated(reminderDTO.getDateUpdated());
        reminder.setName(reminderDTO.getName());
        reminder.setDescription(reminderDTO.getDescription());
        reminder.setReminderTime(reminderDTO.getReminderTime());

        return reminder;
    }

    /**
     * Maps expense to expense dto
     *
     * @param expense expense to map
     * @return newly created expense
     */
    public static ResponseExpenseDTO mapExpenseToDTO(Expense expense) {
        ResponseExpenseDTO expenseDTO = new ResponseExpenseDTO();

        expenseDTO.setId(expense.getId());
        expenseDTO.setDateCreated(expense.getDateCreated());
        expenseDTO.setDateUpdated(expense.getDateUpdated());
        expenseDTO.setName(expense.getName());
        expenseDTO.setStartDate(expense.getStartDate());
        expenseDTO.setEndDate(expense.getEndDate());
        expenseDTO.setCategory(mapCategoryToDTO(expense.getCategory()));
        expenseDTO.setFrequency(mapFrequencyToDTO(expense.getFrequency()));
        expenseDTO.setPriorityLevel(mapPriorityLevelToDTO(expense.getPriorityLevel()));

        List<WithdrawalDTO> withdrawals = expense.getWithdrawals()
                .stream()
                .map(MapperExtensions::mapWithdrawalToDTO)
                .collect(Collectors.toList());

        List<ResponseReminderDTO> reminders = expense.getReminders()
                .stream()
                .map(MapperExtensions::mapReminderToDTO)
                .collect(Collectors.toList());

        expenseDTO.setWithdrawals(withdrawals);
        expenseDTO.setReminders(reminders);

        return expenseDTO;
    }

    /**
     * Maps expense dto to expense
     *
     * @param expenseDTO expense dto to map
     * @return newly created expense
     */
    public static Expense mapDTOToExpense(RequestExpenseDTO expenseDTO) {
        Expense expense = new Expense();

        expense.setId(expenseDTO.getId());
        expense.setDateCreated(expenseDTO.getDateCreated());
        expense.setDateUpdated(expenseDTO.getDateUpdated());
        expense.setStartDate(expenseDTO.getStartDate());
        expense.setEndDate(expenseDTO.getEndDate());
        expense.setName(expenseDTO.getName());

        return expense;
    }

    /**
     * Maps customer to customer dto
     *
     * @param customer customer to map
     * @return newly created customer dto
     */
    public static ResponseCustomerDTO mapCustomerToDTO(Customer customer) {
        ResponseCustomerDTO customerDTO = new ResponseCustomerDTO();

        customerDTO.setId(customer.getId());
        customerDTO.setDateCreated(customer.getDateCreated());
        customerDTO.setDateUpdated(customer.getDateUpdated());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setUsername(customer.getUsername());
        customerDTO.setPassword(customer.getPassword());

        List<InvestmentDTO> investments = customer.getInvestments()
                .stream()
                .map(MapperExtensions::mapInvestmentToDTO)
                .collect(Collectors.toList());

        List<ResponseCashFlowDTO> cashFlowItems = customer.getCashFlowItems()
                .stream()
                .map(MapperExtensions::mapCashFlowToDTO)
                .collect(Collectors.toList());

        List<ResponseReminderDTO> reminders
                = customer.getReminders()
                .stream()
                .map(MapperExtensions::mapReminderToDTO)
                .collect(Collectors.toList());

        List<ResponseExpenseDTO> expenses
                = customer.getExpenses()
                .stream()
                .map(MapperExtensions::mapExpenseToDTO)
                .collect(Collectors.toList());

        List<DepositDTO> deposits = customer.getDeposits()
                .stream()
                .map(MapperExtensions::mapDepositToDTO)
                .collect(Collectors.toList());

        customerDTO.setInvestments(investments);
        customerDTO.setCashFlowItems(cashFlowItems);
        customerDTO.setReminders(reminders);
        customerDTO.setExpenses(expenses);
        customerDTO.setDeposits(deposits);

        return customerDTO;
    }

    /**
     * Maps customer dto to customer
     *
     * @param customerDTO customer dto to map
     * @return newly created customer
     */
    public static Customer mapDTOToCustomer(RequestCustomerDTO customerDTO) {
        Customer customer = new Customer();

        customer.setId(customer.getId());
        customer.setDateCreated(customer.getDateCreated());
        customer.setDateUpdated(customer.getDateUpdated());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setUsername(customerDTO.getUsername());
        customer.setPassword(customerDTO.getPassword());

        return customer;
    }

    /**
     * Maps deposit dto to deposit
     *
     * @param depositDTO deposit dto to map
     * @return newly created deposit
     */
    public static Deposit mapDTOToDeposit(DepositDTO depositDTO) {
        Deposit deposit = new Deposit();

        deposit.setId(depositDTO.getId());
        deposit.setDateCreated(depositDTO.getDateCreated());
        deposit.setDateUpdated(depositDTO.getDateUpdated());
        deposit.setAmount(depositDTO.getAmount());

        return deposit;
    }

    /**
     * Maps deposit to deposit dto
     *
     * @param deposit deposit to map
     * @return newly created deposit dto
     */
    public static DepositDTO mapDepositToDTO(Deposit deposit) {
        DepositDTO depositDTO = new DepositDTO();

        depositDTO.setId(deposit.getId());
        depositDTO.setDateCreated(deposit.getDateCreated());
        depositDTO.setDateUpdated(deposit.getDateUpdated());
        depositDTO.setAmount(deposit.getAmount());

        return depositDTO;
    }

    /**
     * Maps withdrawal dto to withdrawal
     *
     * @param withdrawalDTO withdrawal dto to map
     * @return newly created withdrawal
     */
    public static Withdrawal mapDTOToWithdrawal(WithdrawalDTO withdrawalDTO) {
        Withdrawal withdrawal = new Withdrawal();

        withdrawal.setId(withdrawalDTO.getId());
        withdrawal.setDateCreated(withdrawalDTO.getDateCreated());
        withdrawal.setDateUpdated(withdrawalDTO.getDateUpdated());
        withdrawal.setAmount(withdrawalDTO.getAmount());

        return withdrawal;
    }

    /**
     * Maps withdrawal to withdrawal dto
     *
     * @param withdrawal withdrawal to map
     * @return newly created withdrawal dto
     */
    public static WithdrawalDTO mapWithdrawalToDTO(Withdrawal withdrawal) {
        WithdrawalDTO withdrawalDTO = new WithdrawalDTO();

        withdrawalDTO.setId(withdrawal.getId());
        withdrawalDTO.setDateCreated(withdrawal.getDateCreated());
        withdrawalDTO.setDateUpdated(withdrawal.getDateUpdated());
        withdrawalDTO.setAmount(withdrawal.getAmount());

        return withdrawalDTO;
    }
}
