package fr.greta.domes.service;

import java.io.IOException;

public interface AuthService {
    void login(String email, String password) throws IOException;
}
