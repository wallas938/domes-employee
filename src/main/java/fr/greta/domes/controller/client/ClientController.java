package fr.greta.domes.controller.client;

import fr.greta.domes.model.client.Client;
import fr.greta.domes.model.client.ClientPage;
import fr.greta.domes.service.ClientService;
import fr.greta.domes.service.ClientServiceImpl;
import fr.greta.domes.utils.Utils;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    @FXML
    private TextField clientSearchBar;
    @FXML
    private Button clientSearchButton;
    @FXML
    private TableView<Client> clientsTable =  new TableView<>();
    @FXML
    private Pagination clientPagination = new Pagination(15, 0);
    @FXML
    private MenuButton clientSelectSizeValue;
    @FXML
    private TextField clientPageNumberField;
    @FXML
    private Button clientGoToPage;
    @FXML
    private Button addClientButton;
    @FXML
    private Button clientReloadButton;
    @FXML
    private Label clientReloadMessage;

    private final ClientService clientService = new ClientServiceImpl();

    private final MenuItem size15 = new MenuItem("15");

    private final MenuItem size25 = new MenuItem("25");

    private final MenuItem size50 = new MenuItem("50");

    private static BooleanProperty reloadData = new SimpleBooleanProperty(false);

    public ClientController() {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initTableView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initTableView() throws IOException {
        ClientPage clientPage = clientService.getClientPage(new ClientSearchQuery(
                "",
                "",
                "",
                "",
                Utils.intParser(clientPageNumberField.getText()),
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
                    System.out.println(client);
//                    AnimalDetailController.setCurrentAnimal(client);
                    //showAnimalDetail();
                }
            });
            return row;
        });

        clientsTable.setItems(FXCollections.observableList(clientPage.getClients()));
    }

    private void initTablePagination(int currentPage, int pageCount) {
        clientPagination.setPageCount(pageCount);
        clientPagination.setCurrentPageIndex(currentPage - 1);
    }
}
