package io.thedatapirates.financeapi.domains.deposits;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface to access and query the database
 */
@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
