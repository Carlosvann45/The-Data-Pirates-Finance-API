package theDataPiratesFinanceAPI.domains.customers;

import theDataPiratesFinanceAPI.domains.jwt.JwtResponse;

import java.util.List;

/**
 * Interface class provides abstraction layer for customer service
 */
public interface CustomerService {

  List<Customer> getCustomers(Customer customer);

  Customer saveCustomer(Customer customer);

  JwtResponse authenticateCustomer(Customer customer);
}
