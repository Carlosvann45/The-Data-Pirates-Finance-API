package io.thedatapirates.financeapi.utility;

import io.thedatapirates.financeapi.constants.StringConstants;
import io.thedatapirates.financeapi.exceptions.BadRequest;
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

/**
 * Authentication filter to authenticate all request sent to /login endpoint
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JWTUtility jwtUtility;

    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtility jwtUtility) {
        this.authenticationManager = authenticationManager;
        this.jwtUtility = jwtUtility;
    }

    /**
     * Gets username and password from header to authenticate against a customer in the database
     *
     * @param request http servlet request to get username/password parameters
     * @param response http servlet response
     * @return authentication result based on username and password
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        try {
            return authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException e) {
            throw new BadRequest(StringConstants.INVALID_LOGIN);
        }
    }

    /**
     * This function gets called on authentication success and creates a token for the
     * user while also adding it to the header for the user to retrieve
     *
     * @param request http servlet request to add token to header
     * @param response http servlet response
     * @param chain filter chain
     * @param authResult authentication results to retrieve user details from
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetails user = (UserDetails) authResult.getPrincipal();

        String accessToken = jwtUtility.generateToken(user, request.getRequestURL().toString());

        response.setHeader("access_token", accessToken);
    }
}
