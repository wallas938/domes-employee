package fr.greta.domes.model.article;

import fr.greta.domes.model.category.Category;
import fr.greta.domes.model.specie.Specie;

import java.util.UUID;

public class Article {
    private UUID id;
    private double price;
    private Specie specie;
    private Category category;

    public Article() {
    }

    public Article(UUID id, double price, Specie specie, Category category) {
        this.id = id;
        this.price = price;
        this.specie = specie;
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
