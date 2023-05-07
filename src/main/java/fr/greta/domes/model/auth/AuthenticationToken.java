package fr.greta.domes.model.auth;

public class AuthenticationToken {

    private String accessToken;
    private String refreshToken;

    private Integer statusCode;

    public AuthenticationToken (String accessToken, String refreshToken, Integer statusCode) {
        this.accessToken=accessToken;
        this.refreshToken=refreshToken;
        this.statusCode=statusCode;
    }

    public AuthenticationToken() {}

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "AuthenticationToken{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }
}
