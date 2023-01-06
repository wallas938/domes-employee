package fr.greta.domes.controller.animal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import fr.greta.domes.controller.NavigationController;
import fr.greta.domes.model.animal.AnimalPage;
import fr.greta.domes.model.category.Category;
import fr.greta.domes.model.animal.Animal;

import fr.greta.domes.model.Navigation;
import fr.greta.domes.model.specie.Specie;
import fr.greta.domes.service.AnimalService;
import fr.greta.domes.service.AnimalServiceImpl;
import fr.greta.domes.utils.Utils;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AnimalController implements Initializable {
    @FXML
    private Pagination pagination;
    @FXML
    private Button goToPage;
    @FXML
    private TextField pageNumberField;
    @FXML
    private MenuButton selectSizeValue;
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
    private Category filterCurrentCategory;
    private Specie filterCurrentSpecie;
    private AnimalService animalService = new AnimalServiceImpl();
    @FXML
    private Button filterButton;
    private MenuItem size15 = new MenuItem("15");
    private MenuItem size25 = new MenuItem("25");
    private MenuItem size50 = new MenuItem("50");

    private AnimalPage animalPage;

    public AnimalController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initFilterFields();

        try {
            initTableView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        initEventListeners();
    }

    private void initEventListeners() {

        addAnimalButton.setOnMouseClicked(event -> {
            showAnimalForm();
        });

        byCategory.setOnAction(event -> {
            onChoiceBoxCategoriesChange();
        });
        /*
         * Animal Page size init
         * */
        selectSizeValue.getItems().addAll(size15, size25, size50);

        selectSizeValue.setText(size15.getText());

        /*
         * Size values event init
         * */
        size15.setOnAction(event -> {
            try {
                updateTableView(Integer.parseInt(size15.getText()), Utils.intParser(pageNumberField.getText()));
                selectSizeValue.setText(String.valueOf(Utils.intParser(size15.getText())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        size25.setOnAction(event -> {
            try {
                updateTableView(Integer.parseInt(size25.getText()), Utils.intParser(pageNumberField.getText()));
                selectSizeValue.setText(String.valueOf(Utils.intParser(size25.getText())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        size50.setOnAction(event -> {
            try {
                updateTableView(Integer.parseInt(size50.getText()), Utils.intParser(pageNumberField.getText()));
                selectSizeValue.setText(String.valueOf(Utils.intParser(size50.getText())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        /*
         * Paging search event init
         * */
        goToPage.setOnAction(event -> {
            try {
                updateTableView(Utils.intParser(selectSizeValue.getText()), Utils.intParser(pageNumberField.getText()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        filterButton.setOnAction(event -> {
            try {
                updateTableView(Utils.intParser(selectSizeValue.getText()), Utils.intParser(pageNumberField.getText()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void initTablePagination(int currentPage, int pageCount) {
        pagination.setPageCount(pageCount);
        pagination.setCurrentPageIndex(currentPage - 1);
    }

    private void onChoiceBoxCategoriesChange() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(String.format("http://localhost:8081/api/species/categoryName?categoryName=%s", byCategory.getValue())).build();

        // Reset old values
        bySpecie.getItems().clear();

        // Init ChoiceBox "TOUTES" value
        bySpecie.getItems().add("TOUTES");

        Platform.runLater(() -> {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    updateChoiceBoxSpeciesValues(response);
                }
            });
        });
    }

    private void updateChoiceBoxSpeciesValues(Response response) {
        Platform.runLater(() -> {
            // Update UI here
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseBody responseBody = response.body();
            try {
                CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Specie.class);

                List<Specie> species = objectMapper.readValue(responseBody.byteStream(), listType);

                List<String> speciesNames = species.stream().map(specie -> specie.getName()).toList();

                bySpecie.getItems().addAll(FXCollections.observableList(speciesNames));


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void updateChoiceBoxCategoriesValues() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("http://localhost:8081/api/categories").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Platform.runLater(() -> {
                    // Update UI here
                    ObjectMapper objectMapper = new ObjectMapper();
                    ResponseBody responseBody = response.body();
                    try {
                        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Category.class);

                        List<Category> categories = objectMapper.readValue(responseBody.byteStream(), listType);

                        List<String> categoriesNames = categories.stream().map(category -> category.getName()).toList();

                        byCategory.getItems().addAll(FXCollections.observableList(categoriesNames));

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        });
    }

    private void initFilterFields() {
        setPriceFieldsVisibility(false);

        setAgeFieldsVisibility(false);

        firstChoiceBoxesInit();
    }

    private void firstChoiceBoxesInit() {
        byCategory.getItems().add("TOUTES");
        bySpecie.getItems().add("TOUTES");

        bySpecie.setValue("TOUTES");
        byCategory.setValue("TOUTES");

        // Update category choiceBox values
        updateChoiceBoxCategoriesValues();

    }

    private void initTableView() throws IOException {
        AnimalPage animalPage = animalService.getAnimalPage(new AnimalSearchQuery(
                Utils.doubleParser(minPriceValue.getText()),
                Utils.doubleParser(maxPriceValue.getText()),
                Utils.intParser(minAgeValue.getText()),
                Utils.intParser(maxAgeValue.getText()),
                byCategory.getValue(),
                bySpecie.getValue(),
                Utils.intParser(pageNumberField.getText()),
                Utils.intParser(size15.getText())));

        /*
         * Init Pagination values
         * */
        int byElements = animalPage.getTotalElements() > 0 ? animalPage.getTotalElements() : 1;

        initTablePagination(Utils.intParser(pageNumberField.getText()), (animalPage.getTotalPages() - 1));

        /*
         * Init Table Columns
         * */

        TableColumn<Animal, String> refColumn = new TableColumn<>("ref");
        refColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Animal, String> categoryColumn = new TableColumn<>("category");
        categoryColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCategory().getName()));

        TableColumn<Animal, String> specieColumn = new TableColumn<>("specie");
        specieColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getSpecie().getName()));

        TableColumn<Animal, Integer> priceColumn = new TableColumn<>("price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Animal, String> ageColumn = new TableColumn<>("age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        animalsTable.getColumns().addAll(refColumn, categoryColumn, specieColumn, priceColumn, ageColumn);

        /*
         * Set Events on each table rows
         * */
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
        animalsTable.setItems(FXCollections.observableList(animalPage.getAnimals()));
//                        createPage(animals);

    }

    private void updateTableView(int pageSize, int pageNumber) throws IOException {
        double minPrice = Utils.doubleParser(minPriceValue.getText());
        double maxPrice = Utils.doubleParser(maxPriceValue.getText());
        int minAge = Utils.intParser(minAgeValue.getText());
        int maxAge = Utils.intParser(maxAgeValue.getText());
        AnimalPage animalPage = animalService.getAnimalPage(new AnimalSearchQuery(
                minPrice,
                maxPrice,
                minAge,
                maxAge,
                byCategory.getValue(),
                bySpecie.getValue(),
                pageNumber,
                pageSize));

        minAgeValue.setText(String.valueOf(minAge));
        maxAgeValue.setText(String.valueOf(maxAge == 1 ? 999 : maxAge));
        minPriceValue.setText(String.valueOf(minPrice));
        maxPriceValue.setText(String.valueOf(maxPrice == 1.0 ? 999.99 : maxPrice));
        /*
         * Init Pagination values
         * */
        int byElements = animalPage.getTotalElements() > 0 ? animalPage.getTotalElements() : 1;

        initTablePagination(pageNumber, (animalPage.getTotalPages() - 1));

        animalsTable.setItems(FXCollections.observableList(animalPage.getAnimals()));

//                            createPage(animals);

    }

    private void showAnimalDetail() {
        NavigationController.setCurrentNavigation(Navigation.TO_ANIMAL_DETAIL);
    }

    private void showAnimalForm() {
        AnimalFormController.setAnimalData(null);
        NavigationController.setCurrentNavigation(Navigation.TO_ANIMALS_FORM);
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
}
