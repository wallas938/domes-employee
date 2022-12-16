package fr.greta.domes.controller;

import fr.greta.domes.controller.service.AnimalService;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private CheckBox priceCheckboxStatus;
    @FXML
    private CheckBox ageCheckboxStatus;
    @FXML
    private TextField minPriceValue;
    @FXML
    private TextField maxPriceValue;
    @FXML
    private Label minPriceLabel;
    @FXML
    private Label maxPriceLabel;
    @FXML
    private TextField minAgeValue;
    @FXML
    private TextField maxAgeValue;
    @FXML
    private Label minAgeLabel;
    @FXML
    private Label maxAgeLabel;
    @FXML
    private ChoiceBox<String> byCategory;
    @FXML
    private ChoiceBox<String> bySpecie;
    @FXML
    private Button filterButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPriceFieldsVisibility(false);
        setAgeFieldsVisibility(false);

        byCategory.getItems().addAll(AnimalService.categories());

        bySpecie.getItems().addAll(AnimalService.categories());
    }

    public void setPriceFieldsVisibility(boolean status) {
        minPriceValue.setVisible(status);
        maxPriceValue.setVisible(status);
        minPriceLabel.setVisible(status);
        maxPriceLabel.setVisible(status);
    }

    public void setAgeFieldsVisibility(boolean status) {
        minAgeValue.setVisible(status);
        maxAgeValue.setVisible(status);
        minAgeLabel.setVisible(status);
        maxAgeLabel.setVisible(status);
    }

    public void togglePriceCheckbox() {
        if(priceCheckboxStatus.isSelected()) {
            setPriceFieldsVisibility(true);
            return;
        }
        setPriceFieldsVisibility(false);
        return;
    }
    public void toggleAgeCheckbox() {
        if(ageCheckboxStatus.isSelected()) {
            setAgeFieldsVisibility(true);
            return;
        }
        setAgeFieldsVisibility(false);
        return;
    }

    public boolean isPriceChecked() {
        return priceCheckboxStatus.isSelected();
    }

    public boolean isAgeChecked() {
        return ageCheckboxStatus.isSelected();
    }

    public int getMinPriceValue() {
        if (Integer.parseInt(minPriceValue.getText()) >= 0 && Integer.parseInt(minPriceValue.getText()) < Integer.parseInt(maxPriceValue.getText())) {
            return Integer.parseInt(minPriceValue.getText());
        }
        return 0;
    }

    public int getMaxPriceValue() {
        if (Integer.parseInt(maxPriceValue.getText()) > 0 && Integer.parseInt(maxPriceValue.getText()) > Integer.parseInt(minPriceValue.getText())) {
            return Integer.parseInt(maxPriceValue.getText());
        }
        return 1;
    }
}
