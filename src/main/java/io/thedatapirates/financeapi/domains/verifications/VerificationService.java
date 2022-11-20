package io.thedatapirates.financeapi.domains.verifications;

import io.thedatapirates.financeapi.domains.registration.Registration;

/**
 * Service for verification endpoints
 */
public interface VerificationService {
    void sendVerificationEmailForPassword(String email);

    void sendVerificationEmailForCustomerAccount(Registration registration);

    Boolean confirmAccountWithToken(String token);

    void changePasswordWithToken(String token);

    void updatePasswordOnCustomer(String token, String password);
}
