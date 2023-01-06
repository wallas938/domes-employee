package fr.greta.domes.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import fr.greta.domes.model.category.Category;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public Collection<String> getAll() throws IOException {
        OkHttpClient client = new OkHttpClient();

        ObjectMapper objectMapper = new ObjectMapper();

        Request request = new Request.Builder().url("http://localhost:8081/api/categories").build();

        Call call = client.newCall(request);

        Response response = call.execute();

        ResponseBody responseBody = response.body();


        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Category.class);

        List<Category> categories = objectMapper.readValue(responseBody.byteStream(), listType);

        List<String> categoriesNames = categories.stream().map(category -> category.getName()).toList();

        return categoriesNames;
    }

}
