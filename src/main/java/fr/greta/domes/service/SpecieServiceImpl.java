package fr.greta.domes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import fr.greta.domes.model.Model;
import fr.greta.domes.model.auth.AuthenticationRefreshToken;
import fr.greta.domes.model.auth.AuthenticationToken;
import fr.greta.domes.model.specie.Specie;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpecieServiceImpl implements SpecieService {

    AuthService authService = new AuthServiceImpl();

    @Override
    public Optional<List<String>> getAll(String categoryName) throws IOException {

        Response response = getAllSpeciesRequest(categoryName, Model.getAuthenticationToken().getAccessToken());

        if (response.code() == 202) return Optional.of(speciesNameConverter(response));

        if (response.code() == 401) {
            try {
                AuthenticationToken authenticationToken = authService.renewAccessToken(
                        new AuthenticationRefreshToken(Model.getSubject(), Model.getAuthenticationToken().getRefreshToken())
                ).orElseThrow();

                Response response2 = getAllSpeciesRequest(categoryName, authenticationToken.getAccessToken());

                return Optional.of(speciesNameConverter(response2));
            } catch (Exception e) {
                System.out.println("Thrown by orElseThrow() " + e.getMessage());
            }
        }
        return Optional.empty();
    }

    public Response getAllSpeciesRequest(String categoryName, String accessToken) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(String.format("http://localhost:8081/api/species/categoryName?categoryName=%s", categoryName))
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", accessToken)
                .build();

        Call call = client.newCall(request);

        return call.execute();
    }

    public List<String> speciesNameConverter(Response response) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        ResponseBody responseBody = response.body();

        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Specie.class);

        assert responseBody != null;

        List<Specie> species = objectMapper.readValue(responseBody.byteStream(), listType);

        return species.stream().map(Specie::getName).toList();

    }
}
