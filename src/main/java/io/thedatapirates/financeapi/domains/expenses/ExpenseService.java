package io.thedatapirates.financeapi.domains.expenses;

import java.util.List;

/**
 * Interface class provides abstraction layer for expense service
 */
public interface ExpenseService {

  List<Expense> getExpenseByCustomer(String token);

  Expense createExpenseForCustomer(String token, Long categoryId, Long frequencyId,
      Long priorityLevelId, Expense newExpense);

  Expense updateExpenseForCustomer(String token, Long categoryId, Long frequencyId,
      Long priorityLevelId, Long expenseId, Expense updatedExpense);

  void deleteExpenseForCustomer(String token, Long expenseId);
}
