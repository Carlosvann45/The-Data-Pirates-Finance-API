package io.thedatapirates.financeapi.domains.expenses;

import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.categories.Category;
import io.thedatapirates.financeapi.domains.categories.CategoryRepository;
import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.customers.CustomerRepository;
import io.thedatapirates.financeapi.domains.frequencies.Frequency;
import io.thedatapirates.financeapi.domains.frequencies.FrequencyRepository;
import io.thedatapirates.financeapi.domains.prioritylevels.PriorityLevel;
import io.thedatapirates.financeapi.domains.prioritylevels.PriorityLevelRepository;
import io.thedatapirates.financeapi.exceptions.BadRequest;
import io.thedatapirates.financeapi.exceptions.Conflict;
import io.thedatapirates.financeapi.exceptions.NotFound;
import io.thedatapirates.financeapi.exceptions.ServerUnavailable;
import io.thedatapirates.financeapi.utility.JWTUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * A class to implement all methods from the expense service interface
 */
@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final Logger logger = LogManager.getLogger(ExpenseServiceImpl.class);

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FrequencyRepository frequencyRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PriorityLevelRepository priorityLevelRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    /**
     * Calls repository to get a customer and retrieves all expenses based on that customers id
     *
     * @param token token to get username for customer
     * @return all expenses related to a specified customer
     */
    @Override
    public List<Expense> getExpenseByCustomer(String token) {
        Customer existingCustomer = getCustomerFromToken(token);

        try {
            return expenseRepository.findAllByCustomerId(existingCustomer.getId());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }

    /**
     * Creates a new expense for a customer if frequency and customer exist in the database
     *
     * @param token           token to get customer from
     * @param categoryId      category id to search for
     * @param frequencyId     frequency id to search for
     * @param priorityLevelId priority level id to search for
     * @param newExpense      new expense to be created
     * @return newly created expense
     */
    @Override
    public Expense createExpenseForCustomer(
            String token, Long categoryId, Long frequencyId, Long priorityLevelId, Expense newExpense
    ) {
        Expense existingExpense;
        Category existingCategory;
        Frequency existingFrequency;
        PriorityLevel existingPriorityLevel;
        Customer existingCustomer = getCustomerFromToken(token);
        String catName = newExpense.getName();

        newExpense.setCustomer(existingCustomer);
        newExpense.setName(catName
                .substring(0, 1)
                .toUpperCase() + catName.substring(1).toLowerCase());
        newExpense.setDateCreated(new Date(System.currentTimeMillis()).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
        newExpense.setDateUpdated(new Date(System.currentTimeMillis()).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());

        try {
            existingExpense = expenseRepository.findExpenseByName(newExpense.getName());
            existingCategory = categoryRepository.findCategoryById(categoryId);
            existingFrequency = frequencyRepository.findFrequencyById(frequencyId);
            existingPriorityLevel = priorityLevelRepository.findPriorityLevelById(priorityLevelId);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingExpense != null) {
            throw new Conflict(StringConstants.EXPENSE_NAME_CONFLICT);
        } else if (existingCategory == null) {
            throw new BadRequest(StringConstants.BAD_CATEGORY);
        } else if (existingFrequency == null) {
            throw new BadRequest(StringConstants.BAD_FREQUENCY);
        } else if (existingPriorityLevel == null) {
            throw new BadRequest(StringConstants.BAD_PRIORITY_LEVEL);
        }

        newExpense.setCategory(existingCategory);
        newExpense.setFrequency(existingFrequency);
        newExpense.setPriorityLevel(existingPriorityLevel);

        try {
            return expenseRepository.save(newExpense);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }

    /**
     * Updates an existing expense if the customer exist and frequency exist
     *
     * @param token           token to get customer from
     * @param categoryId      category id to search for
     * @param frequencyId     frequency id to search for
     * @param priorityLevelId priority level id to search for
     * @param expenseId       expense id to search for
     * @param updatedExpense  update expanse to update
     * @return newly updated expense
     */
    @Override
    public Expense updateExpenseForCustomer(
            String token, Long categoryId, Long frequencyId,
            Long priorityLevelId, Long expenseId, Expense updatedExpense
    ) {
        Expense existingExpense;
        Expense existingName;
        Category existingCategory;
        Frequency existingFrequency;
        PriorityLevel existingPriorityLevel;
        Customer existingCustomer = getCustomerFromToken(token);
        String catName = updatedExpense.getName();

        updatedExpense.setName(catName
                .substring(0, 1)
                .toUpperCase() + catName.substring(1).toLowerCase());
        updatedExpense.setDateUpdated(new Date(System.currentTimeMillis()).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());

        try {
            existingExpense = expenseRepository.findExpenseById(expenseId);
            existingName = expenseRepository.findExpenseByName(updatedExpense.getName());
            existingCategory = categoryRepository.findCategoryById(categoryId);
            existingFrequency = frequencyRepository.findFrequencyById(frequencyId);
            existingPriorityLevel = priorityLevelRepository.findPriorityLevelById(priorityLevelId);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingExpense == null) {
            throw new NotFound(StringConstants.EXPENSE_NOT_FOUND);
        } else if (existingName != null) {
            if (!Objects.equals(existingExpense.getName(), updatedExpense.getName())) {
                throw new Conflict(StringConstants.EXPENSE_NAME_CONFLICT);
            }
        } else if (existingCategory == null) {
            throw new BadRequest(StringConstants.BAD_CATEGORY);
        } else if (existingFrequency == null) {
            throw new BadRequest(StringConstants.BAD_FREQUENCY);
        } else if (existingPriorityLevel == null) {
            throw new BadRequest(StringConstants.BAD_PRIORITY_LEVEL);
        }

        updatedExpense.setId(expenseId);
        updatedExpense.setDateCreated(existingExpense.getDateCreated());
        updatedExpense.setCustomer(existingCustomer);
        updatedExpense.setCategory(existingCategory);
        updatedExpense.setFrequency(existingFrequency);
        updatedExpense.setPriorityLevel(existingPriorityLevel);

        try {
            return expenseRepository.save(updatedExpense);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }

    /**
     * Deletes a given expense if it exists on a customer
     *
     * @param token     token to get customer from
     * @param expenseId expense id for expense to delete
     */
    @Override
    public void deleteExpenseForCustomer(String token, Long expenseId) {
        Expense existingExpense;
        Customer existingCustomer = getCustomerFromToken(token);

        try {
            existingExpense = expenseRepository.findExpenseById(expenseId);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingExpense == null) {
            throw new NotFound(StringConstants.EXPENSE_NOT_FOUND);
        } else if (!existingCustomer.getExpenses().contains(existingExpense)) {
            throw new BadRequest(
                    StringConstants.EXPENSE_DIFF_CUSTOMER
            );
        }

        try {
            expenseRepository.deleteById(expenseId);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }

    /**
     * Helper method to retrieve customer from token
     *
     * @param token token to get customer from
     * @return customer from token
     */
    private Customer getCustomerFromToken(String token) {
        // removes bearer from the token
        token = token.substring(7).trim();

        Customer existingCustomer;
        String customerUsername = jwtUtility.getUsernameFromToken(token);

        try {
            existingCustomer = customerRepository.findCustomerByUsername(customerUsername);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingCustomer == null) {
            throw new NotFound(StringConstants.CUSTOMER_NOT_FOUND);
        }

        return existingCustomer;
    }
}
