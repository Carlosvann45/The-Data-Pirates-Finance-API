package io.thedatapirates.financeapi.domains.verifications;

import io.thedatapirates.financeapi.domains.customers.Customer;

/**
 * Service for verification endpoints
 */
public interface VerificationService {
    void sendVerificationEmailForPassword(String email);

    void changePasswordWithToken(String token);

    void updatePasswordOnCustomer(String token, String password);
}
