package io.thedatapirates.financeapi.domains.expenses;

import io.thedatapirates.financeapi.constants.Paths;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.withdrawals.Withdrawal;
import io.thedatapirates.financeapi.domains.withdrawals.WithdrawalDTO;
import io.thedatapirates.financeapi.utility.MapperExtensions;
import lombok.experimental.ExtensionMethod;
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
@ExtensionMethod(MapperExtensions.class)
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
                .map(expense -> expense.mapExpenseToDTO())
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

        Expense expense = expenseDTO.mapDTOToExpense();

        Expense newExpense = expenseService.createExpenseForCustomer(
                token, expenseDTO.getCategoryId(), expenseDTO.getFrequencyId(),
                expenseDTO.getPriorityLevelId(), expense
        );

        ResponseExpenseDTO newExpenseDTO = newExpense.mapExpenseToDTO();

        return new ResponseEntity<>(newExpenseDTO, HttpStatus.CREATED);
    }

    /**
     * Creates a new withdrawal item for an expense
     *
     * @param token         token to get customer
     * @param expenseId     expense d to find expense
     * @param withdrawalDTO withdrawal to create
     * @return updated expense
     */
    @PutMapping(Paths.WITHDRAWAL_FOR_EXPENSE + Paths.EXPENSE_ID)
    public ResponseEntity<ResponseExpenseDTO> withdrawalExpenseForCustomer(
            @RequestHeader(AUTHORIZATION) String token,
            @PathVariable Long expenseId,
            @Valid @RequestBody WithdrawalDTO withdrawalDTO
    ) {
        logger.info(StringConstants.LOG_WITHDRAWAL_EXPENSE_CUSTOMER);

        Withdrawal withdrawal = withdrawalDTO.mapDTOToWithdrawal();

        Expense updatedExpense = expenseService.withdrawalExpenseForCustomer(token, expenseId, withdrawal);

        ResponseExpenseDTO expenseDTO = updatedExpense.mapExpenseToDTO();

        return new ResponseEntity<>(expenseDTO, HttpStatus.OK);
    }


    /**
     * Updates an existing expense from a customer bearer token
     *
     * @param token      token to get customer from
     * @param expenseId  expense id for expense to update
     * @param expenseDTO updated expense
     * @return newly updated expense
     */
    @PutMapping(Paths.EXPENSE_ID)
    public ResponseEntity<ResponseExpenseDTO> updateReminderForCustomer(
            @RequestHeader(AUTHORIZATION) String token,
            @PathVariable Long expenseId,
            @Valid @RequestBody RequestExpenseDTO expenseDTO
    ) {
        logger.info(StringConstants.LOG_UPDATE_EXPENSE_CUSTOMER);

        Expense expense = expenseDTO.mapDTOToExpense();

        Expense updatedExpense = expenseService.updateExpenseForCustomer(
                token, expenseDTO.getCategoryId(), expenseDTO.getFrequencyId(),
                expenseDTO.getPriorityLevelId(), expenseId, expense
        );

        ResponseExpenseDTO updatedExpenseDTO = updatedExpense.mapExpenseToDTO();

        return new ResponseEntity<>(updatedExpenseDTO, HttpStatus.OK);
    }

    /**
     * Deletes an expense from a user with a specified id
     *
     * @param token     token to get user from
     * @param expenseId expense id to get category
     * @return no content
     */
    @DeleteMapping(Paths.EXPENSE_ID)
    public ResponseEntity<?> deleteExpenseForCustomer(
            @RequestHeader(AUTHORIZATION) String token, @PathVariable Long expenseId
    ) {
        logger.info(StringConstants.LOG_DELETE_EXPENSE_CUSTOMER);

        expenseService.deleteExpenseForCustomer(token, expenseId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
