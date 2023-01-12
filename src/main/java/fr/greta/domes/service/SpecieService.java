package fr.greta.domes.service;

import okhttp3.Response;

import java.io.IOException;

public interface SpecieService {
    Response getAll(String categoryName) throws IOException;
}
