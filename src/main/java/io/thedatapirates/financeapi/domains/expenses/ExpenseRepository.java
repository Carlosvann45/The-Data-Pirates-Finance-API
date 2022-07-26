package io.thedatapirates.financeapi.domains.expenses;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface to access and query the database
 */
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

  List<Expense> findAllByCustomerId(Long customerId);

  Expense findExpenseByName(String name);

  Expense findExpenseById(Long id);
}
