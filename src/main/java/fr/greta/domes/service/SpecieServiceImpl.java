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
import java.util.stream.Collectors;

public class SpecieServiceImpl implements SpecieService {

    AuthService authService = new AuthServiceImpl();

    @Override
    public List<String> getAll(String categoryName) throws IOException {

        Optional<Response> requestResult = getAllRequest(categoryName, Model.getAuthenticationToken().getAccessToken());

        List<String> speciesName = null;

        if (requestResult.isEmpty()) return null;

        else {
            Response response = requestResult.get();
            if (response.code() != 401) {
                if (speciesNameConverter(response).isPresent()) {
                    speciesName = speciesNameConverter(response).get();
                }
            } else {
                Optional<AuthenticationToken> authenticationTokenRefresh = authService.renewAccessToken(new AuthenticationRefreshToken(Model.getSubject(), Model.getAuthenticationToken().getRefreshToken()));
                if (authenticationTokenRefresh.isPresent()) {
                    Optional<Response> secondRequestAttempt = getAllRequest(categoryName, authenticationTokenRefresh.get().getAccessToken());

                    if(secondRequestAttempt.isEmpty()) return null;
                    else {
                        Response secondResponse = secondRequestAttempt.get();

                        if(secondResponse.code() == 403) return null;

                        if (speciesNameConverter(secondResponse).isPresent()) {
                            speciesName = speciesNameConverter(response).get();
                        }
                    }
                }
            }
            return speciesName;
        }

    }

    public Optional<Response> getAllRequest(String categoryName, String accessToken) throws IOException {
        OkHttpClient client = new OkHttpClient();

        ObjectMapper objectMapper = new ObjectMapper();

        Request request = new Request.Builder().url(String.format("http://localhost:8081/api/species/categoryName?categoryName=%s", categoryName))
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", Model.getAuthenticationToken().getAccessToken())
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        return Optional.of(response);
    }

    public Optional<List<String>> speciesNameConverter(Response response) {

        ObjectMapper objectMapper = new ObjectMapper();

        ResponseBody responseBody = response.body();

        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Specie.class);

        assert responseBody != null;

        try {

            List<Specie> species = objectMapper.readValue(responseBody.byteStream(), listType);

            return Optional.of(species.stream().map(Specie::getName).toList());

        } catch (IOException e) {
            return Optional.empty();
        }
    }

//        } catch (Exception e) {
//            if (response.code() == 401) {
//                authService.renewAccessToken(
//                        new AuthenticationRefreshToken(
//                                Model.getSubject(),
//                                Model.getAuthenticationToken().getRefreshToken())
//                ).ifPresentOrElse(authenticationToken -> {
//                    Model.setAuthenticationToken(authenticationToken);
//                    Request request1 = new Request.Builder().url(String.format("http://localhost:8081/api/species/categoryName?categoryName=%s", categoryName))
//                            .addHeader("Content-Type", "application/json")
//                            .addHeader("Authorization", Model.getAuthenticationToken().getAccessToken())
//                            .build();
//
//                    Call call2 = client.newCall(request1);
//
//                    Response response2 = null;
//                    try {
//                        response2 = call2.execute();
//                    } catch (IOException ex) {
//                        throw new RuntimeException(ex);
//                    }
//
//                    ResponseBody responseBody2 = response2.body();
//
//
//                    assert responseBody2 != null;
//
//                    try {
//                        species2 = objectMapper.readValue(responseBody2.byteStream(), listType);
//                    } catch (IOException ex) {
//                        throw new RuntimeException(ex);
//                    }
//
//                }, () -> {
//                });
//            }
//        }
//        assert species != null;
//        return species.stream().map(Specie::getName).toList();
}
