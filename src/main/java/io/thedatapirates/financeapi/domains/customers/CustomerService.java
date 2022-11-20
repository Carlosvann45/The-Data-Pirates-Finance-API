package io.thedatapirates.financeapi.domains.customers;

import io.thedatapirates.financeapi.domains.jwts.JwtResponse;

/**
 * Interface class provides abstraction layer for customer service
 */
public interface CustomerService {

    Customer getCustomer(String email, String token);

    JwtResponse refreshCustomerToken(String refreshToken, String url);
}
