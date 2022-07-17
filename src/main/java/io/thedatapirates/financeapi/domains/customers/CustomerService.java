package io.thedatapirates.financeapi.domains.customers;

/**
 * Interface class provides abstraction layer for customer service
 */
public interface CustomerService {

  Customer getCustomer(String username, String token);

  Customer createCustomer(Customer newCustomer);
}
