package fr.greta.domes.controller.animal;


import fr.greta.domes.controller.NavigationController;
import fr.greta.domes.model.Model;
import fr.greta.domes.model.Navigation;
import fr.greta.domes.model.animal.Animal;

import fr.greta.domes.model.animal.AnimalCreateDTO;
import fr.greta.domes.model.animal.AnimalEditDTO;
import fr.greta.domes.model.animal.AnimalFormFieldValidator;
import fr.greta.domes.service.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AnimalFormController implements Initializable {
    @FXML
    private Label mainPictureTextError;
    @FXML
    private Label secondPictureTextError;
    @FXML
    private Label thirdPictureTextError;
    @FXML
    private Label fourthTextError;
    @FXML
    private Label ageTextError;
    @FXML
    private Label priceTextError;
    @FXML
    private TextField ageField;
    @FXML
    private TextField priceField;
    @FXML
    private ChoiceBox<String> categoryField;
    @FXML
    private ChoiceBox<String> specieField;
    @FXML
    private TextField mainPictureField;
    @FXML
    private TextField secondPictureField;
    @FXML
    private TextField thirdPictureField;
    @FXML
    private TextField fourthPictureField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private Button save;
    @FXML
    private Button cancel;
    private static Animal currentAnimal;
    private final CategoryService categoryService = new CategoryServiceImpl();
    private final AnimalService animalService = new AnimalServiceImpl();

    private final AnimalFormFieldValidator animalFormFieldValidator;

    public static void setAnimalData(Animal animal) {
        currentAnimal = animal;
    }

    public AnimalFormController() {
        animalFormFieldValidator = new AnimalFormFieldValidator();
    }

    public AnimalController animalController = new AnimalController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
         * For the first initialisation of the controller
         * */

        checkIsEditMode();

        NavigationController.getCurrentNavigation().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(Navigation.TO_ANIMALS_FORM)) {
                checkIsEditMode();
            }
        });

        initEventListeners();
    }

    /*
     *  First Initializations
     * */
    private void initFormFields(Animal animal) throws IOException {
        ageField.setText(String.valueOf(animal.getAge()));
        animalFormFieldValidator.setAgeValid(true);

        priceField.setText(String.valueOf(animal.getPrice()));
        animalFormFieldValidator.setPriceValid(true);

        categoryField.setValue(animal.getCategory().getName());
        animalFormFieldValidator.setCategoryValid(true);

        onChoiceBoxCategoriesChange();

        specieField.setValue(animal.getSpecie().getName());
        animalFormFieldValidator.setSpecieValid(true);

        descriptionField.setText(animal.getDescription());
        animalFormFieldValidator.setDescription(true);

        mainPictureField.setText(animal.getMainPicture());
        secondPictureField.setText(animal.getSecondPicture());
        thirdPictureField.setText(animal.getThirdPicture());
        fourthPictureField.setText(animal.getFourthPicture());
    }

    private void initEventListeners() {

        updateChoiceBoxCategoriesValues();

        initRequiredFieldValidators();

        categoryField.setOnAction(event -> {
            try {
                onChoiceBoxCategoriesChange();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        cancel.setOnAction(event -> NavigationController.setCurrentNavigation(Navigation.TO_ANIMALS));

        save.disableProperty().bind(animalFormFieldValidator.isAnimalFormIsValid().not());
    }

    public void initRequiredFieldValidators() {

        ageField.textProperty().addListener(event -> {
            try {
                int age = Integer.parseInt(ageField.getText());
                if (age > 1 && age <= 24) {
                    ageTextError.setVisible(false);
                    animalFormFieldValidator.setAgeValid(true);
                } else {
                    ageTextError.setVisible(true);
                    animalFormFieldValidator.setAgeValid(false);
                }
            } catch (NumberFormatException e) {
                ageTextError.setVisible(true);
                animalFormFieldValidator.setAgeValid(false);
            }
        });

        priceField.textProperty().addListener(event -> {
            try {
                double price = Double.parseDouble(priceField.getText());
                if (price >= 50) {
                    priceTextError.setVisible(false);
                    animalFormFieldValidator.setPriceValid(true);
                } else {
                    priceTextError.setVisible(true);
                    animalFormFieldValidator.setPriceValid(false);
                }
            } catch (NumberFormatException e) {
                priceTextError.setVisible(true);
                animalFormFieldValidator.setPriceValid(false);
            }
        });


        categoryField.valueProperty().addListener((obs, wasFocused, isNowFocused) -> {
            animalFormFieldValidator.setCategoryValid(categoryField.getValue() != null);
        });

        specieField.valueProperty().addListener((obs, wasFocused, isNowFocused) -> {
            animalFormFieldValidator.setSpecieValid(specieField.getValue() != null);
        });

        descriptionField.textProperty().addListener(event -> animalFormFieldValidator.setDescription(descriptionField.getText().length() <= 500));
    }

    /*
     * Actions
     * */

    private void checkIsEditMode() {
        if (currentAnimal != null) {
            try {
                initFormFields(currentAnimal);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            save.setText("Modifier");
        } else {
            try {
                resetFields();
                save.setText("Enregistrer");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void save() throws IOException {
        if (animalFormFieldValidator.isAnimalFormIsValid().get() && currentAnimal == null) {
            AnimalCreateDTO animalCreateDTO = new AnimalCreateDTO();
            animalCreateDTO.setAge(Integer.parseInt(ageField.getText()));
            animalCreateDTO.setPrice(Double.parseDouble(priceField.getText()));
            animalCreateDTO.setCategory(categoryField.getValue());
            animalCreateDTO.setSpecie(specieField.getValue());
            animalCreateDTO.setMainPicture(mainPictureField.getText());
            animalCreateDTO.setSecondPicture(secondPictureField.getText());
            animalCreateDTO.setThirdPicture(thirdPictureField.getText());
            animalCreateDTO.setFourthPicture(fourthPictureField.getText());
            animalCreateDTO.setDescription(descriptionField.getText());

            animalService.saveAnimal(animalCreateDTO).ifPresentOrElse(aBoolean -> {
                try {
                    animalController.initTableView();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                NavigationController.setCurrentNavigation(Navigation.TO_ANIMALS);
            }, this::closeWindow);


        } else if (animalFormFieldValidator.isAnimalFormIsValid().get() && currentAnimal != null) {

            AnimalEditDTO dto = new AnimalEditDTO();
            dto.setId(currentAnimal.getId().toString());
            dto.setAge(Integer.parseInt(ageField.getText()));
            dto.setPrice(Double.parseDouble(priceField.getText()));
            dto.setCategory(categoryField.getValue());
            dto.setSpecie(specieField.getValue());
            dto.setMainPicture(mainPictureField.getText());
            dto.setSecondPicture(secondPictureField.getText());
            dto.setThirdPicture(thirdPictureField.getText());
            dto.setFourthPicture(fourthPictureField.getText());
            dto.setDescription(descriptionField.getText());
            animalService.editAnimal(dto).ifPresentOrElse(aBoolean -> {
            }, this::closeWindow);
        }
    }

    /*
     * View Updating
     * */

    private void updateChoiceBoxCategoriesValues() {
        CategoryService categoryService = new CategoryServiceImpl();

        try {
            categoryService.getAll().ifPresentOrElse(names -> {
                categoryField.setItems(FXCollections.observableList(names));
            }, this::closeWindow);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetFields() throws IOException {
        priceField.clear();
        ageField.clear();
        descriptionField.clear();
        mainPictureField.clear();
        secondPictureField.clear();
        thirdPictureField.clear();
        fourthPictureField.clear();

        animalFormFieldValidator.resetData();

        categoryService.getAll().ifPresentOrElse(names -> {
            categoryField.setItems(FXCollections.observableList(names));
            specieField.getItems().clear();
            specieField.setValue(null);
        }, this::closeWindow);

    }

    private void onChoiceBoxCategoriesChange() throws IOException {
        SpecieService specieService = new SpecieServiceImpl();
        // Reset old values
        specieField.getItems().clear();

        // Init ChoiceBox "TOUTES" value

        Optional<List<String>> specieNames = specieService.getAll(categoryField.getValue());

        specieNames.ifPresentOrElse(this::updateChoiceBoxSpeciesValues, this::closeWindow);
    }

    private void updateChoiceBoxSpeciesValues(List<String> specieNames) {
        updateSpeciesChoiceBoxView(specieNames, specieField);
    }

    private void updateSpeciesChoiceBoxView(List<String> specieNames, ChoiceBox<String> field) {
        Platform.runLater(() -> {
            field.getItems().addAll(FXCollections.observableList(specieNames));
        });
    }

    private void closeWindow() {
        Stage stage = (Stage) categoryField.getScene().getWindow();
        Model.getInstance().getViewFactory().closeCurrentWindow(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
    }
}
