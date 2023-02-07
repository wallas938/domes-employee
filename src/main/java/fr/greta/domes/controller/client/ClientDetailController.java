package fr.greta.domes.controller.client;

import fr.greta.domes.controller.NavigationController;
import fr.greta.domes.model.Navigation;
import fr.greta.domes.model.client.Address;
import fr.greta.domes.model.client.Client;
import fr.greta.domes.model.order.Order;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientDetailController implements Initializable {

    private static Client currentClient;
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
    @FXML
    private Button editBtn;
    @FXML
    private Button removeBtn;
    @FXML
    private Button showAllClientOrder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFields(currentClient);

        NavigationController.getCurrentNavigation().addListener((observable, oldValue, newValue) -> {
            setFields(currentClient);
            handleOrderActionVisibility();
            initEventListeners();
        });
        initEventListeners();

    }

    private void initEventListeners() {
        editBtn.setOnAction(event -> showClientForm());
    }

    private void showClientForm() {
        ClientFormController.setClientData(currentClient);
        NavigationController.setCurrentNavigation(Navigation.TO_CLIENTS_FORM);
    }

    private void handleOrderActionVisibility() {
        if (currentClient.getLastOrder() != null) {
            showOrderDetail.setVisible(true);
            showAllClientOrder.setVisible(true);
        } else {
            showOrderDetail.setVisible(false);
            showAllClientOrder.setVisible(false);
        }
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
        if (currentClient.getLastOrder() != null) setLastOrder(currentClient.getLastOrder());
        else resetLastOrderFields();
    }

    private void resetLastOrderFields() {
        orderTotal.setText("0");
        orderReference.setText("Non disponible");
        orderNumberOfArticles.setText("Non disponible");
        purchaseDate.setText("Non disponible");
    }

    private void setLastOrder(Order lastOrder) {
        setOrderTotal(lastOrder.getTotal());
        setPurchaseDate(lastOrder.getPurchaseDate().toString());
        setOrderNumberOfArticles(lastOrder.getNumberOfArticles());
        setOrderReference(lastOrder.getId().toString());
    }

    public static Client getCurrentClient() {
        return currentClient;
    }

    public String getRegistrationDate() {
        return registrationDate.getText();
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

    public String getClientEmail() {
        return clientEmail.getText();
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail.setText(clientEmail);
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber.getText();
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber.setText(clientPhoneNumber);
    }

    public String getClientFirstname() {
        return clientFirstname.getText();
    }

    public void setClientFirstname(String clientFirstname) {
        this.clientFirstname.setText(clientFirstname);
    }

    public String getClientLastname() {
        return clientLastname.getText();
    }

    public void setClientLastname(String clientLastname) {
        this.clientLastname.setText(clientLastname);
    }

    public String getClientId() {
        return clientId.getText();
    }

    public void setClientId(String clientId) {
        this.clientId.setText(clientId);
    }

    public String getOrderNumberOfArticles() {
        return orderNumberOfArticles.getText();
    }

    public void setOrderNumberOfArticles(int orderNumberOfArticles) {
        if (orderNumberOfArticles == 0) this.orderNumberOfArticles.setText("null");
        else this.orderNumberOfArticles.setText(String.valueOf(orderNumberOfArticles));
    }

    public String getOrderReference() {
        return orderReference.getId();
    }

    public void setOrderReference(String orderReference) {
        if (orderReference.isBlank()) this.orderReference.setText("null");
        else this.orderReference.setText(orderReference);
    }

    public String getOrderTotal() {
        return orderTotal.getText();
    }

    public void setOrderTotal(double orderTotal) {
        if (currentClient.getLastOrder() != null) this.orderTotal.setText(String.valueOf(orderTotal));
        else this.orderTotal.setText("null");
    }

    public String getPurchaseDate() {
        return purchaseDate.getText();
    }

    public void setPurchaseDate(String purchaseDate) {
        if (purchaseDate.isBlank()) {
            this.purchaseDate.setText("null");
            return;
        }
        this.purchaseDate.setText(purchaseDate);
    }
}
