package io.thedatapirates.financeapi.domains.withdrawals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface to access and query the database
 */
@Repository
public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {
}
