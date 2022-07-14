package theDataPiratesFinanceAPI.domains.customers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import theDataPiratesFinanceAPI.constants.StringConstants;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import theDataPiratesFinanceAPI.constants.Paths;

@RestController
@RequestMapping(value = Paths.CUSTOMER_PATH)
public class CustomerController {

  Logger logger = LogManager.getLogger(CustomerController.class);

  @Autowired
  private CustomerService customerService;

  /**
   * Get method to retrieve all customer
   * @return A list of customerDTOs
   */
  @GetMapping
  public ResponseEntity<List<CustomerDTO>> getCustomers(Customer customer) {
    logger.info(StringConstants.LOG_GET_CUSTOMERS);

    ObjectMapper mapper = new ObjectMapper();

    List<Customer> customers = customerService.getCustomers(customer);

    List<CustomerDTO> customerDTOS = mapper.convertValue(customers, new TypeReference<List<CustomerDTO>>() {});

    return new ResponseEntity<>(customerDTOS, HttpStatus.OK);
  }

  /**
   * Creates a customer in the database
   * @param customer customer to create
   * @return created customer
   */
  @PostMapping
  public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customer) {
    logger.info(StringConstants.LOG_SAVE_CUSTOMER);

    ObjectMapper mapper = new ObjectMapper();

    Customer newCustomer = mapper.convertValue(customer, Customer.class);

    Customer createdCustomer = customerService.saveCustomer(newCustomer);

    CustomerDTO customerDTO = mapper.convertValue(createdCustomer, CustomerDTO.class);

    return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
  }
}
