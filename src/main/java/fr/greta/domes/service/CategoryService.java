package fr.greta.domes.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<List<String>> getAll() throws IOException;
}
