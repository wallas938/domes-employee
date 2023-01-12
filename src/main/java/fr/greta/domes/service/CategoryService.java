package fr.greta.domes.service;

import java.io.IOException;
import java.util.Collection;

public interface CategoryService {
    Collection<String> getAll() throws IOException;
}
