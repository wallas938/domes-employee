package fr.greta.domes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.greta.domes.controller.NavigationController;
import fr.greta.domes.controller.animal.AnimalController;
import fr.greta.domes.controller.animal.AnimalSearchQuery;
import fr.greta.domes.model.Model;
import fr.greta.domes.model.Navigation;
import fr.greta.domes.model.animal.AnimalCreateDTO;
import fr.greta.domes.model.animal.AnimalEditDTO;
import fr.greta.domes.model.animal.AnimalPage;
import okhttp3.*;

import java.io.IOException;
import java.util.Collection;

public class AnimalServiceImpl implements AnimalService {

    static public Collection<String> categories() {
        return null;
    }

    static public Collection<String> species() {
        return null;
    }

    public AnimalServiceImpl() {
    }

    @Override
    public AnimalPage getAnimalPage(AnimalSearchQuery asq) throws IOException {

        OkHttpClient client = new OkHttpClient();

        String url = "http://localhost:8081/api/animals/search?minPrice=%s&maxPrice=%s&minAge=%s&maxAge=%s&categoryName=%s&specieName=%s&pageNumber=%s&pageSize=%s";

        // FETCH animals by filters - complete request url
        Request request = new Request.Builder().url(String.format(url,
                asq.getMinPrice(),
                asq.getMaxPrice(),
                asq.getMinAge(),
                asq.getMaxAge(),
                asq.getCategoryName(),
                asq.getSpecieName(),
                asq.getPageNumber(),
                asq.getPageSize()))
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", Model.getAuthenticationToken().getAccess_token())
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

        ResponseBody responseBody = response.body();

        assert responseBody != null;

        return objectMapper.readValue(responseBody.byteStream(), AnimalPage.class);
    }

    @Override
    public void saveAnimal(AnimalCreateDTO animalCreateDTO) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = "http://localhost:8081/api/animals";

        ObjectMapper mapper = new ObjectMapper();

        try {
            // Convert the object to a JSON string
            String jsonString = mapper.writeValueAsString(animalCreateDTO);

            // Create the request body
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonString);

            // Create the request
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", Model.getAuthenticationToken().getAccess_token())
                    .build();

            // Send the request
            Response response = client.newCall(request).execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editAnimal(AnimalEditDTO dto) {

        OkHttpClient client = new OkHttpClient();

        String url = "http://localhost:8081/api/animals/" + dto.getId();

        ObjectMapper mapper = new ObjectMapper();

        try {
            // Convert the object to a JSON string
            String jsonString = mapper.writeValueAsString(dto);

            // Create the request body
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonString);

            // Create the request
            Request request = new Request.Builder()
                    .url(url)
                    .put(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", Model.getAuthenticationToken().getAccess_token())
                    .build();

            // Send the request
            Response response = client.newCall(request).execute();

            ResponseBody responseBody = response.body();

            assert responseBody != null;

            AnimalController animalController = new AnimalController();

            animalController.initTableView();

            animalController.setReloadData(true);

            NavigationController.setCurrentNavigation(Navigation.TO_ANIMALS);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
