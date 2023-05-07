package fr.greta.domes.service;

import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface SpecieService {
    Optional<List<String>> getAll(String categoryName) throws IOException;
}
