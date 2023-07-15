package fr.greta.domes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.greta.domes.controller.AuthenticationController;
import fr.greta.domes.controller.NavigationController;
import fr.greta.domes.controller.animal.AnimalController;
import fr.greta.domes.controller.animal.AnimalSearchQuery;
import fr.greta.domes.model.Model;
import fr.greta.domes.model.Navigation;
import fr.greta.domes.model.animal.AnimalCreateDTO;
import fr.greta.domes.model.animal.AnimalEditDTO;
import fr.greta.domes.model.animal.AnimalPage;
import fr.greta.domes.model.auth.AuthenticationRefreshToken;
import fr.greta.domes.model.auth.AuthenticationToken;
import okhttp3.*;

import java.io.IOException;
import java.util.Optional;

public class AnimalServiceImpl implements AnimalService {

    private final AuthService authService = new AuthServiceImpl();

    public AnimalServiceImpl() {
    }

    @Override
    public Optional<AnimalPage> getAnimalPage(AnimalSearchQuery asq) throws IOException {

        Response response = getAnimalPageRequest(asq, Model.getAuthenticationToken().getAccessToken());

        if (response.code() == 202) return Optional.of(getAnimalPageConverter(response));

        if (response.code() == 401) {
            try {
                AuthenticationToken authenticationToken = authService.renewAccessToken(
                        new AuthenticationRefreshToken(Model.getSubject(), Model.getAuthenticationToken().getRefreshToken())
                ).orElseThrow();

                Response response2 = getAnimalPageRequest(asq, authenticationToken.getAccessToken());

                return Optional.of(getAnimalPageConverter(response2));
            } catch (Exception e) {
                System.out.println("RefreshToken is expired: " + e.getMessage());
            }
        }
        Model.setRefreshTokenExpired(true);
        return Optional.empty();
    }

    public Response getAnimalPageRequest(AnimalSearchQuery animalSearchQuery, String accessToken) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = "http://localhost:8081/api/animals/search?minPrice=%s&maxPrice=%s&minAge=%s&maxAge=%s&categoryName=%s&specieName=%s&pageNumber=%s&pageSize=%s";

        // FETCH animals by filters - complete request url
        Request request = new Request.Builder().url(String.format(url,
                        animalSearchQuery.getMinPrice(),
                        animalSearchQuery.getMaxPrice(),
                        animalSearchQuery.getMinAge(),
                        animalSearchQuery.getMaxAge(),
                        animalSearchQuery.getCategoryName(),
                        animalSearchQuery.getSpecieName(),
                        animalSearchQuery.getPageNumber(),
                        animalSearchQuery.getPageSize()))
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", accessToken)
                .build();

        Call call = client.newCall(request);

        return call.execute();
    }

    public AnimalPage getAnimalPageConverter(Response response) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

        ResponseBody responseBody = response.body();

        assert responseBody != null;

        return objectMapper.readValue(responseBody.byteStream(), AnimalPage.class);

    }

    @Override
    public Optional<Boolean> saveAnimal(AnimalCreateDTO animalCreateDTO) {

        Response response = saveAnimalRequest(animalCreateDTO, Model.getAuthenticationToken().getAccessToken());

        if (response.code() == 201) {
            return Optional.of(true);
        }
        if (response.code() == 401) {
            try {
                AuthenticationToken authenticationToken = authService.renewAccessToken(
                        new AuthenticationRefreshToken(Model.getSubject(), Model.getAuthenticationToken().getRefreshToken())
                ).orElseThrow();

                saveAnimalRequest(animalCreateDTO, authenticationToken.getAccessToken());
                return Optional.of(true);
            } catch (Exception e) {
                System.out.println("RefreshToken is expired: " + e.getMessage());
            }
        }
        Model.setRefreshTokenExpired(true);
        return Optional.empty();
    }

    public Response saveAnimalRequest(AnimalCreateDTO animalCreateDTO, String accessToken) {
        OkHttpClient client = new OkHttpClient();
        String url = "http://localhost:8081/api/animals";
        ObjectMapper mapper = new ObjectMapper();
        Response response = null;
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
                    .addHeader("Authorization", accessToken)
                    .build();

            // Send the request
            response = client.newCall(request).execute();

            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Optional<Boolean> editAnimal(AnimalEditDTO dto) throws IOException {

        Response response = editAnimalRequest(dto, Model.getAuthenticationToken().getAccessToken());
        if (response.code() == 201) {
            setReloadMessage();

            return Optional.of(true);

        } else if (response.code() == 401) {
            try {
                AuthenticationToken authenticationToken = authService.renewAccessToken(
                        new AuthenticationRefreshToken(Model.getSubject(), Model.getAuthenticationToken().getRefreshToken())
                ).orElseThrow();

                editAnimalRequest(dto, authenticationToken.getAccessToken());

                setReloadMessage();

                return Optional.of(true);
            } catch (Exception e) {
                System.out.println("RefreshToken is expired: " + e.getMessage());
            }
        }
        Model.setRefreshTokenExpired(true);
        return Optional.empty();
    }

    public Response editAnimalRequest(AnimalEditDTO animalEditDTO, String accessToken) throws IOException {

        OkHttpClient client = new OkHttpClient();

        String url = "http://localhost:8081/api/animals/" + animalEditDTO.getId();

        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writeValueAsString(animalEditDTO);

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonString);

        // Create the request
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", Model.getAuthenticationToken().getAccessToken())
                .build();

        // Send the request
        return client.newCall(request).execute();
    }

    private static void setReloadMessage() throws IOException {
        AnimalController animalController = new AnimalController();

        animalController.initTableView();

        animalController.setReloadData(true);

        NavigationController.setCurrentNavigation(Navigation.TO_ANIMALS);
    }
}
