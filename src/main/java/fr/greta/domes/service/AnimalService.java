package fr.greta.domes.service;

import fr.greta.domes.controller.animal.AnimalSearchQuery;
import fr.greta.domes.model.animal.AnimalCreateDTO;
import fr.greta.domes.model.animal.AnimalEditDTO;
import fr.greta.domes.model.animal.AnimalPage;

import java.io.IOException;
import java.util.Optional;

public interface AnimalService {
    Optional<AnimalPage> getAnimalPage(AnimalSearchQuery animalSearchQuery) throws IOException;

    Optional<Boolean> saveAnimal(AnimalCreateDTO animalCreateDTO) throws IOException;

    Optional<Boolean> editAnimal(AnimalEditDTO dto) throws IOException;
}
