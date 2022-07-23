package io.thedatapirates.financeapi.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * A class to handle a variety of functions for managing Jwt tokens
 */
@Component
public class JWTUtility implements Serializable {
    private static final long serialVersionUID = 234234523523L;

    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private static final long JWT_REFRESHER_TOKEN_VALIDITY = 86400000;

    @Value("${jwt.secret}")
    private String secretKey;

    /**
     * Gets username from given token
     *
     * @param token token to get username from
     * @return username from token
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Gets expiration date from given token
     *
     * @param token token to get expiration from
     * @return expiration date from token
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Generic function to get specific claims from a token
     *
     * @param token          token to get claims from
     * @param claimsResolver function for getting claim value from
     * @param <T>            generic type for value from claim
     * @return value from generic claim type
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Gets all claims from a token
     *
     * @param token token to get claims from
     * @return all claims from token
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    /**
     * Checks if token as expired
     *
     * @param token token to check expiration date
     * @return whether token has expired
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Generates a new Jwt token
     *
     * @param userDetails user details to create token with
     * @return newly created Jwt token
     */
    public String generateToken(UserDetails userDetails, String url) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername(), url);
    }

    /**
     * Generates a new Refresher Jwt token
     *
     * @param userDetails user details to create token with
     * @return newly created refresher Jwt token
     */
    public String generateRefresherToken(UserDetails userDetails, String url) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateRefresherToken(claims, userDetails.getUsername(), url);
    }

    /**
     * Uses Jwt builder to generate a token
     *
     * @param claims  claims to set token generated claims to
     * @param subject subject to set token generated subject to
     * @return newly generated Jwt token
     */
    private String doGenerateToken(Map<String, Object> claims, String subject, String url) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuer(url)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    /**
     * Uses Jwt builder to generate a refresher token
     *
     * @param claims  claims to set token generated claims to
     * @param subject subject to set token generated subject to
     * @return newly generated refresher Jwt token
     */
    private String doGenerateRefresherToken(Map<String, Object> claims, String subject, String url) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuer(url)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_REFRESHER_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    /**
     * Validates JWT token
     *
     * @param token       token to validate
     * @param userDetails user details to check
     * @return return boolean value based on if token was authenticated
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
