package fr.greta.domes.controller.client;

import fr.greta.domes.controller.NavigationController;
import fr.greta.domes.controller.animal.AnimalFormController;
import fr.greta.domes.model.Navigation;
import fr.greta.domes.model.client.Client;
import fr.greta.domes.model.client.ClientPage;
import fr.greta.domes.service.ClientService;
import fr.greta.domes.service.ClientServiceImpl;
import fr.greta.domes.utils.Utils;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ClientController implements Initializable {
    @FXML
    private TextField clientSearchBar;
    @FXML
    private TableView<Client> clientsTable = new TableView<>();
    @FXML
    private Pagination clientPagination = new Pagination(15, 0);
    @FXML
    private MenuButton clientSelectSizeValue;
    @FXML
    private TextField clientPageNumberField;
    @FXML
    private Button clientGoToPage;
    @FXML
    private Button clientReloadButton;
    @FXML
    private Label clientReloadMessage;

    private final ClientService clientService = new ClientServiceImpl();

    private final MenuItem size15 = new MenuItem("15");

    private final MenuItem size25 = new MenuItem("25");

    private final MenuItem size50 = new MenuItem("50");

    private static BooleanProperty reloadData = new SimpleBooleanProperty(false);

    private ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);

    public ClientController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initTableView();
            initEventListeners();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initEventListeners() {
        clientReloadButton.visibleProperty().bind(reloadData);

        clientReloadMessage.visibleProperty().bind(reloadData);

        clientReloadButton.setOnAction(event -> {
            try {
                updateTableView();
                setReloadData(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        clientSelectSizeValue.getItems().addAll(size15, size25, size50);

        clientSelectSizeValue.setText(size15.getText());

        /*
         * Size values event init
         * */
        size15.setOnAction(event -> {
            try {
                clientSelectSizeValue.setText(String.valueOf(Utils.intParser(size15.getText())));
                updateTableView();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        size25.setOnAction(event -> {
            try {
                clientSelectSizeValue.setText(String.valueOf(Utils.intParser(size25.getText())));
                updateTableView();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        size50.setOnAction(event -> {
            try {
                clientSelectSizeValue.setText(String.valueOf(Utils.intParser(size50.getText())));
                updateTableView();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        /*
         * Paging search event init
         * */
        clientGoToPage.setOnAction(event -> {
            try {
                updateTableView();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        clientSearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            Runnable task = () -> {
                Platform.runLater(() -> {
                    try {
                        updateTableView();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            };

            executor.shutdownNow();

            // Schedule a new task to run after 2 seconds
            executor = new ScheduledThreadPoolExecutor(1);
            executor.schedule(task, 500, TimeUnit.MILLISECONDS);
        });
    }

    public void setReloadData(boolean status) {
        reloadData.setValue(status);
    }

    public void initTableView() throws IOException {
        ClientPage clientPage = clientService.getClientPage(new ClientSearchQuery(
                "",
                "",
                "",
                "",
                1,
                Utils.intParser(size15.getText())));

        /*
         * Init Pagination values
         * */

        initTablePagination(Utils.intParser("1"), (clientPage.getTotalPages() - 1));

        /*
         * Init Table Columns
         * */

        TableColumn<Client, String> refColumn = new TableColumn<>("id");
        refColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Client, String> lastnameColumn = new TableColumn<>("Nom");
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));

        TableColumn<Client, String> firstnameColumn = new TableColumn<>("Prénom");
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));

        TableColumn<Client, String> phoneNumberColumn = new TableColumn<>("Téléphone");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Client, String> emailColumn = new TableColumn<>("@");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        clientsTable.getColumns().addAll(refColumn, lastnameColumn, firstnameColumn, phoneNumberColumn, emailColumn);

        /*
         * Set Events on each table rows
         * */
        clientsTable.setRowFactory(param -> {
            TableRow<Client> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Client client = row.getItem();
                    ClientDetailController.setCurrentClient(client);
                    showClientDetail();
                }
            });
            return row;
        });

        clientsTable.getItems().addAll(FXCollections.observableList(clientPage.getClients()));
    }

    public void updateTableView() throws IOException {
        clientsTable.setItems(FXCollections.observableList(List.of()));
        int pageNumber = Utils.intParser(clientPageNumberField.getText()) <= 0 ? 1 : Utils.intParser(clientPageNumberField.getText());
        clientPageNumberField.setText(String.valueOf(pageNumber));
        String searchBarValue = clientSearchBar.getText();
        ClientPage clientPage = clientService.searchBarGetClients(new ClientSearchQuery(
                searchBarValue,
                searchBarValue,
                searchBarValue,
                searchBarValue,
                pageNumber,
                Utils.intParser(clientSelectSizeValue.getText())));

        clientsTable.setItems(FXCollections.observableList(clientPage.getClients()));
    }

    private void showClientDetail() {
        NavigationController.setCurrentNavigation(Navigation.TO_CLIENT_DETAIL);
    }

    private void initTablePagination(int currentPage, int pageCount) {
        clientPagination.setPageCount(pageCount);
        clientPagination.setCurrentPageIndex(currentPage - 1);
    }
}
