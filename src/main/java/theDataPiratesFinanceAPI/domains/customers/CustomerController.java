package theDataPiratesFinanceAPI.domains.customers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import theDataPiratesFinanceAPI.constants.StringConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import theDataPiratesFinanceAPI.constants.Paths;
import theDataPiratesFinanceAPI.domains.jwt.JwtRequest;
import theDataPiratesFinanceAPI.domains.jwt.JwtResponse;

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
  @GetMapping("{username}")
  public ResponseEntity<CustomerDTO> getCustomer(@RequestHeader("Authorization") String token, @PathVariable String username) {
    logger.info(StringConstants.LOG_GET_CUSTOMER);

    token = token.substring(7).trim();

    ObjectMapper mapper = new ObjectMapper();

    Customer customer = customerService.getCustomer(username, token);

    CustomerDTO customerDTO = mapper.convertValue(customer, CustomerDTO.class);

    return new ResponseEntity<>(customerDTO, HttpStatus.OK);
  }

  /**
   * Creates a customer in the database
   *
   * @param customerDTO customer to create
   * @return newly created customer
   */
  @PostMapping("create")
  public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
    logger.info(StringConstants.LOG_CREATE_CUSTOMER);

    ObjectMapper mapper = new ObjectMapper();

    Customer newCustomer = mapper.convertValue(customerDTO, Customer.class);

    Customer createdCustomer = customerService.createCustomer(newCustomer);

    CustomerDTO createdCustomerDTO = mapper.convertValue(createdCustomer, CustomerDTO.class);

    return new ResponseEntity<>(createdCustomerDTO, HttpStatus.CREATED);
  }

  /**
   * Authenticates a given jwt request
   *
   * @param jwtRequest jwt request to authenticate
   * @return Jwt token response
   */
  @PostMapping("authenticate")
  public ResponseEntity<JwtResponse> authenticateJwtRequest(@RequestBody JwtRequest jwtRequest) {
    logger.info(StringConstants.LOG_AUTH_JWT_REQUEST);

    JwtResponse jwtResponse = customerService.authenticateJwtRequest(jwtRequest);

    return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
  }
}
