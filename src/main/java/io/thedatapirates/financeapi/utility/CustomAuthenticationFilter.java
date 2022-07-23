package io.thedatapirates.financeapi.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.domains.jwt.JwtResponse;
import io.thedatapirates.financeapi.exceptions.BadRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Authentication filter to authenticate all request sent to /login endpoint
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final Logger logger = LogManager.getLogger(JwtFilter.class);

    private final JWTUtility jwtUtility;

    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtility jwtUtility) {
        this.authenticationManager = authenticationManager;
        this.jwtUtility = jwtUtility;
    }

    /**
     * Gets username and password from header to authenticate against a customer in the database
     *
     * @param request  http servlet request to get username/password parameters
     * @param response http servlet response
     * @return authentication result based on username and password
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter(StringConstants.USERNAME_PARAM_NAME);
        String password = request.getParameter(StringConstants.PASSWORD_PARAM_NAME);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        try {
            return authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            logger.error(StringConstants.JWT_CREDENTIAL_BEGINNING.concat(e.getMessage()));

            throw new BadRequest(StringConstants.INVALID_LOGIN);
        }
    }

    /**
     * This function gets called on authentication success and creates a token for the
     * user while also adding it to the body for the user to retrieve
     *
     * @param request    http servlet request to add token to body
     * @param response   http servlet response
     * @param chain      filter chain
     * @param authResult authentication results to retrieve user details from
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetails user = (UserDetails) authResult.getPrincipal();

        String accessToken = jwtUtility.generateToken(user, request.getRequestURL().toString());

        String refresherToken = jwtUtility.generateRefresherToken(user, request.getRequestURL().toString());

        response.setContentType(APPLICATION_JSON_VALUE);

        new ObjectMapper().writeValue(response.getOutputStream(), new JwtResponse(accessToken, refresherToken));
    }
}
