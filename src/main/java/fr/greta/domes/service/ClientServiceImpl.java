package fr.greta.domes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.greta.domes.controller.NavigationController;
import fr.greta.domes.controller.client.ClientController;
import fr.greta.domes.controller.client.ClientSearchQuery;
import fr.greta.domes.model.Model;
import fr.greta.domes.model.Navigation;
import fr.greta.domes.model.auth.AuthenticationRefreshToken;
import fr.greta.domes.model.auth.AuthenticationToken;
import fr.greta.domes.model.client.ClientPage;
import fr.greta.domes.model.client.ClientPutDTO;
import okhttp3.*;

import java.io.IOException;
import java.util.Optional;

public class ClientServiceImpl implements ClientService {
    private final AuthService authService = new AuthServiceImpl();

    @Override
    public Optional<ClientPage> getClientPage(ClientSearchQuery csq) throws IOException {

        Response response = getClientPageRequest(csq, Model.getAuthenticationToken().getAccessToken());

        if (response.code() == 202) return Optional.of(clientPageConverter(response));

        if (response.code() == 401) {
            try {
                AuthenticationToken authenticationToken = authService.renewAccessToken(
                        new AuthenticationRefreshToken(Model.getSubject(), Model.getAuthenticationToken().getRefreshToken())
                ).orElseThrow();

                Response response2 = getClientPageRequest(csq, authenticationToken.getAccessToken());

                return Optional.of(clientPageConverter(response2));

            } catch (Exception e) {
                System.out.println("Thrown by orElseThrow() " + e.getMessage());
            }
            return Optional.empty();
        }
        return Optional.empty();
    }

    public Response getClientPageRequest(ClientSearchQuery clientSearchQuery, String accessToken) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = "http://localhost:8081/api/clients/init?lastname=%s&firstname=%s&phoneNumber=%s&email=%s&pageNumber=%s&pageSize=%s";

        // FETCH clients
        Request request = new Request.Builder().url(String.format(url,
                        clientSearchQuery.getLastname(),
                        clientSearchQuery.getFirstname(),
                        clientSearchQuery.getPhoneNumber(),
                        clientSearchQuery.getEmail(),
                        clientSearchQuery.getPageNumber() - 1,
                        clientSearchQuery.getPageSize()))
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", accessToken)
                .build();

        Call call = client.newCall(request);

        return call.execute();
    }

    public ClientPage clientPageConverter(Response response) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

        ResponseBody responseBody = response.body();

        assert responseBody != null;

        return objectMapper.readValue(responseBody.byteStream(), ClientPage.class);
    }

    @Override
    public Optional<ClientPage> searchBarGetClients(ClientSearchQuery csq) throws IOException {

        Response response = searchBarGetClientsRequest(csq, Model.getAuthenticationToken().getAccessToken());

        if (response.code() == 202) return Optional.of(searchClientResultPageConverter(response));

        if (response.code() == 401) {
            try {
                AuthenticationToken authenticationToken = authService.renewAccessToken(
                        new AuthenticationRefreshToken(Model.getSubject(), Model.getAuthenticationToken().getRefreshToken())
                ).orElseThrow();

                Response response2 = searchBarGetClientsRequest(csq, authenticationToken.getAccessToken());

                return Optional.of(searchClientResultPageConverter(response2));

            } catch (Exception e) {
                System.out.println("Thrown by orElseThrow() " + e.getMessage());
            }
            Model.setRefreshTokenExpired(true);
            return Optional.empty();
        }
        Model.setRefreshTokenExpired(true);
        return Optional.empty();
    }

    public Response searchBarGetClientsRequest(ClientSearchQuery clientSearchQuery, String accessToken) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = "http://localhost:8081/api/clients/search?lastname=%s&firstname=%s&phoneNumber=%s&email=%s&pageNumber=%s&pageSize=%s";

        // FETCH clients
        Request request = new Request.Builder().url(String.format(url,
                        clientSearchQuery.getLastname(),
                        clientSearchQuery.getFirstname(),
                        clientSearchQuery.getPhoneNumber(),
                        clientSearchQuery.getEmail(),
                        clientSearchQuery.getPageNumber() - 1,
                        clientSearchQuery.getPageSize()))
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", accessToken)
                .build();

        Call call = client.newCall(request);

        return call.execute();
    }

    public ClientPage searchClientResultPageConverter(Response response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

        ResponseBody responseBody = response.body();

        assert responseBody != null;

        return objectMapper.readValue(responseBody.byteStream(), ClientPage.class);
    }

    @Override
    public Optional<Boolean> editClient(ClientPutDTO editedClient) throws IOException {
        Response response = editClientRequest(editedClient, Model.getAuthenticationToken().getAccessToken());

        if (response.code() == 201) {

            ClientController clientController = new ClientController();

            clientController.initTableView();

            clientController.setReloadData(true);

            NavigationController.setCurrentNavigation(Navigation.TO_CLIENTS);

            return Optional.of(true);

        }

        if (response.code() == 401) {
            try {
                AuthenticationToken authenticationToken = authService.renewAccessToken(
                        new AuthenticationRefreshToken(Model.getSubject(), Model.getAuthenticationToken().getRefreshToken())
                ).orElseThrow();

                editClientRequest(editedClient, authenticationToken.getAccessToken());

                ClientController clientController = new ClientController();

                clientController.initTableView();

                clientController.setReloadData(true);

                NavigationController.setCurrentNavigation(Navigation.TO_CLIENTS);

                return Optional.of(true);

            } catch (Exception e) {
                System.out.println("Thrown by orElseThrow() " + e.getMessage());
            }
            Model.setRefreshTokenExpired(true);
            return Optional.empty();
        }
        Model.setRefreshTokenExpired(true);
        return Optional.empty();
    }

    public Response editClientRequest(ClientPutDTO clientPutDTO, String accessToken) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = "http://localhost:8081/api/clients/" + clientPutDTO.getId();

        ObjectMapper mapper = new ObjectMapper();

        // Convert the object to a JSON string
        String jsonString = mapper.writeValueAsString(clientPutDTO);

        // Create the request body
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonString);

        // Create the request
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", accessToken)
                .build();

        // Send the request
        return client.newCall(request).execute();
    }
}
