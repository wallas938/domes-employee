package fr.greta.domes.service;

import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public interface SpecieService {
    List<String> getAll(String categoryName) throws IOException;
}
