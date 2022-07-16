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
import theDataPiratesFinanceAPI.domains.jwt.JwtResponse;

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
  public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String username) {
    logger.info(StringConstants.LOG_GET_CUSTOMER);

    ObjectMapper mapper = new ObjectMapper();

    Customer customer = customerService.getCustomer(username);

    CustomerDTO customerDTO = mapper.convertValue(customer, CustomerDTO.class);

    return new ResponseEntity<>(customerDTO, HttpStatus.OK);
  }

  /**
   * Authenticates a given customer
   *
   * @param customer customer to authenticate
   * @return Jwt token response
   */
  @PostMapping("authenticate")
  public JwtResponse authenticateCustomer(@RequestBody CustomerDTO customer) {
    logger.info(StringConstants.LOG_AUTH_CUSTOMER);

    ObjectMapper mapper = new ObjectMapper();

    Customer customerToAuth = mapper.convertValue(customer, Customer.class);

    return customerService.authenticateCustomer(customerToAuth);
  }
}
