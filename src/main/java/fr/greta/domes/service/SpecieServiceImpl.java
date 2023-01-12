package fr.greta.domes.service;

import okhttp3.*;

import java.io.IOException;

public class SpecieServiceImpl implements SpecieService{
    @Override
    public Response getAll(String categoryName) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(String.format("http://localhost:8081/api/species/categoryName?categoryName=%s", categoryName)).build();

        Call call = client.newCall(request);

        return call.execute();
    }
}
