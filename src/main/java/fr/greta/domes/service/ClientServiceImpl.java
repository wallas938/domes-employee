package fr.greta.domes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.greta.domes.controller.NavigationController;
import fr.greta.domes.controller.client.ClientController;
import fr.greta.domes.controller.client.ClientSearchQuery;
import fr.greta.domes.model.Model;
import fr.greta.domes.model.Navigation;
import fr.greta.domes.model.client.ClientPage;
import fr.greta.domes.model.client.ClientPutDTO;
import okhttp3.*;

import java.io.IOException;

public class ClientServiceImpl implements ClientService {
    @Override
    public ClientPage getClientPage(ClientSearchQuery csq) throws IOException {

        OkHttpClient client = new OkHttpClient();

        String url = "http://localhost:8081/api/clients/init?lastname=%s&firstname=%s&phoneNumber=%s&email=%s&pageNumber=%s&pageSize=%s";

        // FETCH clients
        Request request = new Request.Builder().url(String.format(url,
                        csq.getLastname(),
                        csq.getFirstname(),
                        csq.getPhoneNumber(),
                        csq.getEmail(),
                        csq.getPageNumber() - 1,
                        csq.getPageSize()))
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", Model.getAuthenticationToken().getAccess_token())
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
        ResponseBody responseBody = response.body();

        assert responseBody != null;
        return objectMapper.readValue(responseBody.byteStream(), ClientPage.class);
    }

    @Override
    public ClientPage searchBarGetClients(ClientSearchQuery csq) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = "http://localhost:8081/api/clients/search?lastname=%s&firstname=%s&phoneNumber=%s&email=%s&pageNumber=%s&pageSize=%s";

        // FETCH clients
        Request request = new Request.Builder().url(String.format(url,
                csq.getLastname(),
                csq.getFirstname(),
                csq.getPhoneNumber(),
                csq.getEmail(),
                csq.getPageNumber() - 1,
                csq.getPageSize())).build();

        Call call = client.newCall(request);

        Response response = call.execute();

        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

        ResponseBody responseBody = response.body();

        assert responseBody != null;

        return objectMapper.readValue(responseBody.byteStream(), ClientPage.class);
    }

    @Override
    public void editClient(ClientPutDTO editedClient) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = "http://localhost:8081/api/clients/" + editedClient.getId();
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Convert the object to a JSON string
            String jsonString = mapper.writeValueAsString(editedClient);

            // Create the request body
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonString);

            // Create the request
            Request request = new Request.Builder()
                    .url(url)
                    .put(body)
                    .build();

            // Send the request
            Response response = client.newCall(request).execute();

            ResponseBody responseBody = response.body();

            assert responseBody != null;

            ClientController clientController = new ClientController();

            clientController.initTableView();

            clientController.setReloadData(true);

            NavigationController.setCurrentNavigation(Navigation.TO_CLIENTS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
