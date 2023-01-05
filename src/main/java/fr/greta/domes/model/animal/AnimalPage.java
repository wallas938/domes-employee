package fr.greta.domes.model.animal;

import java.util.List;

public class AnimalPage {
    private List<Animal> animals;
    private Integer totalPages;
    private Integer totalElements;

    // default constructor
    public AnimalPage() {
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
    public Integer getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
    public Integer getTotalElements() {
        return totalElements;
    }
    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    @Override
    public String toString() {
        return "AnimalPage{" +
                "animals=" + animals +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                '}';
    }
}
