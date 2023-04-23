package fr.greta.domes.model.auth;

public class AuthenticationToken {

    private String accessToken;
    private String refreshToken;

    public AuthenticationToken (String accessToken, String refreshToken) {
        this.accessToken=accessToken;
        this.refreshToken=refreshToken;
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

    @Override
    public String toString() {
        return "AuthenticationToken{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
