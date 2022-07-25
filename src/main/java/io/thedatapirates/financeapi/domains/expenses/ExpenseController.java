package io.thedatapirates.financeapi.domains.expenses;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.thedatapirates.financeapi.constants.Paths;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.categories.CategoryDTO;
import io.thedatapirates.financeapi.domains.frequencies.FrequencyDTO;
import io.thedatapirates.financeapi.domains.prioritylevels.PriorityLevelDTO;
import io.thedatapirates.financeapi.domains.reminders.Reminder;
import io.thedatapirates.financeapi.domains.reminders.ResponseReminderDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

/**
 * Controller for expense endpoints
 */
@RestController
@RequestMapping(value = Paths.EXPENSE_PATH)
public class ExpenseController {

    private final Logger logger = LogManager.getLogger(ExpenseController.class);

    @Autowired
    private ExpenseService expenseService;

    /**
     * Gets all expenses related to a customer through a bearer token
     *
     * @param token token to get username of customer from
     * @return all expenses of a given customer
     */
    @GetMapping
    public ResponseEntity<List<ResponseExpenseDTO>> getExpenseByCustomer(
            @RequestHeader(AUTHORIZATION) String token
    ) {
        logger.info(StringConstants.LOG_GET_EXPENSE_CUSTOMER);

        List<Expense> expenseList = expenseService.getExpenseByCustomer(token);

        List<ResponseExpenseDTO> expenseDTOList = expenseList
                .stream()
                .map(this::mapExpenseToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(expenseDTOList, HttpStatus.OK);
    }

    /**
     * Creates an expense for a customer from bearer token
     *
     * @param token      token to get a customer from
     * @param expenseDTO expense to create
     * @return newly created expense
     */
    @PostMapping
    public ResponseEntity<ResponseExpenseDTO> createExpenseForCustomer(
            @RequestHeader(AUTHORIZATION) String token, @Valid @RequestBody RequestExpenseDTO expenseDTO
    ) {
        logger.info(StringConstants.LOG_CREATE_EXPENSE_CUSTOMER);

        ObjectMapper mapper = new ObjectMapper();

        Expense expense = mapper.convertValue(expenseDTO, Expense.class);

        Expense newExpense = expenseService.createExpenseForCustomer(
                token, expenseDTO.getCategoryId(), expenseDTO.getFrequencyId(),
                expenseDTO.getPriorityLevelId(), expense
        );

        ResponseExpenseDTO newExpenseDTO = mapExpenseToDTO(newExpense);

        return new ResponseEntity<>(newExpenseDTO, HttpStatus.CREATED);
    }

    /**
     * Updates an existing expense from a customer bearer token
     *
     * @param token      token to get customer from
     * @param expenseId  expense id for expense to update
     * @param expenseDTO updated expense
     * @return newly updated expense
     */
    @PutMapping(Paths.REMINDER_ID)
    public ResponseEntity<ResponseExpenseDTO> updateReminderForCustomer(
            @RequestHeader(AUTHORIZATION) String token,
            @PathVariable Long expenseId,
            @Valid @RequestBody RequestExpenseDTO expenseDTO
    ) {
        logger.info(StringConstants.LOG_UPDATE_EXPENSE_CUSTOMER);

        ObjectMapper mapper = new ObjectMapper();

        Expense expense = mapper.convertValue(expenseDTO, Expense.class);

        Expense updatedExpense = expenseService.updateExpenseForCustomer(
                token, expenseDTO.getCategoryId(), expenseDTO.getFrequencyId(),
                expenseDTO.getPriorityLevelId(), expenseId, expense
        );

        ResponseExpenseDTO updatedExpenseDTO = mapExpenseToDTO(updatedExpense);

        return new ResponseEntity<>(updatedExpenseDTO, HttpStatus.OK);
    }

    /**
     * Deletes an expense from a user with a specified id
     *
     * @param token     token to get user from
     * @param expenseId expense id to get category
     * @return no content
     */
    @DeleteMapping(Paths.REMINDER_ID)
    public ResponseEntity<?> deleteExpenseForCustomer(
            @RequestHeader(AUTHORIZATION) String token, @PathVariable Long expenseId
    ) {
        logger.info(StringConstants.LOG_DELETE_EXPENSE_CUSTOMER);

        expenseService.deleteExpenseForCustomer(token, expenseId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Maps a expense object to a response expense DTO object
     *
     * @param expense expense to convert
     * @return newly created response expense DTO
     */
    private ResponseExpenseDTO mapExpenseToDTO(Expense expense) {
        ObjectMapper mapper = new ObjectMapper();
        ResponseExpenseDTO expenseDTO = new ResponseExpenseDTO();

        expenseDTO.setId(expense.getId());
        expenseDTO.setDateCreated(expense.getDateCreated());
        expenseDTO.setDateUpdated(expense.getDateUpdated());
        expenseDTO.setName(expense.getName());
        expenseDTO.setAmount(expense.getAmount());
        expenseDTO.setDueDate(expense.getDueDate());
        expenseDTO.setCategory(mapper.convertValue(expense.getCategory(), CategoryDTO.class));
        expenseDTO.setPriorityLevel(mapper.convertValue(expense.getPriorityLevel(), PriorityLevelDTO.class));
        expenseDTO.setFrequency(mapper.convertValue(expense.getFrequency(), FrequencyDTO.class));

        Reminder reminder = expense.getReminder();
        ResponseReminderDTO reminderDTO = new ResponseReminderDTO();

        reminderDTO.setId(reminder.getId());
        reminderDTO.setDateCreated(reminder.getDateCreated());
        reminderDTO.setDateUpdated(reminder.getDateUpdated());
        reminderDTO.setName(reminder.getName());
        reminderDTO.setDescription(reminder.getDescription());
        reminderDTO.setReminderTime(reminder.getReminderTime());
        reminderDTO.setFrequency(mapper.convertValue(reminder.getFrequency(), FrequencyDTO.class));

        expenseDTO.setReminder(reminderDTO);

        return expenseDTO;
    }
}
