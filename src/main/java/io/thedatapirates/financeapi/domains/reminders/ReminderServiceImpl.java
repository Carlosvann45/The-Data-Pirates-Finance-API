package io.thedatapirates.financeapi.domains.reminders;

import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.customers.CustomerRepository;
import io.thedatapirates.financeapi.domains.expenses.Expense;
import io.thedatapirates.financeapi.domains.expenses.ExpenseRepository;
import io.thedatapirates.financeapi.domains.frequencies.Frequency;
import io.thedatapirates.financeapi.domains.frequencies.FrequencyRepository;
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
 * A class to implement all methods from the reminder service interface
 */
@Service
public class ReminderServiceImpl implements ReminderService {

    private final Logger logger = LogManager.getLogger(ReminderServiceImpl.class);

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FrequencyRepository frequencyRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ReminderRepository reminderRepository;

    /**
     * Calls repository to get a customer and retrieves all reminders based on that customers id
     *
     * @param token token to get username for customer
     * @return all reminders related to a specified customer
     */
    @Override
    public List<Reminder> getReminderByCustomer(String token) {
        Customer existingCustomer = getCustomerFromToken(token);

        try {
            return reminderRepository.findAllByCustomerId(existingCustomer.getId());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }

    /**
     * Creates a new reminder for a customer if frequency and customer exist in the database
     *
     * @param token       token to get customer from
     * @param frequencyId frequency id to get frequency
     * @param newReminder new reminder
     * @return newly created reminder
     */
    @Override
    public Reminder createReminderForCustomer(String token, Long frequencyId, Long expenseId,
                                              Reminder newReminder) {
        Reminder existingReminder;
        Frequency existingFrequency;
        Expense existingExpense;
        Customer existingCustomer = getCustomerFromToken(token);
        String catName = newReminder.getName();

        newReminder.setCustomer(existingCustomer);
        newReminder.setName(catName
                .substring(0, 1)
                .toUpperCase() + catName.substring(1).toLowerCase());
        newReminder.setDateCreated(new Date(System.currentTimeMillis()).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
        newReminder.setDateUpdated(new Date(System.currentTimeMillis()).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());

        try {
            existingReminder = reminderRepository.findReminderByName(newReminder.getName());
            existingFrequency = frequencyRepository.findFrequencyById(frequencyId);
            existingExpense = expenseRepository.findExpenseById(expenseId);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingReminder != null) {
            throw new Conflict(StringConstants.REMINDER_NAME_CONFLICT);
        } else if (existingExpense == null) {
            throw new BadRequest(StringConstants.EXPENSE_BAD_ID);
        } else if (existingFrequency == null) {
            throw new BadRequest(StringConstants.BAD_FREQUENCY);
        } else if (existingExpense.getReminders().size() >= 1) throw new BadRequest(
                StringConstants.EXPENSE_HAS_REMINDER
        );

        newReminder.setFrequency(existingFrequency);
        newReminder.setExpense(existingExpense);

        try {
            return reminderRepository.save(newReminder);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }

    /**
     * Updates an existing reminder if the customer exist and frequency exist
     *
     * @param token           token to get customer from
     * @param frequencyId     frequency id to attach to a customer
     * @param reminderId      reminder id for reminder to update
     * @param updatedReminder updated reminder
     * @return newly updated reminder
     */
    @Override
    public Reminder updateReminderForCustomer(
            String token, Long frequencyId, Long reminderId, Long expenseId, Reminder updatedReminder
    ) {
        Reminder existingReminder;
        Reminder existingName;
        Frequency existingFrequency;
        Expense existingExpense;
        Customer existingCustomer = getCustomerFromToken(token);
        String catName = updatedReminder.getName();

        updatedReminder.setName(catName
                .substring(0, 1)
                .toUpperCase() + catName.substring(1).toLowerCase());
        updatedReminder.setDateUpdated(new Date(System.currentTimeMillis()).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());

        try {
            existingReminder = reminderRepository.findReminderById(reminderId);
            existingName = reminderRepository.findReminderByName(updatedReminder.getName());
            existingFrequency = frequencyRepository.findFrequencyById(frequencyId);
            existingExpense = expenseRepository.findExpenseById(expenseId);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingReminder == null) {
            throw new NotFound(StringConstants.REMINDER_NOT_FOUND);
        } else if (existingExpense == null) {
            throw new BadRequest(StringConstants.EXPENSE_BAD_ID);
        } else if (existingName != null) {
            if (!Objects.equals(existingReminder.getName(), updatedReminder.getName())) {
                throw new Conflict(StringConstants.REMINDER_NAME_CONFLICT);
            }
        } else if (existingFrequency == null) {
            throw new BadRequest(StringConstants.BAD_FREQUENCY);
        }

        updatedReminder.setId(reminderId);
        updatedReminder.setDateCreated(existingReminder.getDateCreated());
        updatedReminder.setCustomer(existingCustomer);
        updatedReminder.setFrequency(existingFrequency);
        updatedReminder.setExpense(existingExpense);

        try {
            return reminderRepository.save(updatedReminder);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }

    /**
     * Deletes a given reminder if it exists on a customer
     *
     * @param token      token to get customer from
     * @param reminderId reminder id for reminder to delete
     */
    @Override
    public void deleteReminderForCustomer(String token, Long reminderId) {
        Reminder existingReminder;
        Customer existingCustomer = getCustomerFromToken(token);

        try {
            existingReminder = reminderRepository.findReminderById(reminderId);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingReminder == null) {
            throw new NotFound(StringConstants.REMINDER_NOT_FOUND);
        } else if (!existingCustomer.getReminders().contains(existingReminder)) {
            throw new BadRequest(
                    StringConstants.REMINDER_DIFF_CUSTOMER
            );
        }

        try {
            reminderRepository.deleteById(reminderId);
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
