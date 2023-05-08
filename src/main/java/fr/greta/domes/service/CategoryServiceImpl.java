package fr.greta.domes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import fr.greta.domes.controller.AuthenticationController;
import fr.greta.domes.model.Model;
import fr.greta.domes.model.auth.AuthenticationRefreshToken;
import fr.greta.domes.model.auth.AuthenticationToken;
import fr.greta.domes.model.category.Category;
import okhttp3.*;

import java.io.IOException;
import java.util.*;

public class CategoryServiceImpl implements CategoryService {

    AuthService authService = new AuthServiceImpl();
    @Override
    public Optional<List<String>> getAll() throws IOException {
        Response response = getAllRequest(Model.getAuthenticationToken().getAccessToken());

        if(response.code() == 202) return Optional.of(getAllCategoryNamesConverter(response));

        if (response.code() == 401) {
            try {
                AuthenticationToken authenticationToken = authService.renewAccessToken(
                        new AuthenticationRefreshToken(Model.getSubject(), Model.getAuthenticationToken().getRefreshToken())
                ).orElseThrow();

                Response response2 = getAllRequest(authenticationToken.getAccessToken());

                return Optional.of(getAllCategoryNamesConverter(response2));
            } catch (Exception e) {
                System.out.println("Thrown by orElseThrow() " + e.getMessage());
            }
        }
        Model.setRefreshTokenExpired(true);
        return Optional.empty();
    }

    public Response getAllRequest(String accessToken) throws IOException {
        OkHttpClient client = new OkHttpClient();

        ObjectMapper objectMapper = new ObjectMapper();

        Request request = new Request.Builder()
                .url("http://localhost:8081/api/categories")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", accessToken)
                .build();

        Call call = client.newCall(request);

        return call.execute();
    }

    public List<String> getAllCategoryNamesConverter(Response response) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        ResponseBody responseBody = response.body();

        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Category.class);

        assert responseBody != null;

        List<Category> categories = objectMapper.readValue(responseBody.byteStream(), listType);

        return categories.stream().map(Category::getName).toList();
    }

}
