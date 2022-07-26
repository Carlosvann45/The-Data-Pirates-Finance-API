package io.thedatapirates.financeapi.domains.customers;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

import io.thedatapirates.financeapi.constants.Paths;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.jwt.JwtResponse;
import io.thedatapirates.financeapi.utility.MapperExtensions;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.experimental.ExtensionMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for customer endpoints
 */
@RestController
@RequestMapping(value = Paths.CUSTOMERS_PATH)
@ExtensionMethod(MapperExtensions.class)
public class CustomerController {

  private final Logger logger = LogManager.getLogger(CustomerController.class);

  @Autowired
  private CustomerService customerService;

  /**
   * Get request to get a customer by username
   *
   * @param username username to search for
   * @return a user with the given username
   */
  @GetMapping(Paths.USERNAME_PATH)
  public ResponseEntity<ResponseCustomerDTO> getCustomer(
      @RequestHeader(AUTHORIZATION) String token, @PathVariable String username
  ) {
    logger.info(StringConstants.LOG_GET_CUSTOMER);

    token = token.substring(7).trim();

    Customer customer = customerService.getCustomer(username, token);

    ResponseCustomerDTO customerDTO = customer.mapCustomerToDTO();

    return new ResponseEntity<>(customerDTO, HttpStatus.OK);
  }

  /**
   * Creates a new Jwt token if refresh token is valid
   *
   * @param refreshToken refresh token to check
   * @return new jwt response with new access and original refresh token
   */
  @GetMapping(Paths.REFRESH_TOKEN_PATH)
  public ResponseEntity<JwtResponse> refreshCustomerToken(
      @RequestHeader(AUTHORIZATION) String refreshToken, HttpServletRequest request
  ) {
    logger.info(StringConstants.LOG_REFRESH_CUSTOMER_TOKEN);

    JwtResponse jwtResponse = customerService.refreshCustomerToken(
        refreshToken, request.getRequestURL().toString()
    );

    return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
  }

  /**
   * Creates a customer in the database
   *
   * @param customerDTO customer to create
   * @return newly created customer
   */
  @PostMapping(Paths.CREATE_PATH)
  public ResponseEntity<ResponseCustomerDTO> createCustomer(
      @Valid @RequestBody RequestCustomerDTO customerDTO) {
    logger.info(StringConstants.LOG_CREATE_CUSTOMER);

    Customer newCustomer = customerDTO.mapDTOToCustomer();

    Customer createdCustomer = customerService.createCustomer(newCustomer);

    ResponseCustomerDTO createdCustomerDTO = createdCustomer.mapCustomerToDTO();

    return new ResponseEntity<>(createdCustomerDTO, HttpStatus.CREATED);
  }
}
