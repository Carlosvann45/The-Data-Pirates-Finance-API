package io.thedatapirates.financeapi.domains.customers;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.thedatapirates.financeapi.constants.Paths;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.cashflows.CashFlow;
import io.thedatapirates.financeapi.domains.cashflows.ResponseCashFlowDTO;
import io.thedatapirates.financeapi.domains.categories.Category;
import io.thedatapirates.financeapi.domains.categories.CategoryDTO;
import io.thedatapirates.financeapi.domains.frequencies.Frequency;
import io.thedatapirates.financeapi.domains.frequencies.FrequencyDTO;
import io.thedatapirates.financeapi.domains.investments.InvestmentDTO;
import io.thedatapirates.financeapi.domains.jwt.JwtResponse;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for customer endpoints
 */
@RestController
@RequestMapping(value = Paths.CUSTOMERS_PATH)
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
    public ResponseEntity<CustomerDTO> getCustomer(
            @RequestHeader(AUTHORIZATION) String token, @PathVariable String username
    ) {
        logger.info(StringConstants.LOG_GET_CUSTOMER);

        token = token.substring(7).trim();

        Customer customer = customerService.getCustomer(username, token);

        CustomerDTO customerDTO = mapCustomerToCustomerDTO(customer);

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
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CreateCustomerDTO customerDTO) {
        logger.info(StringConstants.LOG_CREATE_CUSTOMER);

        ObjectMapper mapper = new ObjectMapper();

        Customer newCustomer = mapper.convertValue(customerDTO, Customer.class);

        Customer createdCustomer = customerService.createCustomer(newCustomer);

        CustomerDTO createdCustomerDTO = mapCustomerToCustomerDTO(createdCustomer);

        return new ResponseEntity<>(createdCustomerDTO, HttpStatus.CREATED);
    }

    /**
     * Maps a customer object to a customer DTO object
     *
     * @param customer customer to convert
     * @return newly create customer DTO
     */
    private CustomerDTO mapCustomerToCustomerDTO(Customer customer) {
        ObjectMapper mapper = new ObjectMapper();

        List<CategoryDTO> categories = customer.getCategories()
                .stream()
                .map(category -> mapper.convertValue(category, CategoryDTO.class))
                .collect(Collectors.toList());

        List<InvestmentDTO> investments = customer.getInvestments()
                .stream()
                .map(investment -> mapper.convertValue(investment, InvestmentDTO.class))
                .collect(Collectors.toList());

        List<ResponseCashFlowDTO> cashFlowItems = customer.getCashFlowItems()
                .stream()
                .map(cashFlowItem -> {
                    ResponseCashFlowDTO cashFlowDTO = new ResponseCashFlowDTO();

                    cashFlowDTO.setId(cashFlowItem.getId());
                    cashFlowDTO.setDateCreated(cashFlowItem.getDateCreated());
                    cashFlowDTO.setDateUpdated(cashFlowItem.getDateUpdated());
                    cashFlowDTO.setName(cashFlowItem.getName());
                    cashFlowDTO.setAmount(cashFlowItem.getAmount());

                    Frequency frequency = cashFlowItem.getFrequency();
                    FrequencyDTO frequencyDTO = new FrequencyDTO();

                    frequencyDTO.setId(frequency.getId());
                    frequencyDTO.setDateCreated(frequency.getDateCreated());
                    frequencyDTO.setDateUpdated(frequency.getDateUpdated());
                    frequencyDTO.setName(frequency.getName());

                    cashFlowDTO.setFrequency(frequencyDTO);

                    return cashFlowDTO;
                })
                .collect(Collectors.toList());

        CustomerDTO newCustomer = new CustomerDTO();

        newCustomer.setId(customer.getId());
        newCustomer.setDateCreated(customer.getDateCreated());
        newCustomer.setDateUpdated(customer.getDateUpdated());
        newCustomer.setUsername(customer.getUsername());
        newCustomer.setPassword(customer.getPassword());
        newCustomer.setCategories(categories);
        newCustomer.setInvestments(investments);
        newCustomer.setCashFlowItems(cashFlowItems);

        return newCustomer;
    }
}
