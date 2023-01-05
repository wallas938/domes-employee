package fr.greta.domes.controller.animal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import fr.greta.domes.controller.NavigationController;
import fr.greta.domes.model.animal.AnimalPage;
import fr.greta.domes.model.category.Category;
import fr.greta.domes.model.animal.Animal;

import fr.greta.domes.model.Navigation;
import fr.greta.domes.model.specie.Specie;
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
    @FXML
    private Button filterButton;

    private MenuItem size15 = new MenuItem("15");
    private MenuItem size25 = new MenuItem("25");
    private MenuItem size50 = new MenuItem("50");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initFilterFields();

        initTableView();

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
            updateTableView(Integer.parseInt(size15.getText()), numberParser(pageNumberField.getText()));
            selectSizeValue.setText(String.valueOf(numberParser(size15.getText())));
        });
        size25.setOnAction(event -> {
            updateTableView(Integer.parseInt(size25.getText()), numberParser(pageNumberField.getText()));
            selectSizeValue.setText(String.valueOf(numberParser(size25.getText())));
        });
        size50.setOnAction(event -> {
            updateTableView(Integer.parseInt(size50.getText()), numberParser(pageNumberField.getText()));
            selectSizeValue.setText(String.valueOf(numberParser(size50.getText())));
        });

        /*
         * Paging search event init
         * */
        goToPage.setOnAction(event -> {
            updateTableView(numberParser(selectSizeValue.getText()), numberParser(pageNumberField.getText()));
        });
    }

    private void initTablePagination(int currentPage, int pageCount) {
        pagination.setPageCount(pageCount);
        pagination.setCurrentPageIndex(currentPage - 1);
        pagination.lookupAll(".bullet-button").forEach(node -> {
            System.out.println(node);
        });

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

    private void initTableView() {
        OkHttpClient client = new OkHttpClient();
        // FETCH animals by filters - complete request url
        Request request = new Request.Builder().url(String.format("http://localhost:8081/api/animals?pageNumber=%s&pageSize=%s", numberParser(pageNumberField.getText()), size15.getText())).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ObjectMapper objectMapper = new ObjectMapper();
                ResponseBody responseBody = response.body();

                try {

                    AnimalPage animals = objectMapper.readValue(responseBody.byteStream(), AnimalPage.class);

                    Platform.runLater(() -> {
                        /*
                         * Init Pagination values
                         * */
                        int byElements = animals.getTotalElements() > 0 ? animals.getTotalElements() : 1;

                        initTablePagination(numberParser(pageNumberField.getText()), (animals.getTotalPages() - 1));

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
                        animalsTable.setItems(FXCollections.observableList(animals.getAnimals()));

//                        createPage(animals);

                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    private void updateTableView(int pageSize, int pageNumber) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(String.format("http://localhost:8081/api/animals?pageNumber=%s&pageSize=%s", pageNumber, pageSize)).build();

        Platform.runLater(() -> {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("error");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    ObjectMapper objectMapper = new ObjectMapper();

                    ResponseBody responseBody = response.body();

                    try {
                        AnimalPage animals = objectMapper.readValue(responseBody.byteStream(), AnimalPage.class);

                        Platform.runLater(() -> {
                            /*
                             * Init Pagination values
                             * */
                            int byElements = animals.getTotalElements() > 0 ? animals.getTotalElements() : 1;

                            initTablePagination(numberParser(pageNumberField.getText()), (animals.getTotalPages() - 1));
                animalsTable.setItems(FXCollections.observableList(animals.getAnimals()));

//                            createPage(animals);

                        });
                    } catch (IOException e) {
                        System.out.println(e.getMessage());

                    }
                }
            });
        });
    }

//    private void createPage(AnimalPage animalPage) {
//        pagination.setPageCount(animalPage.getAnimals().size());
//        pagination.setCurrentPageIndex(numberParser(selectSizeValue.getText())-1);
//        pagination.setPageFactory(new javafx.util.Callback<Integer, Node>() {
//            public Node call(Integer pageIndex) {
//                int fromIndex = (pageIndex * Integer.parseInt(selectSizeValue.getText()));
//                int toIndex = Math.min(fromIndex + Integer.parseInt(selectSizeValue.getText()), animalPage.getAnimals().size());
//                System.out.println(pageIndex);
//                animalsTable.setItems(FXCollections.observableList(animalPage.getAnimals().subList(fromIndex, toIndex)));
//                return animalsTable;
//            }
//        });
//    }

    private int numberParser(String pageNumberString) {
        int pageNumber = 1;
        try {
            // Gérer le type de la pageNumber
            pageNumber = Integer.parseInt(pageNumberString);
            return pageNumber;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            pageNumber = 1;
            pageNumberField.setText("1");
            return pageNumber;
        }
    }

    private void showAnimalDetail() {
        NavigationController.setCurrentNavigation(Navigation.TO_ANIMAL_DETAIL);
//        Model.getInstance().getViewFactory().getAnimalDetailView();
    }

    private void showAnimalForm() {
        AnimalFormController.setAnimalData(null);
        NavigationController.setCurrentNavigation(Navigation.TO_ANIMALS_FORM);
//        Model.getInstance().getViewFactory().getAnimalFormView();
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
