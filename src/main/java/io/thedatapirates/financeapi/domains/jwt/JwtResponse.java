package io.thedatapirates.financeapi.domains.jwt;

public class JwtResponse {

    private String accessToken;

    private String refresherToken;

    public JwtResponse() {
    }

    public JwtResponse(String accessToken, String refresherToken) {
        this.accessToken = accessToken;
        this.refresherToken = refresherToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefresherToken() {
        return refresherToken;
    }

    public void setRefresherToken(String refresherToken) {
        this.refresherToken = refresherToken;
    }
}
