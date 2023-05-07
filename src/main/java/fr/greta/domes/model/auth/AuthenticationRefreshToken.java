package fr.greta.domes.model.auth;

public class AuthenticationRefreshToken {
    private String email;
    private String refreshToken;

    public AuthenticationRefreshToken(String email, String refreshToken) {
        this.email = email;
        this.refreshToken = refreshToken;
    }

    public AuthenticationRefreshToken() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "AuthenticationRefreshToken{" +
                "email='" + email + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
