package fr.greta.domes.controller.animal;

import fr.greta.domes.controller.NavigationController;
import fr.greta.domes.controller.service.AnimalService;
import fr.greta.domes.model.Animal;
import fr.greta.domes.model.Model;
import fr.greta.domes.model.Navigation;
import fr.greta.domes.model.enums.Category;
import fr.greta.domes.model.enums.Specie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class AnimalController implements Initializable {
    @FXML
    private Button addAnimalButton;
    @FXML
    private TableView<Animal> animalsTable;
    @FXML
    private CheckBox priceCheckboxStatus;
    @FXML
    private CheckBox ageCheckboxStatus;
    @FXML
    private Label minPriceLabel;
    @FXML
    private TextField minPriceValue;
    @FXML
    private Label maxPriceLabel;
    @FXML
    private TextField maxPriceValue;
    @FXML
    private Label minAgeLabel;
    @FXML
    private TextField minAgeValue;
    @FXML
    private Label maxAgeLabel;
    @FXML
    private TextField maxAgeValue;
    @FXML
    private ChoiceBox<String> byCategory;
    @FXML
    private ChoiceBox<String> bySpecie;
    @FXML
    private Button filterButton;
    @FXML
    private MenuButton selectPageNumber;
    @FXML
    private MenuButton selectSizeValue;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initFilterValues();
        initTableView();

        /*
        * Keep at the last position
        * */
        initEventListeners();
    }

    private void initEventListeners() {
        addAnimalButton.setOnMouseClicked(event -> {
            showAnimalForm();
        });
    }

    private void initFilterValues() {
        setPriceFieldsVisibility(false);
        setAgeFieldsVisibility(false);

        byCategory.getItems().addAll(AnimalService.categories());
        bySpecie.getItems().addAll(AnimalService.categories());
    }
    private void initTableView() {
        ObservableList<Animal> animals = FXCollections.observableList(
                List.of(
                        new Animal(UUID.randomUUID(), null, "https://www.webbox.co.uk/wp-content/uploads/2020/10/angry_cat_2-scaled.jpg", null, "https://www.webbox.co.uk/wp-content/uploads/2020/10/angry_cat_2-scaled.jpg", null, Category.REPTILE, Specie.Alligator_Snapping_Turtle, 20.20, 40),
                        new Animal(UUID.randomUUID(), null, null, null, null, null, Category.REPTILE, Specie.Aldabra_Tortoise, 200.20, 18),
                        new Animal(UUID.randomUUID(), null, null, null, null, null, Category.REPTILE, Specie.American_Alligator, 120.20, 24),
                        new Animal(UUID.randomUUID(), null, null, null, null, null, Category.REPTILE, Specie.Armenian_Viper, 40.20, 32),
                        new Animal(UUID.randomUUID(), null, null, null, null, null, Category.REPTILE, Specie.Ball_Python, 90.20, 20),
                        new Animal(UUID.randomUUID(), null, null, null, null, null, Category.REPTILE, Specie.Boelen_Python, 80.20, 7)
                )
        );

        animalsTable.setItems(animals);

        TableColumn<Animal, String> refColumn = new TableColumn<>("ref");
        refColumn.setCellValueFactory(new PropertyValueFactory<>("uuid"));

        TableColumn<Animal, Integer> categoryColumn = new TableColumn<>("category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Animal, String> specieColumn = new TableColumn<>("specie");
        specieColumn.setCellValueFactory(new PropertyValueFactory<>("specie"));

        TableColumn<Animal, Integer> priceColumn = new TableColumn<>("price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Animal, String> ageColumn = new TableColumn<>("age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        animalsTable.getColumns().addAll(refColumn, categoryColumn, specieColumn, priceColumn, ageColumn);

        animalsTable.setRowFactory(param -> {
            TableRow<Animal> row = new TableRow<>();
            row.setOnMouseClicked(event -> {

                if (!row.isEmpty()) {
                    Animal animal = row.getItem();
                    AnimalDetailController.setCurrentAnimal(animal);
                    showAnimalDetail();
                }
            });
            return row;
        });
    }

    private void showAnimalDetail() {
        NavigationController.setCurrentNavigation(Navigation.TO_ANIMAL_DETAIL);
        Model.getInstance().getViewFactory().getAnimalDetailView();
    }

    private void showAnimalForm() {
        NavigationController.setCurrentNavigation(Navigation.TO_ANIMALS_FORM);
        Model.getInstance().getViewFactory().getAnimalFormView();
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
        if (priceCheckboxStatus.isSelected()) {
            setPriceFieldsVisibility(true);
            return;
        }
        setPriceFieldsVisibility(false);
        return;
    }

    public void toggleAgeCheckbox() {
        if (ageCheckboxStatus.isSelected()) {
            setAgeFieldsVisibility(true);
            return;
        }
        setAgeFieldsVisibility(false);
        return;
    }

    private boolean isPriceChecked() {
        return priceCheckboxStatus.isSelected();
    }

    private boolean isAgeChecked() {
        return ageCheckboxStatus.isSelected();
    }

    private int getMinPriceValue() {
        if (Integer.parseInt(minPriceValue.getText()) >= 0 && Integer.parseInt(minPriceValue.getText()) < Integer.parseInt(maxPriceValue.getText())) {
            return Integer.parseInt(minPriceValue.getText());
        }
        return 0;
    }

    private int getMaxPriceValue() {
        if (Integer.parseInt(maxPriceValue.getText()) > 0 && Integer.parseInt(maxPriceValue.getText()) > Integer.parseInt(minPriceValue.getText())) {
            return Integer.parseInt(maxPriceValue.getText());
        }
        return 1;
    }
}