package io.thedatapirates.financeapi.domains.registration;

/**
 * Service for registration endpoints
 */
public interface RegistrationService {

    Registration registerCustomer(Registration newCustomer);

    void finishCustomerRegistration(String email);
}
