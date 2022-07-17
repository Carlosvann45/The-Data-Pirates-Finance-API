package io.thedatapirates.financeapi.domains.customers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.thedatapirates.financeapi.domains.jwt.JwtResponse;
import org.springframework.web.bind.annotation.*;
import io.thedatapirates.financeapi.constants.StringConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.thedatapirates.financeapi.constants.Paths;

import javax.servlet.http.HttpServletRequest;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

/**
 * Controller for customer endpoints
 */
@RestController
@RequestMapping(value = Paths.CUSTOMER_PATH)
public class CustomerController {

  Logger logger = LogManager.getLogger(CustomerController.class);

  @Autowired
  private CustomerService customerService;

  /**
   * Get request to get a customer by username
   *
   * @param username username to search for
   * @return a user with the given username
   */
  @GetMapping(Paths.USERNAME_PATH)
  public ResponseEntity<CustomerDTO> getCustomer(@RequestHeader(AUTHORIZATION) String token, @PathVariable String username) {
    logger.info(StringConstants.LOG_GET_CUSTOMER);

    token = token.substring(7).trim();

    ObjectMapper mapper = new ObjectMapper();

    Customer customer = customerService.getCustomer(username, token);

    CustomerDTO customerDTO = mapper.convertValue(customer, CustomerDTO.class);

    return new ResponseEntity<>(customerDTO, HttpStatus.OK);
  }

  /**
   * Creates a new Jwt token if refresh token is valid
   *
   * @param refreshToken refresh token to check
   * @return new jwt response with new access and original refresh token
   */
  @GetMapping(Paths.REFRESH_TOKEN_PATH)
  public ResponseEntity<JwtResponse> refreshCustomerToken(@RequestHeader(AUTHORIZATION) String refreshToken, HttpServletRequest request) {
    logger.info(StringConstants.LOG_REFRESH_CUSTOMER_TOKEN);

    JwtResponse jwtResponse = customerService.refreshCustomerToken(refreshToken, request.getRequestURL().toString());

    return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
  }

  /**
   * Creates a customer in the database
   *
   * @param customerDTO customer to create
   * @return newly created customer
   */
  @PostMapping(Paths.CREATE_PATH)
  public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
    logger.info(StringConstants.LOG_CREATE_CUSTOMER);

    ObjectMapper mapper = new ObjectMapper();

    Customer newCustomer = mapper.convertValue(customerDTO, Customer.class);

    Customer createdCustomer = customerService.createCustomer(newCustomer);

    CustomerDTO createdCustomerDTO = mapper.convertValue(createdCustomer, CustomerDTO.class);

    return new ResponseEntity<>(createdCustomerDTO, HttpStatus.CREATED);
  }
}
