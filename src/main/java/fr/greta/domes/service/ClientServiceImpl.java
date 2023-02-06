package fr.greta.domes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.greta.domes.controller.client.ClientSearchQuery;
import fr.greta.domes.model.client.ClientPage;
import okhttp3.*;

import java.io.IOException;

public class ClientServiceImpl implements ClientService{
    @Override
    public ClientPage getClientPage(ClientSearchQuery csq) throws IOException  {

        OkHttpClient client = new OkHttpClient();

        String url = "http://localhost:8081/api/clients/init?lastname=%s&firstname=%s&phoneNumber=%s&email=%s&pageNumber=%s&pageSize=%s";

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
}
