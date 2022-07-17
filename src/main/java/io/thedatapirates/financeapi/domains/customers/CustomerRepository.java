package io.thedatapirates.financeapi.domains.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface to access and query the database
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByUsername(String username);
}
