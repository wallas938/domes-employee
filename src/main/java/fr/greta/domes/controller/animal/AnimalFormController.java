package fr.greta.domes.controller.animal;

import fr.greta.domes.controller.NavigationController;
import fr.greta.domes.model.Model;
import fr.greta.domes.model.Navigation;
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
    private TextField fourthImageField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField priceField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private ChoiceBox categoryField;
    @FXML
    private ChoiceBox specieField;
    @FXML
    private Button save;
    @FXML
    private Button cancel;
    @FXML
    private TextField mainPictureField;
    @FXML
    private TextField secondImageField;
    @FXML
    private TextField thirdImageField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("AnimalFormController");
    }

    public void save() {

    }

    public void cancel() {
        NavigationController.setCurrentNavigation(Navigation.TO_ANIMALS);
        Model.getInstance().getViewFactory().getAnimalView();
    }
}
