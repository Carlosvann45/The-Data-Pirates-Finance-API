package io.thedatapirates.financeapi.domains.verifications;

import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.customers.Customer;
import io.thedatapirates.financeapi.domains.customers.CustomerRepository;
import io.thedatapirates.financeapi.domains.email.EmailService;
import io.thedatapirates.financeapi.exceptions.ServerUnavailable;
import io.thedatapirates.financeapi.utility.VerificationTypes;
import java.time.LocalDateTime;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * A class to implement all methods from the verified service interface
 */
@Service
public class VerificationServiceImpl implements VerificationService {

  private final Logger logger = LogManager.getLogger(VerificationServiceImpl.class);

  @Autowired
  private EmailService emailService;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private VerificationRepository verificationRepository;

  /**
   * Sends an email to existing customer to change password
   * @param email email address to receive email
   */
  @Override
  public void sendVerificationEmailForPassword(String email) {
    Customer existingCustomer;

    try {
      existingCustomer = customerRepository.findCustomerByUsername(email);
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
      verification.setUsername(existingCustomer.getUsername());
      verification.setToken(token);
      verification.setType(VerificationTypes.Password);

      try {
        verificationRepository.save(verification);
      } catch (DataAccessException e) {
        logger.error(e.getMessage());

        throw new ServerUnavailable(e.getMessage());
      }

      emailService.sendEmail(existingCustomer.getUsername(),
          StringConstants.FORGOT_PASSWORD_SUBJECT,
          StringConstants.GET_PASSWORD_TEMPLATE(
              existingCustomer.getFirstName(), StringConstants.CREATE_FORGOT_PASSWORD_LINK(token)
          ));
    }
  }
}
