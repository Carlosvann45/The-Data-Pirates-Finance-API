package io.thedatapirates.financeapi.domains.registration;

import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.customers.CustomerRepository;
import io.thedatapirates.financeapi.exceptions.BadRequest;
import io.thedatapirates.financeapi.exceptions.Conflict;
import io.thedatapirates.financeapi.exceptions.NotFound;
import io.thedatapirates.financeapi.exceptions.ServerUnavailable;
import io.thedatapirates.financeapi.utility.MapperExtensions;
import lombok.experimental.ExtensionMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * A class to implement all methods from the registration service interface
 */
@Service
@ExtensionMethod(MapperExtensions.class)
public class RegistrationServiceImpl implements RegistrationService {

    private final Logger logger = LogManager.getLogger(RegistrationServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Creates a new registration if email is does not exist
     *
     * @param newRegistration new registration to create
     * @return newly created registration
     */
    @Override
    public Registration registerCustomer(Registration newRegistration) {
        Customer existingCustomer;
        Registration existingRegistration;

        try {
            existingCustomer = customerRepository.findCustomerByEmail(newRegistration.getEmail());
            existingRegistration = registrationRepository.findRegistrationByEmail(newRegistration.getEmail());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingCustomer != null) {
            throw new Conflict(StringConstants.CUSTOMER_EMAIL_CONFLICT);
        } else if (existingRegistration != null) {
            return existingRegistration;
        }

        newRegistration.setPassword(passwordEncoder.encode(newRegistration.getPassword()));
        newRegistration.setDateCreated(LocalDateTime.now());
        newRegistration.setDateUpdated(LocalDateTime.now());

        try {
            return registrationRepository.save(newRegistration);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }

    /**
     * Converts a registration to a customer account so users can log in
     *
     * @param email email to get registration
     */
    @Override
    public void finishCustomerRegistration(String email) {
        Registration existingRegistration;

        try {
            existingRegistration = registrationRepository.findRegistrationByEmail(email);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingRegistration == null) {
            throw new NotFound(StringConstants.REGISTRATION_NOT_FOUND);
        }

        assert false;

        Customer newCustomer = existingRegistration.mapRegistrationToCustomer();

        newCustomer.setDateCreated(LocalDateTime.now());
        newCustomer.setDateUpdated(LocalDateTime.now());

        try {
            customerRepository.save(newCustomer);

            registrationRepository.delete(existingRegistration);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }
}
