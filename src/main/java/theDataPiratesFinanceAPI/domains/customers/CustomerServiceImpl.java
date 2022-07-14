package theDataPiratesFinanceAPI.domains.customers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import theDataPiratesFinanceAPI.constants.StringConstants;
import theDataPiratesFinanceAPI.domains.jwt.JwtResponse;
import theDataPiratesFinanceAPI.exceptions.BadRequest;
import theDataPiratesFinanceAPI.exceptions.ServerUnavailable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import theDataPiratesFinanceAPI.utility.JWTUtility;

/**
 * A class to implement all methods from the customer service interface
 */
@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

  private final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

  @Autowired
  private JWTUtility jwtUtility;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private CustomerRepository customerRepository;

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

  /**
   * Authenticates given user and generates a Jwt token
   *
   * @param customer customer to authenticate
   * @return Jwt token
   */
  @Override
  public JwtResponse authenticateCustomer(Customer customer) {

    try {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(customer.getUsername(), customer.getPassword())
      );
    } catch (BadCredentialsException e) {
      throw new BadRequest("INVALID CREDENTIALS");
    }

    final UserDetails userDetails = loadUserByUsername(customer.getUsername());

    final String token = jwtUtility.generateToken(userDetails);

    return new JwtResponse(token);
  }

  /**
   * Loads a user by a given username
   *
   * @param username username to search for
   * @return User details based from username
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Customer customer;

    try {
      customer = customerRepository.findCustomerByUsername(username);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());

      throw new ServerUnavailable(e.getMessage());
    }

    return new User(customer.getUsername(), customer.getPassword(), new ArrayList<>());
  }
}
