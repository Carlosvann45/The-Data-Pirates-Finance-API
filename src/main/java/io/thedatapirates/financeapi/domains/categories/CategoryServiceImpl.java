package io.thedatapirates.financeapi.domains.categories;

import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.customers.CustomerRepository;
import io.thedatapirates.financeapi.domains.customers.CustomerServiceImpl;
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

/**
 * A class to implement all methods from the category service interface
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    private final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Calls repository to get a customer and retrieves all
     * categories based on that customers id
     *
     * @param token token to get username for customer
     * @return all categories related to a specified customer
     */
    @Override
    public List<Category> getCategoriesByCustomer(String token) {
        Customer existingCustomer = getCustomerFromToken(token);

        try {
            return categoryRepository.findAllByCustomerId(existingCustomer.getId());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }

    /**
     * Creates a category for a give customer from a
     * give bearer token
     *
     * @param token token to get customer from
     * @param newCategory category to create
     * @return newly created category
     */
    @Override
    public Category createCategoryForCustomer(String token, Category newCategory) {
        Category existingCategory;
        Customer existingCustomer = getCustomerFromToken(token);
        String catName = newCategory.getName();

        newCategory.setCustomer(existingCustomer);
        newCategory.setName(catName
                .substring(0,1)
                .toUpperCase() + catName.substring(1).toLowerCase());
        newCategory.setDateCreated(new Date(System.currentTimeMillis()));
        newCategory.setDateUpdated(new Date(System.currentTimeMillis()));

        try {
            existingCategory = categoryRepository.findCategoryByName(newCategory.getName());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if(existingCategory != null) throw new Conflict(StringConstants.CATEGORY_NAME_CONFLICT);

        try {
            newCategory = categoryRepository.save(newCategory);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        newCategory.setCustomer(existingCustomer);

        return newCategory;
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

        if (existingCustomer == null ) throw new NotFound(StringConstants.CUSTOMER_NOT_FOUND);

        return existingCustomer;
    }
}
