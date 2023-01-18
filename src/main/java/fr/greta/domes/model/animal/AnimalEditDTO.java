package fr.greta.domes.model.animal;

public class AnimalEditDTO {
    private String id;
    private String description;
    private String mainPicture;
    private String secondPicture;
    private String thirdPicture;
    private String fourthPicture;
    private String category;
    private String specie;
    private double price;
    private int age;

    public AnimalEditDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
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
}
