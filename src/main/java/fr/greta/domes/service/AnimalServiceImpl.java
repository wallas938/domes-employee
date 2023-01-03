package fr.greta.domes.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.greta.domes.model.animal.Animal;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Collection;

public class AnimalServiceImpl implements AnimalService {

    static public Collection<String> categories() {
        return null;
    }

    static public Collection<String> species() {
        return null;
    }

    @Override
    public Collection<Animal> getAll() {
        return null;
    }
}
