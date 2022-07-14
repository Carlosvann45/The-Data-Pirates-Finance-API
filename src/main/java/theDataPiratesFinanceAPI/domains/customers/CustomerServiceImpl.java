package theDataPiratesFinanceAPI.domains.customers;

import theDataPiratesFinanceAPI.constants.StringConstants;
import theDataPiratesFinanceAPI.exceptions.BadRequest;
import theDataPiratesFinanceAPI.exceptions.ServerUnavailable;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * A class to implement all methods from the customer service interface
 */
@Service
public class CustomerServiceImpl implements CustomerService {

  private final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

  private CustomerRepository customerRepository;

  @Autowired
  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  /**
   * Gets all customers from the customer repository
   * @param customer base customer to search for
   * @return a list of customers
   */
  @Override
  public List<Customer> getCustomers(Customer customer) {
    try {
      return customerRepository.findAll(Example.of(customer));
    } catch (DataAccessException e) {
      logger.error(e.getMessage());

      throw new ServerUnavailable(e.getMessage());
    }
  }

  /**
   * Saves customer to repository
   * @param customer customer to save
   * @return saved customer from repository
   */
  @Override
  public Customer saveCustomer(Customer customer) {
    if (customer.getUsername() == null) throw new BadRequest(StringConstants.CUSTOMER_USERNAME_NULL_400);
    else if (customer.getPassword() == null) throw new BadRequest(StringConstants.CUSTOMER_PASSWORD_NULL_400);

    try {
      return customerRepository.save(customer);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());

      throw new ServerUnavailable(e.getMessage());
    }
  }
}
