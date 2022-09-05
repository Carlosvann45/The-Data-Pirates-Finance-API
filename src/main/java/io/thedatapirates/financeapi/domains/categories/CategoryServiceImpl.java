package io.thedatapirates.financeapi.domains.categories;

import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.customers.CustomerRepository;
import io.thedatapirates.financeapi.domains.customers.CustomerServiceImpl;
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

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * A class to implement all methods from the category service interface
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Gets all categories from the repository
     *
     * @return a list of categories
     */
    @Override
    public List<Category> getCategoriesByCustomer() {
        try {
            return categoryRepository.findAll();
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }
}
