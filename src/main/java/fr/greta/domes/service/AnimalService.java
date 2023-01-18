package fr.greta.domes.service;

import fr.greta.domes.controller.animal.AnimalSearchQuery;
import fr.greta.domes.model.animal.AnimalCreateDTO;
import fr.greta.domes.model.animal.AnimalEditDTO;
import fr.greta.domes.model.animal.AnimalPage;

import java.io.IOException;

public interface AnimalService {
    AnimalPage getAnimalPage(AnimalSearchQuery animalSearchQuery) throws IOException;

    void saveAnimal(AnimalCreateDTO animalCreateDTO) throws IOException;

    void editAnimal(AnimalEditDTO dto);
}
