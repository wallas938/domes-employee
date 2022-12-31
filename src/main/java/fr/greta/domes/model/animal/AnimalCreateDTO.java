package fr.greta.domes.model.animal;

import fr.greta.domes.model.enums.Category;
import fr.greta.domes.model.enums.Specie;

import java.util.Objects;

public class AnimalCreateDTO {
    private String description;
    private String mainPicture;
    private String secondPicture;
    private String thirdPicture;
    private String fourthPicture;
    private Category category;
    private Specie specie;
    private double price;
    private int age;

    public AnimalCreateDTO() {
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

    public void setMainPicture(String mainPicture) {
        this.mainPicture = mainPicture;
    }

    public String getSecondPicture() {
        return secondPicture;
    }

    public void setSecondPicture(String secondPicture) {
        this.secondPicture = secondPicture;
    }

    public String getThirdPicture() {
        return thirdPicture;
    }

    public void setThirdPicture(String thirdPicture) {
        this.thirdPicture = thirdPicture;
    }

    public String getFourthPicture() {
        return fourthPicture;
    }

    public void setFourthPicture(String fourthPicture) {
        this.fourthPicture = fourthPicture;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalCreateDTO that = (AnimalCreateDTO) o;
        return Double.compare(that.price, price) == 0 && age == that.age && Objects.equals(description, that.description) && Objects.equals(mainPicture, that.mainPicture) && Objects.equals(secondPicture, that.secondPicture) && Objects.equals(thirdPicture, that.thirdPicture) && Objects.equals(fourthPicture, that.fourthPicture) && category == that.category && specie == that.specie;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, mainPicture, secondPicture, thirdPicture, fourthPicture, category, specie, price, age);
    }

    @Override
    public String toString() {
        return "AnimalCreateDTO{" +
                "description='" + description + '\'' +
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
