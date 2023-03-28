package fr.greta.domes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import fr.greta.domes.model.category.Category;
import okhttp3.*;

import java.io.IOException;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AuthServiceImpl implements AuthService{
    @Override
    public void login(String email, String password) throws IOException {
        OkHttpClient client = new OkHttpClient();

        ObjectMapper objectMapper = new ObjectMapper();

        Request request = new Request.Builder()
                .url("http://localhost:8081/api/login")
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        ResponseBody responseBody = response.body();


        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Category.class);

        assert responseBody != null;
//        List<Category> categories = objectMapper.readValue(responseBody.byteStream(), listType);

//        return categories.stream().map(Category::getName).toList();
    }
}
