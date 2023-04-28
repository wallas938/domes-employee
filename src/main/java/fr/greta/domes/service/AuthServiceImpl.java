package fr.greta.domes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.greta.domes.model.Model;
import fr.greta.domes.model.auth.AuthenticationCredentials;
import fr.greta.domes.model.auth.AuthenticationRefreshToken;
import fr.greta.domes.model.auth.AuthenticationToken;
import okhttp3.*;

import java.io.IOException;
import java.util.Optional;

public class AuthServiceImpl implements AuthService {
    @Override
    public Optional<AuthenticationToken> login(String email, String password) throws IOException {
        OkHttpClient client = new OkHttpClient();

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            String json = objectMapper.writeValueAsString(new AuthenticationCredentials(email, password));

            RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);


            Request request = new Request.Builder()
                    .url("http://localhost:8081/api/auth/employee-authentication")
                    .method("POST", body)
                    .build();

            Call call = client.newCall(request);

            Response response = call.execute();

            ResponseBody responseBody = response.body();

            assert responseBody != null;

            return Optional.of(objectMapper.readValue(responseBody.byteStream(), AuthenticationToken.class));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }

    }

    @Override
    public Optional<AuthenticationToken> renewAccessToken(AuthenticationRefreshToken authenticationRefreshToken) throws IOException {
        OkHttpClient client = new OkHttpClient();

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(authenticationRefreshToken);

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url("http://localhost:8081/api/auth/employee/token/refresh")
                .method("POST", body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        try {
            ResponseBody responseBody = response.body();

            assert responseBody != null;

            return Optional.of(objectMapper.readValue(responseBody.byteStream(), AuthenticationToken.class));

        } catch (Exception e) {
            if (response.code() == 403) {
                System.out.println("refresh token is expired too");
                Model.setAuthenticationToken(null);
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
