package io.thedatapirates.financeapi.domains.investments;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface to access and query the database
 */
@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {

  List<Investment> findAllByCustomerId(Long customerId);

  Investment findInvestmentByName(String name);

  Investment findInvestmentById(Long id);
}
