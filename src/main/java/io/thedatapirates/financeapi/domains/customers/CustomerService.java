package io.thedatapirates.financeapi.domains.customers;

import io.thedatapirates.financeapi.domains.jwts.JwtResponse;

/**
 * Interface class provides abstraction layer for customer service
 */
public interface CustomerService {

    Customer getCustomer(String username, String token);

    Customer createCustomer(Customer newCustomer);

    JwtResponse refreshCustomerToken(String refreshToken, String url);
}
