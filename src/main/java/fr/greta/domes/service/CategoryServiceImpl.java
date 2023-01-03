package fr.greta.domes.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.greta.domes.model.category.Category;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Collection;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public Collection<Category> getAll() {
        return null;
    }
}
