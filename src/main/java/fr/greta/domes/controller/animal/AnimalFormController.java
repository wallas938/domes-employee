package fr.greta.domes.controller.animal;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import fr.greta.domes.controller.NavigationController;
import fr.greta.domes.model.Navigation;
import fr.greta.domes.model.animal.Animal;

import fr.greta.domes.model.animal.AnimalCreateDTO;
import fr.greta.domes.model.specie.Specie;
import fr.greta.domes.service.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

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

        NavigationController.getCurrentNavigation().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(Navigation.TO_ANIMALS_FORM)) {
                if (currentAnimal != null) {
                    setFields(currentAnimal);
                    save.setText("Modifier");
                } else {
                    try {
                    save.setText("Enregistrer");
                        resetFields();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        initEventListeners();

        if (currentAnimal != null) {
            setFields(currentAnimal);
            save.setText("Modifier");
        } else {
            save.setText("Enregistrer");
        }
    }

    private void setFields(Animal animal) {
        ageField.setText(String.valueOf(animal.getAge()));
        priceField.setText(String.valueOf(animal.getPrice()));
        categoryField.setValue(animal.getCategory().getName());
        specieField.setValue(animal.getSpecie().getName());
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

        ageField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (wasFocused) {
                try {
                    int age = Integer.parseInt(ageField.getText());
                    if (age > 0 && age <= 10) {
                        ageField.getStyleClass().remove("input-error");
                        ageTextError.setVisible(false);
                        animalFormFieldValidator.setAgeValid(true);
                    } else {
                        ageField.getStyleClass().add("input-error");
                        ageTextError.setVisible(true);
                        animalFormFieldValidator.setAgeValid(false);
                    }
                } catch (NumberFormatException e) {
                    ageField.getStyleClass().add("input-error");
                    ageTextError.setVisible(true);
                    animalFormFieldValidator.setAgeValid(false);
                }
            }
        });

        priceField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (wasFocused) {
                try {
                    double price = Double.parseDouble(priceField.getText());
                    if (price >= 50) {
                        priceTextError.setVisible(false);
                        priceField.getStyleClass().remove("input-error");
                        animalFormFieldValidator.setPriceValid(true);
                    } else {
                        priceTextError.setVisible(true);
                        priceField.getStyleClass().add("input-error");
                        animalFormFieldValidator.setPriceValid(false);
                    }
                } catch (NumberFormatException e) {
                    priceTextError.setVisible(true);
                    priceField.getStyleClass().add("input-error");
                    animalFormFieldValidator.setPriceValid(false);
                }
            }
        });

        mainPictureField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (wasFocused) {
                checkPictureUrlValidity(mainPictureField, mainPictureTextError);
            }
        });

        secondPictureField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (wasFocused) {
                checkPictureUrlValidity(secondPictureField, secondPictureTextError);
            }
        });

        thirdPictureField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (wasFocused) {
                checkPictureUrlValidity(thirdPictureField, thirdPictureTextError);
            }
        });

        fourthPictureField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (wasFocused) {
                checkPictureUrlValidity(fourthPictureField, fourthTextError);
            }
        });

        categoryField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (wasFocused) {
                animalFormFieldValidator.setCategoryValid(categoryField.getValue() != null);
            }
        });

        specieField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (wasFocused) {
                animalFormFieldValidator.setSpecieValid(specieField.getValue() != null);
            }
        });

        descriptionField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (wasFocused) {
                animalFormFieldValidator.setDescription(descriptionField.getText().length() <= 500);
            }
        });
    }

    private void checkPictureUrlValidity(TextField field, Label errorLabel) {
        try {
            URL url = new URL(field.getText());
            URLConnection connection = url.openConnection();
            connection.connect();
            // The URL is valid
            errorLabel.setVisible(false);
            System.out.println("No image error");
            field.getStyleClass().remove("input-error");
        } catch (Exception e) {
            System.out.println("Loading image error");
            // The URL is invalid
            errorLabel.setVisible(true);
            field.getStyleClass().add("input-error");
        }
    }

    public void save() throws IOException {

        if (animalFormFieldValidator.isAnimalFormIsValid().get() || currentAnimal == null) {
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
            animalService.saveAnimal(animalCreateDTO);
            System.out.println("Animal submitted");
            animalController.initTableView();
            NavigationController.setCurrentNavigation(Navigation.TO_ANIMALS);
        }
    }

    private void updateChoiceBoxCategoriesValues() {
        CategoryService categoryService = new CategoryServiceImpl();

        try {
            Collection<String> categories = categoryService.getAll();

            categoryField.getItems().addAll(FXCollections.observableList(categories.stream().toList()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetFields() throws IOException {
        ageField.setText("");
        priceField.setText("");
        mainPictureField.setText("");
        secondPictureField.setText("");
        thirdPictureField.setText("");
        fourthPictureField.setText("");

        List<String> categories = (List<String>) categoryService.getAll();

        categoryField.setItems(FXCollections.observableList(categories));
    }

    private void onChoiceBoxCategoriesChange() throws IOException {
        SpecieService specieService = new SpecieServiceImpl();
        // Reset old values
        specieField.getItems().clear();

        // Init ChoiceBox "TOUTES" value

        Response response = specieService.getAll(categoryField.getValue());

        updateChoiceBoxSpeciesValues(response);
    }

    private void updateChoiceBoxSpeciesValues(Response response) {
        updateSpeciesChoiceBoxView(response, specieField);
    }

    private void updateSpeciesChoiceBoxView(Response response, ChoiceBox<String> field) {
        Platform.runLater(() -> {
            ObjectMapper objectMapper = new ObjectMapper();

            ResponseBody responseBody = response.body();

            try {
                CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Specie.class);

                assert responseBody != null;
                List<Specie> species = objectMapper.readValue(responseBody.byteStream(), listType);

                List<String> speciesNames = species.stream().map(Specie::getName).toList();

                field.getItems().addAll(FXCollections.observableList(speciesNames));


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
