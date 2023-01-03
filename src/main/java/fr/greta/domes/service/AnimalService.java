package fr.greta.domes.service;

import fr.greta.domes.model.animal.Animal;

import java.io.IOException;
import java.util.Collection;

public interface AnimalService {
    public Collection<Animal> getAll() throws IOException;
}
