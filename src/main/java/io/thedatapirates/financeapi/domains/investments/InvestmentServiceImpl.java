package io.thedatapirates.financeapi.domains.investments;

import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.customers.CustomerRepository;
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

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * A class to implement all methods from the investment service interface
 */
@Service
public class InvestmentServiceImpl implements InvestmentService {

    private final Logger logger = LogManager.getLogger(InvestmentServiceImpl.class);

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private InvestmentRepository investmentRepository;

    /**
     * Calls repository to get a customer and retrieves all investments based on that customers id
     *
     * @param token token to get username for customer
     * @return all investments related to a specified customer
     */
    @Override
    public List<Investment> getInvestmentsByCustomer(String token) {
        Customer existingCustomer = getCustomerFromToken(token);

        try {
            return investmentRepository.findAllByCustomerId(existingCustomer.getId());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }

    /**
     * Creates an investment for a give customer from a give bearer token
     *
     * @param token         token to get customer from
     * @param newInvestment investment to create
     * @return newly created investment
     */
    @Override
    public Investment createInvestmentForCustomer(String token, Investment newInvestment) {
        Investment existingInvestment;
        Customer existingCustomer = getCustomerFromToken(token);
        String catName = newInvestment.getName();

        newInvestment.setCustomer(existingCustomer);
        newInvestment.setName(catName
                .substring(0, 1)
                .toUpperCase() + catName.substring(1).toLowerCase());
        newInvestment.setDateCreated(new Date(System.currentTimeMillis()));
        newInvestment.setDateUpdated(new Date(System.currentTimeMillis()));
        newInvestment.setInvestmentType(StringConstants.STOCK);

        try {
            existingInvestment = investmentRepository.findInvestmentByName(newInvestment.getName());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingInvestment != null) {
            throw new Conflict(StringConstants.INVESTMENT_NAME_CONFLICT);
        }

        try {
            return investmentRepository.save(newInvestment);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }

    /**
     * Updates an investment for a customer from a token id customer and investment exist in the
     * database
     *
     * @param token             token to get customer from
     * @param investmentId      investment id to retrieve investment
     * @param updatedInvestment updated investment
     * @return newly updated investment
     */
    @Override
    public Investment updateInvestmentForCustomer(String token, Long investmentId,
                                                  Investment updatedInvestment) {
        Investment existingInvestment;
        Investment existingName;
        Customer existingCustomer = getCustomerFromToken(token);
        String catName = updatedInvestment.getName();

        updatedInvestment.setName(catName
                .substring(0, 1)
                .toUpperCase() + catName.substring(1).toLowerCase());
        updatedInvestment.setDateUpdated(new Date(System.currentTimeMillis()));
        updatedInvestment.setInvestmentType(StringConstants.STOCK);

        try {
            existingInvestment = investmentRepository.findInvestmentById(investmentId);
            existingName = investmentRepository.findInvestmentByName(updatedInvestment.getName());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingInvestment == null) {
            throw new NotFound(StringConstants.INVESTMENT_NOT_FOUND);
        } else if (existingName != null) {
            if (!Objects.equals(existingInvestment.getName(), updatedInvestment.getName())) {
                throw new Conflict(StringConstants.INVESTMENT_NAME_CONFLICT);
            }
        } else if (!existingCustomer.getInvestments().contains(existingInvestment)) {
            throw new BadRequest(
                    StringConstants.INVESTMENT_DIFF_CUSTOMER
            );
        }

        updatedInvestment.setId(investmentId);
        updatedInvestment.setDateCreated(existingInvestment.getDateCreated());
        updatedInvestment.setCustomer(existingCustomer);

        try {
            return investmentRepository.save(updatedInvestment);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }

    /**
     * Deletes a given investment if it exists on a customer
     *
     * @param token        token to get customer from
     * @param investmentId investment id for investment to delete
     */
    @Override
    public void deleteInvestmentForCustomer(String token, Long investmentId) {
        Investment existingInvestment;
        Customer existingCustomer = getCustomerFromToken(token);

        try {
            existingInvestment = investmentRepository.findInvestmentById(investmentId);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingInvestment == null) {
            throw new NotFound(StringConstants.INVESTMENT_NOT_FOUND);
        } else if (!existingCustomer.getInvestments().contains(existingInvestment)) {
            throw new BadRequest(
                    StringConstants.INVESTMENT_DIFF_CUSTOMER
            );
        }

        try {
            investmentRepository.deleteById(investmentId);
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
