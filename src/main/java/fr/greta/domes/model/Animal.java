package fr.greta.domes.model;

import fr.greta.domes.model.enums.Category;
import fr.greta.domes.model.enums.Specie;

import java.util.Objects;
import java.util.UUID;

public class Animal {

    private UUID uuid;
    private String description;
    private String imageUrl;
    private Category category;
    private Specie specie;
    private double price;
    private int age;

    public Animal() {
    }

    public Animal(UUID uuid, String description, String imageUrl, Category category, Specie specie, double price, int age) {
        this.uuid = uuid;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.specie = specie;
        this.price = price;
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Double.compare(animal.price, price) == 0 && age == animal.age && Objects.equals(uuid, animal.uuid) && Objects.equals(description, animal.description) && Objects.equals(imageUrl, animal.imageUrl) && category == animal.category && specie == animal.specie;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, description, imageUrl, category, specie, price, age);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "uuid=" + uuid +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", category=" + category +
                ", specie=" + specie +
                ", price=" + price +
                ", age=" + age +
                '}';
    }
}
