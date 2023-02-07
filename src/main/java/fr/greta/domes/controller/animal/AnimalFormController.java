package fr.greta.domes.controller.animal;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import fr.greta.domes.controller.NavigationController;
import fr.greta.domes.model.Navigation;
import fr.greta.domes.model.animal.Animal;

import fr.greta.domes.model.animal.AnimalCreateDTO;
import fr.greta.domes.model.animal.AnimalEditDTO;
import fr.greta.domes.model.animal.AnimalFormFieldValidator;
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
                if (age > 0 && age <= 10) {
                    ageTextError.setVisible(false);
                    animalFormFieldValidator.setAgeValid(true);
                } else {
                    ageTextError.setVisible(true);
                    animalFormFieldValidator.setAgeValid(false);
                }
            } catch (NumberFormatException e) {
//                if(ageField.getText().trim().equals("")) {
//                    ageTextError.setVisible(false);
//                    animalFormFieldValidator.setAgeValid(true);
//                    return;
//                }
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
//                if(priceField.getText().trim().equals("")) {
//                    priceTextError.setVisible(false);
//                    animalFormFieldValidator.setPriceValid(true);
//                    return;
//                }
                priceTextError.setVisible(true);
                animalFormFieldValidator.setPriceValid(false);
            }
        });


        categoryField.valueProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (categoryField.getValue() != null) {
                animalFormFieldValidator.setCategoryValid(true);
            } else {
                animalFormFieldValidator.setCategoryValid(false);
            }
        });

        specieField.valueProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (specieField.getValue() != null) {
                animalFormFieldValidator.setSpecieValid(true);
            } else {
                animalFormFieldValidator.setSpecieValid(false);
            }
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


//    private void checkPictureUrlValidity(TextField field, Label errorLabel) {
//        try {
//            URL url = new URL(field.getText());
//            URLConnection connection = url.openConnection();
//            connection.connect();
//            // The URL is valid
//            errorLabel.setVisible(false);
//        } catch (Exception e) {
//            // The URL is invalid
//            errorLabel.setVisible(true);
//        }
//    }

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

            animalService.saveAnimal(animalCreateDTO);

            animalController.initTableView();

            NavigationController.setCurrentNavigation(Navigation.TO_ANIMALS);

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
            animalService.editAnimal(dto);
        }
    }

    /*
     * View Updating
     * */

    private void updateChoiceBoxCategoriesValues() {
        CategoryService categoryService = new CategoryServiceImpl();

        try {
            Collection<String> categories = categoryService.getAll();

            categoryField.setItems(FXCollections.observableList(categories.stream().toList()));

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

        List<String> categories = (List<String>) categoryService.getAll();

        categoryField.setItems(FXCollections.observableList(categories));
        specieField.getItems().clear();
        specieField.setValue(null);
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
