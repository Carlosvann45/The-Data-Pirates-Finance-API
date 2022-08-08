package io.thedatapirates.financeapi.domains.cashflows;

import com.google.api.client.util.DateTime;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.customers.CustomerRepository;
import io.thedatapirates.financeapi.domains.frequencies.Frequency;
import io.thedatapirates.financeapi.domains.frequencies.FrequencyRepository;
import io.thedatapirates.financeapi.exceptions.BadRequest;
import io.thedatapirates.financeapi.exceptions.Conflict;
import io.thedatapirates.financeapi.exceptions.NotFound;
import io.thedatapirates.financeapi.exceptions.ServerUnavailable;
import io.thedatapirates.financeapi.utility.JWTUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * A class to implement all methods from the cash flow service interface
 */
@Service
public class CashFlowServiceImpl implements CashFlowService {

    private final Logger logger = LogManager.getLogger(CashFlowServiceImpl.class);

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FrequencyRepository frequencyRepository;

    @Autowired
    private CashFlowRepository cashFlowRepository;

    /**
     * Calls repository to get a customer and retrieves all cash flow items based on that customers
     * id
     *
     * @param token token to get username for customer
     * @return all cash flow items related to a specified customer
     */
    @Override
    public List<CashFlow> getCashFlowByCustomer(String token) {
        Customer existingCustomer = getCustomerFromToken(token);

        try {
            return cashFlowRepository.findAllByCustomerId(existingCustomer.getId());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }

    /**
     * Creates a new cash flow for a customer if frequency and customer exist in the database
     *
     * @param token           token to get customer from
     * @param frequencyId     frequency id to get frequency
     * @param newCashFlowItem new cash flow item
     * @return newly created cash flow item
     */
    @Override
    public CashFlow createCashFlowForCustomer(String token, Long frequencyId,
                                              CashFlow newCashFlowItem) {
        CashFlow existingCashFlow;
        Frequency existingFrequency;
        Customer existingCustomer = getCustomerFromToken(token);
        String catName = newCashFlowItem.getName();

        newCashFlowItem.setCustomer(existingCustomer);
        newCashFlowItem.setName(catName
                .substring(0, 1)
                .toUpperCase() + catName.substring(1).toLowerCase());
        newCashFlowItem.setDateCreated(new Date(System.currentTimeMillis()).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
        newCashFlowItem.setDateUpdated(new Date(System.currentTimeMillis()).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());

        try {
            existingCashFlow = cashFlowRepository.findCashFlowByName(newCashFlowItem.getName());
            existingFrequency = frequencyRepository.findFrequencyById(frequencyId);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingCashFlow != null) {
            throw new Conflict(StringConstants.CASH_FLOW_NAME_CONFLICT);
        } else if (existingFrequency == null) {
            throw new BadRequest(StringConstants.BAD_FREQUENCY);
        }

        newCashFlowItem.setFrequency(existingFrequency);

        try {
            return cashFlowRepository.save(newCashFlowItem);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }

    /**
     * Updates an existing cash flow item if the customer exist and frequency exist
     *
     * @param token               token to get customer from
     * @param frequencyId         frequency id to attach to a customer
     * @param cashFlowId          cash flow id for cash flow to update
     * @param updatedCashFlowItem updated cash flow item
     * @return newly updated cash flow item
     */
    @Override
    public CashFlow updateCashFlowForCustomer(String token, Long frequencyId, Long cashFlowId,
                                              CashFlow updatedCashFlowItem) {
        CashFlow existingCashFlow;
        CashFlow existingName;
        Frequency existingFrequency;
        Customer existingCustomer = getCustomerFromToken(token);
        String catName = updatedCashFlowItem.getName();

        updatedCashFlowItem.setName(catName
                .substring(0, 1)
                .toUpperCase() + catName.substring(1).toLowerCase());
        updatedCashFlowItem.setDateUpdated(new Date(System.currentTimeMillis()).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());

        try {
            existingCashFlow = cashFlowRepository.findCashFlowById(cashFlowId);
            existingName = cashFlowRepository.findCashFlowByName(updatedCashFlowItem.getName());
            existingFrequency = frequencyRepository.findFrequencyById(frequencyId);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingCashFlow == null) {
            throw new NotFound(StringConstants.CASH_FLOW_NOT_FOUND);
        } else if (existingName != null) {
            if (!Objects.equals(existingCashFlow.getName(), updatedCashFlowItem.getName())) {
                throw new Conflict(StringConstants.CASH_FLOW_NAME_CONFLICT);
            }
        } else if (existingFrequency == null) {
            throw new BadRequest(StringConstants.BAD_FREQUENCY);
        }

        updatedCashFlowItem.setId(cashFlowId);
        updatedCashFlowItem.setDateCreated(existingCashFlow.getDateCreated());
        updatedCashFlowItem.setCustomer(existingCustomer);
        updatedCashFlowItem.setFrequency(existingFrequency);

        try {
            return cashFlowRepository.save(updatedCashFlowItem);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }

    /**
     * Deletes a given cash flow item if it exists on a customer
     *
     * @param token      token to get customer from
     * @param cashFlowId cash flow id for cash flow to delete
     */
    @Override
    public void deleteCashFlowForCustomer(String token, Long cashFlowId) {
        CashFlow existingCashFlow;
        Customer existingCustomer = getCustomerFromToken(token);

        try {
            existingCashFlow = cashFlowRepository.findCashFlowById(cashFlowId);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingCashFlow == null) {
            throw new NotFound(StringConstants.CASH_FLOW_NOT_FOUND);
        } else if (!existingCustomer.getCashFlowItems().contains(existingCashFlow)) {
            throw new BadRequest(
                    StringConstants.CASH_FLOW_DIFF_CUSTOMER
            );
        }

        try {
            cashFlowRepository.deleteById(cashFlowId);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }

    /**
     * Helper method to retrieve customer from token
     *
     * @param token token to get customer from
     * @return customer from token
     */
    private Customer getCustomerFromToken(String token) {
        // removes bearer from the token
        token = token.substring(7).trim();

        Customer existingCustomer;
        String customerUsername = jwtUtility.getUsernameFromToken(token);

        try {
            existingCustomer = customerRepository.findCustomerByUsername(customerUsername);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingCustomer == null) {
            throw new NotFound(StringConstants.CUSTOMER_NOT_FOUND);
        }

        return existingCustomer;
    }
}
