package fr.greta.domes.controller.client;

import fr.greta.domes.controller.NavigationController;
import fr.greta.domes.model.client.Address;
import fr.greta.domes.model.client.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientDetailController implements Initializable {

    private static Client currentClient;
    @FXML
    private Button editBtn;
    @FXML
    private Button removeBtn;
    @FXML
    private Label orderTotal;
    @FXML
    private Label clientCountry;
    @FXML
    private Label clientZipCode;
    @FXML
    private Label clientCity;
    @FXML
    private Label clientStreet;
    @FXML
    private Label registrationDate;
    @FXML
    private Label clientEmail;
    @FXML
    private Label clientPhoneNumber;
    @FXML
    private Label clientFirstname;
    @FXML
    private Label clientLastname;
    @FXML
    private Label clientId;
    @FXML
    private Label orderNumberOfArticles;
    @FXML
    private Label orderReference;
    @FXML
    private Label purchaseDate;
    @FXML
    private Button showOrderDetail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFields(currentClient);
        NavigationController.getCurrentNavigation().addListener((observable, oldValue, newValue) -> {
//            if (newValue.equals(Navigation.TO_CLIENT_DETAIL))
            setFields(currentClient);
        });
    }

    public static void setCurrentClient(Client client) {
        currentClient = client;
    }

    public void setFields(Client currentClient) {

        setClientId(currentClient.getId().toString());
        setClientLastname(currentClient.getLastname());
        setClientFirstname(currentClient.getFirstname());
        setClientAddress(currentClient.getAddress());
        setClientPhoneNumber(currentClient.getPhoneNumber());
        setClientEmail(currentClient.getEmail());
        setRegistrationDate(currentClient.getRegistrationDate().toString());
    }

    public static Client getCurrentClient() {
        return currentClient;
    }

    public Label getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate.setText(registrationDate);
    }

    public void setClientAddress(Address clientAddress) {
        this.clientCountry.setText(clientAddress.getCountry());
        this.clientCity.setText(clientAddress.getCity());
        this.clientZipCode.setText(clientAddress.getZipcode());
        this.clientStreet.setText(clientAddress.getStreet());
    }

    public Label getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        if (clientEmail.isBlank()) {
            this.clientEmail.setText("");
            return;
        }
        this.clientEmail.setText(clientEmail);
    }

    public Label getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        if (clientPhoneNumber.isBlank()) {
            this.clientPhoneNumber.setText("");
            return;
        }
        this.clientPhoneNumber.setText(clientPhoneNumber);
    }

    public Label getClientFirstname() {
        return clientFirstname;
    }

    public void setClientFirstname(String clientFirstname) {
        if (clientFirstname.isBlank()) {
            this.clientFirstname.setText("");
            return;
        }
        this.clientFirstname.setText(clientFirstname);
    }

    public Label getClientLastname() {
        return clientLastname;
    }

    public void setClientLastname(String clientLastname) {
        if (clientLastname.isBlank()) {
            this.clientLastname.setText("");
            return;
        }
        this.clientLastname.setText(clientLastname);
    }

    public Label getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        if (clientId.isBlank()) {
            this.clientId.setText("");
            return;
        }
        this.clientId.setText(clientId);
    }

    public Label getOrderNumberOfArticles() {
        return orderNumberOfArticles;
    }

    public void setOrderNumberOfArticles(int orderNumberOfArticles) {
        if (orderNumberOfArticles == 0) {
            this.orderNumberOfArticles.setText("");
            return;
        }
        this.orderNumberOfArticles.setText(String.valueOf(orderNumberOfArticles));
    }

    public Label getOrderReference() {
        return orderReference;
    }

    public void setOrderReference(String orderReference) {
        if (orderReference.isBlank()) {
            this.orderReference.setText("");
            return;
        }
        this.orderReference.setText(orderReference);
    }

    public Label getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal.setText(String.valueOf(orderTotal));
    }

    public Label getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        if (purchaseDate.isBlank()) {
            this.purchaseDate.setText("");
            return;
        }
        this.purchaseDate.setText(purchaseDate);
    }

    public Button getShowOrderDetail() {
        return showOrderDetail;
    }
}
