package fr.greta.domes.service;

import fr.greta.domes.model.auth.AuthenticationToken;

import java.io.IOException;
import java.util.Optional;

public interface AuthService {
    Optional<AuthenticationToken> login(String email, String password) throws IOException;
    Optional<AuthenticationToken> renewAccessToken(String refreshToken) throws IOException;
}
