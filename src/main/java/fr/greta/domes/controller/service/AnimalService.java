package fr.greta.domes.controller.service;

import fr.greta.domes.category.Category;
import fr.greta.domes.category.Specie;

import java.util.Collection;
import java.util.List;

public class AnimalService {

    static public Collection<String> categories() {
        return List.of(Category.ALL.toString(), Category.DOG.toString(), Category.CAT.toString(), Category.REPTILE.toString(), Category.FISH.toString(), Category.BIRD.toString());
    }

    static public Collection<String> species() {
        return List.of(
                /**/
        );
    }
}
