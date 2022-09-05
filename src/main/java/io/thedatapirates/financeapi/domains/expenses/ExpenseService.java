package io.thedatapirates.financeapi.domains.expenses;

import io.thedatapirates.financeapi.domains.withdrawals.Withdrawal;

import java.util.List;

/**
 * Interface class provides abstraction layer for expense service
 */
public interface ExpenseService {

    List<Expense> getExpenseByCustomer(String token);

    Expense createExpenseForCustomer(String token, Long categoryId, Long frequencyId,
                                     Long priorityLevelId, Expense newExpense);

    Expense withdrawalExpenseForCustomer(String token, Long expenseId, Withdrawal newWithdrawal);

    Expense updateExpenseForCustomer(String token, Long categoryId, Long frequencyId,
                                     Long priorityLevelId, Long expenseId, Expense updatedExpense);

    void deleteExpenseForCustomer(String token, Long expenseId);
}
