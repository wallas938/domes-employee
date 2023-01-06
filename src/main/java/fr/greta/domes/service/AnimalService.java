package fr.greta.domes.service;

import fr.greta.domes.controller.animal.AnimalSearchQuery;
import fr.greta.domes.model.animal.Animal;
import fr.greta.domes.model.animal.AnimalPage;

import java.io.IOException;
import java.util.Collection;

public interface AnimalService {
    public AnimalPage getAnimalPage(AnimalSearchQuery animalSearchQuery) throws IOException;
}
