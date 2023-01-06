package fr.greta.domes.service;

import fr.greta.domes.model.category.Category;

import java.io.IOException;
import java.util.Collection;

public interface CategoryService {
    Collection<String> getAll() throws IOException;
}
