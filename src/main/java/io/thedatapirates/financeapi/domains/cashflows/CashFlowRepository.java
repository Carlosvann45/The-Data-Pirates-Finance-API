package io.thedatapirates.financeapi.domains.cashflows;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface to access and query the database
 */
@Repository
public interface CashFlowRepository extends JpaRepository<CashFlow, Long> {

    List<CashFlow> findAllByCustomerId(Long customerId);

    CashFlow findCashFlowByName(String name);

    CashFlow findCashFlowById(Long id);
}
