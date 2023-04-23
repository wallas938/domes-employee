package fr.greta.domes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import fr.greta.domes.model.Model;
import fr.greta.domes.model.specie.Specie;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpecieServiceImpl implements SpecieService {
    @Override
    public List<String> getAll(String categoryName) throws IOException {

        OkHttpClient client = new OkHttpClient();

        ObjectMapper objectMapper = new ObjectMapper();

        Request request = new Request.Builder().url(String.format("http://localhost:8081/api/species/categoryName?categoryName=%s", categoryName))
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", Model.getAuthenticationToken().getAccessToken())
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        try {

            ResponseBody responseBody = response.body();

            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Specie.class);

            assert responseBody != null;

            List<Specie> species = objectMapper.readValue(responseBody.byteStream(), listType);

            return species.stream().map(Specie::getName).toList();

        } catch (Exception e) {
            if(response.code()==401) {
                AuthService authService = new AuthServiceImpl();
                authService.renewAccessToken(Model.getAuthenticationToken().getRefreshToken());
            }
        }

        return null;
    }
}
