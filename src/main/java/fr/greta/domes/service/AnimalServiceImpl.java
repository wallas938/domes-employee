package fr.greta.domes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.greta.domes.controller.animal.AnimalController;
import fr.greta.domes.controller.animal.AnimalSearchQuery;
import fr.greta.domes.model.animal.AnimalCreateDTO;
import fr.greta.domes.model.animal.AnimalPage;
import okhttp3.*;

import java.io.IOException;
import java.util.Collection;

public class AnimalServiceImpl implements AnimalService {

    AnimalController animalController;

    static public Collection<String> categories() {
        return null;
    }

    static public Collection<String> species() {
        return null;
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
                asq.getPageSize())).build();

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

        // Create an object to serialize
        AnimalCreateDTO dto = new AnimalCreateDTO();
        dto.setDescription(animalCreateDTO.getDescription());
        dto.setCategory(animalCreateDTO.getCategory());
        dto.setSpecie(animalCreateDTO.getSpecie());
        dto.setAge(animalCreateDTO.getAge());
        dto.setPrice(animalCreateDTO.getPrice());
        dto.setMainPicture(animalCreateDTO.getMainPicture());
        dto.setSecondPicture(animalCreateDTO.getSecondPicture());
        dto.setThirdPicture(animalCreateDTO.getThirdPicture());
        dto.setFourthPicture(animalCreateDTO.getFourthPicture());

        try {
            // Convert the object to a JSON string
            String jsonString = mapper.writeValueAsString(dto);

            // Create the request body
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonString);

            // Create the request
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            // Send the request
            Response response = client.newCall(request).execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
