package io.thedatapirates.financeapi.domains.customers;

import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.jwts.JwtResponse;
import io.thedatapirates.financeapi.exceptions.*;
import io.thedatapirates.financeapi.utility.JWTUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * A class to implement all methods from the customer service interface
 */
@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

    private final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Gets a customer from the customer repository by username
     *
     * @param email username to search for
     * @return customer with given username
     */
    @Override
    public Customer getCustomer(String email, String token) {
        Customer customer;

        try {
            customer = customerRepository.findCustomerByEmail(email);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        String actualEmail = jwtUtility.getUsernameFromToken(token);

        if (customer == null) {
            throw new NotFound(StringConstants.CUSTOMER_NOT_FOUND);
        } else if (!Objects.equals(customer.getEmail(), actualEmail)) {
            throw new BadRequest(StringConstants.EMAIL_MISMATCH);
        }

        return customer;
    }


    /**
     * Validates refresh token and generates new access token
     *
     * @param refreshToken refresh token to validate
     * @param url          url to use for access token creation
     * @return Jwt response with access/refresher tokens
     */
    @Override
    public JwtResponse refreshCustomerToken(String refreshToken, String url) {
        String token = null;
        String username = null;

        if (refreshToken.startsWith(StringConstants.BEARER_BEGINNING)) {
            token = refreshToken.substring(7).trim();

            try {
                username = jwtUtility.getUsernameFromToken(token);
            } catch (Exception e) {
                logger.error(StringConstants.JWT_ERROR_BEGINNING.concat(e.getMessage()));

                throw new Unauthorized(StringConstants.BAD_TOKEN);
            }

            if (username != null) {
                UserDetails userDetails = loadUserByUsername(username);

                boolean validToken = false;

                try {
                    validToken = jwtUtility.validateToken(token, userDetails);
                } catch (Exception e) {
                    logger.error(StringConstants.JWT_ERROR_BEGINNING.concat(e.getMessage()));

                    throw new Unauthorized(StringConstants.BAD_TOKEN);
                }

                if (validToken) {
                    return new JwtResponse(jwtUtility.generateToken(userDetails, url), token);
                }
            }
        }

        throw new Unauthorized(StringConstants.BAD_TOKEN);
    }

    /**
     * Loads a user by a given username
     *
     * @param username username to search for
     * @return User details based from username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer;

        try {
            customer = customerRepository.findCustomerByEmail(username);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());

            throw new ServerUnavailable(e.getMessage());
        }

        if (customer == null) {
            customer = new Customer(StringConstants.EMPTY_STRING, StringConstants.EMPTY_STRING);
        }

        return new User(customer.getEmail(), customer.getPassword(), new ArrayList<>());
    }
}
