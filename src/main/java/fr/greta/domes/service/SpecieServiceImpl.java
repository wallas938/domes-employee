package fr.greta.domes.service;

import fr.greta.domes.model.specie.Specie;
import okhttp3.*;

import java.io.IOException;
import java.util.Collection;

public class SpecieServiceImpl implements SpecieService{
    @Override
    public Response getAll(String categoryName) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(String.format("http://localhost:8081/api/species/categoryName?categoryName=%s", categoryName)).build();

        Call call = client.newCall(request);

        Response response = call.execute();

        return response;
    }
}
