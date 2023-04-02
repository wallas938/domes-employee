package fr.greta.domes.model.auth;

public class AuthenticationCredentials {
    private String email;
    private String password;

    public AuthenticationCredentials(String email, String password) {
        this.email=email;
        this.password=password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthenticationCredentials{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
