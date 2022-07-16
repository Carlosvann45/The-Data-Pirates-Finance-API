package theDataPiratesFinanceAPI.domains.customers;

import theDataPiratesFinanceAPI.domains.jwt.JwtResponse;

/**
 * Interface class provides abstraction layer for customer service
 */
public interface CustomerService {

  Customer getCustomer(String username);

  JwtResponse authenticateCustomer(Customer customer);
}
