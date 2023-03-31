package fr.greta.domes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import fr.greta.domes.model.auth.AuthenticationToken;
import fr.greta.domes.model.category.Category;
import okhttp3.*;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;

import java.io.IOException;
import java.util.ArrayList;

public class AuthServiceImpl implements AuthService {
    @Override
    public void login(String email, String password) throws IOException {
        OkHttpClient client = new OkHttpClient();

        ObjectMapper objectMapper = new ObjectMapper();

        Request request = new Request.Builder()
                .url("http://localhost:8081/api/auth/login")
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        ResponseBody responseBody = response.body();

        assert responseBody != null;

        AuthenticationToken authenticationToken = objectMapper.readValue(responseBody.byteStream(), AuthenticationToken.class);

        System.out.println(authenticationToken);

//        return categories.stream().map(Category::getName).toList();
    }
}
