package fr.greta.domes.model;

import fr.greta.domes.model.enums.Category;
import fr.greta.domes.model.enums.Specie;

import java.util.Objects;
import java.util.UUID;

public class Animal {
    private UUID uuid;
    private String description;
    private String mainPicture;
    private String secondPicture;
    private String thirdPicture;
    private String fourthPicture;
    private Category category;
    private Specie specie;
    private double price;
    private int age;

    public Animal() {
    }

    public Animal(UUID uuid, String description, String mainPicture, String secondPicture, String thirdPicture, String fourthPicture, Category category, Specie specie, double price, int age) {
        this.uuid = uuid;
        this.description = description;
        this.mainPicture = mainPicture;
        this.secondPicture = secondPicture;
        this.thirdPicture = thirdPicture;
        this.fourthPicture = fourthPicture;
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

    public String getMainPicture() {
        return mainPicture;
    }

    public void setMainPicture(String url) {
        this.mainPicture = url;
    }

    public String getSecondPicture() {
        return secondPicture;
    }

    public void setSecondPicture(String url) {
        this.secondPicture = url;
    }

    public String getThirdPicture() {
        return thirdPicture;
    }

    public void setThirdPicture(String url) {
        this.thirdPicture = url;
    }

    public String getFourthPicture() {
        return fourthPicture;
    }

    public void setFourthPicture(String url) {
        this.fourthPicture = url;
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
        return Double.compare(animal.price, price) == 0 && age == animal.age && Objects.equals(uuid, animal.uuid) && Objects.equals(description, animal.description) && Objects.equals(mainPicture, animal.mainPicture) && Objects.equals(secondPicture, animal.secondPicture) && Objects.equals(thirdPicture, animal.thirdPicture) && Objects.equals(fourthPicture, animal.fourthPicture) && category == animal.category && specie == animal.specie;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, description, mainPicture, secondPicture, thirdPicture, fourthPicture, category, specie, price, age);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "uuid=" + uuid +
                ", description='" + description + '\'' +
                ", mainPicture='" + mainPicture + '\'' +
                ", secondPicture='" + secondPicture + '\'' +
                ", thirdPicture='" + thirdPicture + '\'' +
                ", fourthPicture='" + fourthPicture + '\'' +
                ", category=" + category +
                ", specie=" + specie +
                ", price=" + price +
                ", age=" + age +
                '}';
    }
}
