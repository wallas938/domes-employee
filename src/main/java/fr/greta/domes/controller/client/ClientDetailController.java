package fr.greta.domes.controller.client;

import fr.greta.domes.controller.NavigationController;
import fr.greta.domes.model.Navigation;
import fr.greta.domes.model.animal.Animal;
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
    private Label registrationDate;
    @FXML
    private Label clientAddress;
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
//        setFourthImage(currentAnimal.getFourthPicture());
//        setCategory(currentAnimal.getCategory().getName());
//        setSpecie(currentAnimal.getSpecie().getName());
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

    public Label getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress.setText(clientAddress);
    }

    public Label getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail.setText(clientEmail);
    }

    public Label getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber.setText(clientPhoneNumber);
    }

    public Label getClientFirstname() {
        return clientFirstname;
    }

    public void setClientFirstname(String clientFirstname) {
        this.clientFirstname.setText(clientFirstname);
    }

    public Label getClientLastname() {
        return clientLastname;
    }

    public void setClientLastname(String clientLastname) {
        this.clientLastname.setText(clientLastname);
    }

    public Label getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId.setText(clientId);
    }

    public Label getOrderNumberOfArticles() {
        return orderNumberOfArticles;
    }

    public void setOrderNumberOfArticles(int orderNumberOfArticles) {
        this.orderNumberOfArticles.setText(String.valueOf(orderNumberOfArticles));
    }

    public Label getOrderReference() {
        return orderReference;
    }

    public void setOrderReference(String orderReference) {
        this.orderReference.setText(orderReference);
    }

    public Label getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate.setText(purchaseDate);
    }

    public Button getShowOrderDetail() {
        return showOrderDetail;
    }
}
