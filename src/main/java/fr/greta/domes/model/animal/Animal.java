package fr.greta.domes.model.animal;

import fr.greta.domes.model.category.Category;
import fr.greta.domes.model.order.Order;
import fr.greta.domes.model.specie.Specie;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Animal {
    private UUID id;
    private String description;
    private String mainPicture;
    private String secondPicture;
    private String thirdPicture;
    private String fourthPicture;
    private Category category;
    private Specie specie;
    private double price;
    private Order order;
    private int age;
    private boolean sold;
    private LocalDate registrationDate = LocalDate.now();

    public Animal() {
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
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

    public UUID getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Double.compare(animal.price, price) == 0 && age == animal.age && sold == animal.sold && Objects.equals(description, animal.description) && Objects.equals(mainPicture, animal.mainPicture) && Objects.equals(secondPicture, animal.secondPicture) && Objects.equals(thirdPicture, animal.thirdPicture) && Objects.equals(fourthPicture, animal.fourthPicture) && Objects.equals(category, animal.category) && Objects.equals(specie, animal.specie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, mainPicture, secondPicture, thirdPicture, fourthPicture, category, specie, price, age, sold);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", mainPicture='" + mainPicture + '\'' +
                ", secondPicture='" + secondPicture + '\'' +
                ", thirdPicture='" + thirdPicture + '\'' +
                ", fourthPicture='" + fourthPicture + '\'' +
                ", category=" + category +
                ", specie=" + specie +
                ", price=" + price +
                ", age=" + age +
                ", sold=" + sold +
                '}';
    }
}
