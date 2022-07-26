package io.thedatapirates.financeapi.domains.cashflows;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface to access and query the database
 */
@Repository
public interface CashFlowRepository extends JpaRepository<CashFlow, Long> {

  List<CashFlow> findAllByCustomerId(Long customerId);

  CashFlow findCashFlowByName(String name);

  CashFlow findCashFlowById(Long id);
}
