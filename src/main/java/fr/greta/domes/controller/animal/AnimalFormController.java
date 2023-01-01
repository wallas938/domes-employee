package fr.greta.domes.controller.animal;

import fr.greta.domes.controller.NavigationController;
import fr.greta.domes.model.Model;
import fr.greta.domes.model.Navigation;
import fr.greta.domes.model.animal.Animal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AnimalFormController implements Initializable {
    @FXML
    private TextField ageField;
    @FXML
    private TextField priceField;
    @FXML
    private ChoiceBox categoryField;
    @FXML
    private ChoiceBox specieField;
    @FXML
    private TextField mainPictureField;
    @FXML
    private TextField secondImageField;
    @FXML
    private TextField thirdImageField;
    @FXML
    private TextField fourthImageField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private Button save;
    @FXML
    private Button cancel;
    private static Animal currentAnimal;

    public static void setAnimalData(Animal animal) {
        currentAnimal = animal;
    }

    public void setFields(Animal animal) {
        ageField.setText(String.valueOf(animal.getAge()));
        priceField.setText(String.valueOf(animal.getPrice()));
        mainPictureField.setText(animal.getMainPicture());
        secondImageField.setText(animal.getSecondPicture());
        thirdImageField.setText(animal.getThirdPicture());
        fourthImageField.setText(animal.getFourthPicture());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
         * For the first initialisation of the controller
         * */
        if (currentAnimal != null) setFields(currentAnimal);

        NavigationController.getCurrentNavigation().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(Navigation.TO_ANIMALS_FORM)) {
                if (currentAnimal != null) {
                    setFields(currentAnimal);
                } else {
                    resetFields();
                }
            }
        });
    }

    public void save() {

    }

    public void cancel() {
        NavigationController.setCurrentNavigation(Navigation.TO_ANIMALS);
//        Model.getInstance().getViewFactory().getAnimalView();
    }

    public void resetFields() {
        ageField.setText("");
        priceField.setText("");
        mainPictureField.setText("");
        secondImageField.setText("");
        thirdImageField.setText("");
        fourthImageField.setText("");
    }
}
