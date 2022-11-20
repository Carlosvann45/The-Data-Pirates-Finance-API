package io.thedatapirates.financeapi.domains.verifications;

import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.customers.CustomerRepository;
import io.thedatapirates.financeapi.domains.email.EmailService;
import io.thedatapirates.financeapi.domains.registration.Registration;
import io.thedatapirates.financeapi.exceptions.BadRequest;
import io.thedatapirates.financeapi.exceptions.ServerUnavailable;
import io.thedatapirates.financeapi.utility.VerificationTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A class to implement all methods from the verified service interface
 */
@Service
public class VerificationServiceImpl implements VerificationService {

    private final Logger logger = LogManager.getLogger(VerificationServiceImpl.class);

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VerificationRepository verificationRepository;

    /**
     * Sends an email to existing customer to change password and creates a verification for a customer
     *
     * @param email email address to receive email
     */
    @Override
    public void sendVerificationEmailForPassword(String email) {
        Customer existingCustomer;

        try {
            existingCustomer = customerRepository.findCustomerByEmail(email);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingCustomer != null) {
            String token = UUID.randomUUID().toString();

            Verification verification = new Verification();

            verification.setDateCreated(LocalDateTime.now());
            verification.setDateUpdated(LocalDateTime.now());
            verification.setDateExpires(LocalDateTime.now().plusMinutes(15));
            verification.setToken(token);
            verification.setType(VerificationTypes.Password);
            verification.setCustomer(existingCustomer);

            try {
                verificationRepository.save(verification);
            } catch (DataAccessException e) {
                logger.error(e.getMessage());

                throw new ServerUnavailable(e.getMessage());
            }

            emailService.sendEmail(existingCustomer.getEmail(),
                    StringConstants.FORGOT_PASSWORD_SUBJECT,
                    StringConstants.GET_PASSWORD_TEMPLATE(
                            existingCustomer.getFirstName(), StringConstants.CREATE_FORGOT_PASSWORD_LINK(token)
                    ));
        }
    }

    /**
     * Sends an email for a customer to confirm there account creation
     *
     * @param registration registration to create
     */
    @Override
    public void sendVerificationEmailForCustomerAccount(Registration registration) {
        String token = UUID.randomUUID().toString();

        Verification verification = new Verification();

        verification.setDateCreated(LocalDateTime.now());
        verification.setDateUpdated(LocalDateTime.now());
        verification.setDateExpires(LocalDateTime.now().plusMinutes(15));
        verification.setToken(token);
        verification.setType(VerificationTypes.AccountConfirmation);

        try {
            verificationRepository.save(verification);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        emailService.sendEmail(registration.getEmail(),
                StringConstants.CONFIRMATION_SUBJECT,
                StringConstants.GET_CONFIRMATION_TEMPLATE(
                        registration.getFirstName(), StringConstants.CREATE_CONFIRMATION_LINK(token, registration.getEmail())
                ));
    }

    /**
     * Confirms token for account creation
     *
     * @param token token for verification
     */
    @Override
    public Boolean confirmAccountWithToken(String token) {
        Verification existingVerification;

        try {
            existingVerification = verificationRepository.findVerificationByToken(token);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingVerification == null)
            throw new BadRequest();
        else if (LocalDateTime.now().isAfter(existingVerification.getDateExpires()))
            throw new BadRequest();
        else if (existingVerification.getDateConfirmed() != null)
            return true;

        existingVerification.setDateUpdated(LocalDateTime.now());
        existingVerification.setDateConfirmed(LocalDateTime.now());

        try {
            verificationRepository.save(existingVerification);

            return false;
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }

    /**
     * Searches for verification with a token
     *
     * @param token token to search for
     */
    @Override
    public void changePasswordWithToken(String token) {
        Verification existingVerification;

        try {
            existingVerification = verificationRepository.findVerificationByToken(token);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingVerification == null)
            throw new BadRequest();
        else if (existingVerification.getDateConfirmed() != null)
            throw new BadRequest();
        else if (LocalDateTime.now().isAfter(existingVerification.getDateExpires()))
            throw new BadRequest();
    }

    /**
     * Handles updating the customer information with the new password.
     *
     * @param token    token to get verification for
     * @param password new password for customer
     */
    @Override
    public void updatePasswordOnCustomer(String token, String password) {
        Verification existingVerification;

        try {
            existingVerification = verificationRepository.findVerificationByToken(token);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (existingVerification == null)
            throw new BadRequest();
        else if (existingVerification.getDateConfirmed() != null)
            throw new BadRequest();
        else if (LocalDateTime.now().isAfter(existingVerification.getDateExpires()))
            throw new BadRequest();

        Customer updatedCustomer = existingVerification.getCustomer();

        if (updatedCustomer == null) throw new BadRequest();

        updatedCustomer.setPassword(passwordEncoder.encode(password));
        existingVerification.setDateConfirmed(LocalDateTime.now());

        try {
            customerRepository.save(updatedCustomer);
            verificationRepository.save(existingVerification);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }
    }
}
