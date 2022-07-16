package theDataPiratesFinanceAPI.domains.customers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import theDataPiratesFinanceAPI.constants.StringConstants;
import theDataPiratesFinanceAPI.domains.jwt.JwtRequest;
import theDataPiratesFinanceAPI.domains.jwt.JwtResponse;
import theDataPiratesFinanceAPI.exceptions.BadRequest;
import theDataPiratesFinanceAPI.exceptions.Conflict;
import theDataPiratesFinanceAPI.exceptions.NotFound;
import theDataPiratesFinanceAPI.exceptions.ServerUnavailable;

import java.util.ArrayList;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
   * Gets a customer from the customer repository by username
   *
   * @param username username to search for
   * @return customer with given username
   */
  @Override
  public Customer getCustomer(String username, String token) {
    Customer customer;

    try {
      customer = customerRepository.findCustomerByUsername(username);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());

      throw new ServerUnavailable(e.getMessage());
    }

    String actualUsername = jwtUtility.getUsernameFromToken(token);

    if (customer == null) throw new NotFound(StringConstants.CUSTOMER_NOT_FOUND);
    else if (!Objects.equals(customer.getUsername(), actualUsername)) throw new BadRequest(StringConstants.USERNAME_MISMATCH);

    return customer;
  }

  /**
   * Creates customer in database if username doesn't exist
   *
   * @param newCustomer customer to add to database
   * @return new customer created
   */
  @Override
  public Customer createCustomer(Customer newCustomer) {
    Customer existingCustomer;

    try {
      existingCustomer = customerRepository.findCustomerByUsername(newCustomer.getUsername());
    } catch(DataAccessException e) {
      logger.error(e.getMessage());

      throw new ServerUnavailable(e.getMessage());
    }

    if (existingCustomer != null) throw new Conflict(StringConstants.USERNAME_CONFLICT);

    try {
      return customerRepository.save(newCustomer);
    } catch(DataAccessException e) {
      logger.error(e.getMessage());

      throw new ServerUnavailable(e.getMessage());
    }
  }

  /**
   * Authenticates given jwt request and generates a Jwt token
   *
   * @param jwtRequest jwt request to authenticate
   * @return Jwt token
   */
  @Override
  public JwtResponse authenticateJwtRequest(JwtRequest jwtRequest) {

    try {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
      );
    } catch (BadCredentialsException e) {
      throw new BadRequest(StringConstants.INVALID_LOGIN);
    }

    final UserDetails userDetails = loadUserByUsername(jwtRequest.getUsername());

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

    if (customer == null) customer = new Customer(" ", " ");

    return new User(customer.getUsername(), customer.getPassword(), new ArrayList<>());
  }
}
