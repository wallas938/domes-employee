package fr.greta.domes.controller.animal;

import fr.greta.domes.Main;
import fr.greta.domes.controller.NavigationController;
import fr.greta.domes.model.animal.Animal;
import fr.greta.domes.model.Navigation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AnimalDetailController implements Initializable {
    @FXML
    private Label animalRef;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Text description;
    @FXML
    private Label category;
    @FXML
    private Label age;
    @FXML
    private Label price;
    @FXML
    private Label specie;
    @FXML
    private ImageView mainPicture;
    @FXML
    private ImageView secondImage;
    @FXML
    private ImageView thirdImage;
    @FXML
    private ImageView fourthImage;
    private static Animal currentAnimal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFields(currentAnimal);
        NavigationController.getCurrentNavigation().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(Navigation.TO_ANIMAL_DETAIL))
                setFields(currentAnimal);
        });
    }

    /*
    * Methods
    * */

    public void showEditForm() {
        AnimalFormController.setAnimalData(currentAnimal);
        NavigationController.setCurrentNavigation(Navigation.TO_ANIMALS_FORM);
//        Model.getInstance().getViewFactory().getAnimalFormView();
    }
    public void setFields(Animal currentAnimal) {
        setAnimalRef(currentAnimal.getId().toString());
        setAge(currentAnimal.getAge());
        setPrice(currentAnimal.getPrice());
        setMainPicture(currentAnimal.getMainPicture());
        setSecondImage(currentAnimal.getSecondPicture());
        setThirdImage(currentAnimal.getThirdPicture());
        setFourthImage(currentAnimal.getFourthPicture());
        setCategory(currentAnimal.getCategory().getName());
        setSpecie(currentAnimal.getSpecie().getName());
    }

    public static void setCurrentAnimal(Animal animal) {
        currentAnimal = animal;
    }

    /*
    * Setters and getters
    * */
    public String getAnimalRef() {
        return animalRef.getText();
    }

    public void setAnimalRef(String animalRef) {
        this.animalRef.setText(animalRef);
    }

    public String getDescription() {
        return description.getText();
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public String getCategory() {
        return category.getText();
    }

    public void setCategory(String category) {
        this.category.setText(category);
    }

    public int getAge() {
        return Integer.parseInt(age.getText());
    }

    public void setAge(int age) {
        this.age.setText(String.valueOf(age));
    }

    public double getPrice() {
        return Integer.parseInt(price.getText());
    }

    public void setPrice(double price) {
        this.price.setText(String.valueOf(price));
    }

    public String getSpecie() {
        return specie.getText();
    }

    public void setSpecie(String specie) {
        this.specie.setText(specie);
    }

    public String getMainPicture() {
        return mainPicture.getImage().getUrl();
    }

    public void setMainPicture(String url) {
        this.mainPicture.setImage(pictureUrlHandler(url));
    }

    public String getSecondImage() {
        return secondImage.getImage().getUrl();
    }

    public void setSecondImage(String url) {
        this.secondImage.setImage(pictureUrlHandler(url));
    }

    public String getThirdImage() {
        return thirdImage.getImage().getUrl();
    }

    public void setThirdImage(String url) {
        this.thirdImage.setImage(pictureUrlHandler(url));
    }

    public String getFourthImage() {
        return fourthImage.getImage().getUrl();
    }

    public void setFourthImage(String url) {
        this.fourthImage.setImage(pictureUrlHandler(url));
    }

    /*
    * Private utils
    * */
    private Image pictureUrlHandler(String imageUrl) {
        Image image;
        try {
            image = new Image(imageUrl);
            return image;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            image = new Image(Objects.requireNonNull(Main.class.getResource("images/no_picture.png")).toString());
        }
        return image;
    }
}
