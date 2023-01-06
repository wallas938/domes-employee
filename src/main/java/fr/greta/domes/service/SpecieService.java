package fr.greta.domes.service;

import fr.greta.domes.model.specie.Specie;
import okhttp3.Response;

import java.io.IOException;
import java.util.Collection;

public interface SpecieService {
    Response getAll(String categoryName) throws IOException;
}
