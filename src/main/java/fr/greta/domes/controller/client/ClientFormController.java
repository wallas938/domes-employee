package fr.greta.domes.controller.client;

import fr.greta.domes.controller.NavigationController;
import fr.greta.domes.model.Navigation;
import fr.greta.domes.model.client.Address;
import fr.greta.domes.model.client.Client;
import fr.greta.domes.model.client.ClientFormFieldValidator;
import fr.greta.domes.model.client.ClientPutDTO;
import fr.greta.domes.service.ClientService;
import fr.greta.domes.service.ClientServiceImpl;
import fr.greta.domes.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientFormController implements Initializable {
    @FXML
    private Label lastnameErrorMessage;
    @FXML
    private Label firstnameErrorMessage;
    @FXML
    private Label phoneNumberErrorMessage;
    @FXML
    private Label emailErrorMessage;
    @FXML
    private Label countryErrorMessage;
    @FXML
    private Label cityErrorMessage;
    @FXML
    private Label streetErrorMessage;
    @FXML
    private Label zipCodeErrorMessage;
    @FXML
    private TextField zipCodeField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private Button submit;
    @FXML
    private Button cancelEditClient;

    static private Client currentClient;

    private ClientFormFieldValidator clientFormFieldValidator = new ClientFormFieldValidator();

    private final ClientService clientService = new ClientServiceImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initFormValues();
        initEventListeners();
        NavigationController.getCurrentNavigation().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(Navigation.TO_CLIENTS_FORM)) {
                initFormValues();
                initEventListeners();
            }
        });
    }

    private void initEventListeners() {
        submit.setOnAction(event -> {
            try {
                if(clientFormFieldValidator.isClientFormIsValid().get())
                    submitEditedClient();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        initRequiredFieldValidators();

        cancelEditClient.setOnAction(event -> NavigationController.setCurrentNavigation(Navigation.TO_CLIENTS));

        submit.disableProperty().bind(clientFormFieldValidator.isClientFormIsValid().not());
    }

    private void submitEditedClient() throws IOException {
        ClientPutDTO clientPutDTO = new ClientPutDTO();
        Address address = new Address();
        address.setCountry(countryField.getText());
        address.setCity(cityField.getText());
        address.setStreet(streetField.getText());
        address.setZipcode(zipCodeField.getText());
        clientPutDTO.setAddress(address);
        clientPutDTO.setLastname(lastnameField.getText());
        clientPutDTO.setFirstname(firstnameField.getText());
        clientPutDTO.setPhoneNumber(phoneNumberField.getText());
        clientPutDTO.setEmail(emailField.getText());
        clientPutDTO.setId(currentClient.getId());
        clientService.editClient(clientPutDTO);
    }

    private void initRequiredFieldValidators() {
        lastnameField.textProperty().addListener(event -> {
            if (lastnameField.getText().isBlank() || lastnameField.getText().length() > 100) {
                lastnameErrorMessage.setVisible(true);
                clientFormFieldValidator.setIsLastnameValid(false);
                return;
            }
            lastnameErrorMessage.setVisible(false);
            clientFormFieldValidator.setIsLastnameValid(true);
        });

        firstnameField.textProperty().addListener(event -> {
            if (firstnameField.getText().isBlank() || firstnameField.getText().length() > 100) {
                firstnameErrorMessage.setVisible(true);
                clientFormFieldValidator.setIsFirstnameValid(false);
                return;
            }
            firstnameErrorMessage.setVisible(false);
            clientFormFieldValidator.setIsFirstnameValid(true);
        });

        phoneNumberField.textProperty().addListener(event -> {
            if (phoneNumberField.getText().isBlank() || phoneNumberField.getText().length() > 10) {
                phoneNumberErrorMessage.setVisible(true);
                clientFormFieldValidator.setIsPhoneNumberValid(false);
                return;
            }
            phoneNumberErrorMessage.setVisible(false);
            clientFormFieldValidator.setIsPhoneNumberValid(true);
        });

        emailField.textProperty().addListener(event -> {
            if (emailField.getText().isBlank() || !Utils.isEmailValid(emailField.getText())) {
                emailErrorMessage.setVisible(true);
                clientFormFieldValidator.setIsEmailValid(false);
                return;
            }
            emailErrorMessage.setVisible(false);
            clientFormFieldValidator.setIsEmailValid(true);
        });

        countryField.textProperty().addListener(event -> {
            if (countryField.getText().isBlank() || countryField.getText().length() > 100) {
                countryErrorMessage.setVisible(true);
                clientFormFieldValidator.setIsCountryValid(false);
                return;
            }
            countryErrorMessage.setVisible(false);
            clientFormFieldValidator.setIsCountryValid(true);
        });
        cityField.textProperty().addListener(event -> {
            if (cityField.getText().isBlank() || cityField.getText().length() > 100) {
                cityErrorMessage.setVisible(true);
                clientFormFieldValidator.setIsCityValid(false);
                return;
            }
            cityErrorMessage.setVisible(false);
            clientFormFieldValidator.setIsCityValid(true);
        });
        streetField.textProperty().addListener(event -> {
            if (streetField.getText().isBlank() || streetField.getText().length() > 255) {
                streetErrorMessage.setVisible(true);
                clientFormFieldValidator.setIsStreetValid(false);
                return;
            }
            streetErrorMessage.setVisible(false);
            clientFormFieldValidator.setIsStreetValid(true);
        });
        zipCodeField.textProperty().addListener(event -> {
            if (zipCodeField.getText().isBlank() || zipCodeField.getText().length() > 20 || zipCodeField.getText().length() < 3) {
                zipCodeErrorMessage.setVisible(true);
                clientFormFieldValidator.setIsZipCode(false);
                return;
            }
            zipCodeErrorMessage.setVisible(false);
            clientFormFieldValidator.setIsZipCode(true);
        });
    }

    static void setClientData(Client client) {
        currentClient = client;
    }

    private void initFormValues() {
        setClientEmail(currentClient.getEmail());
        setClientAddress(currentClient.getAddress());
        setClientLastname(currentClient.getLastname());
        setClientFirstname(currentClient.getFirstname());
        setClientPhoneNumber(currentClient.getPhoneNumber());
    }


    void setClientLastname(String lastname) {
        lastnameField.setText(lastname);
        clientFormFieldValidator.setIsLastnameValid(true);
    }

    void setClientFirstname(String firstname) {
        firstnameField.setText(firstname);
        clientFormFieldValidator.setIsFirstnameValid(true);
    }

    void setClientEmail(String email) {
        emailField.setText(email);
        clientFormFieldValidator.setIsEmailValid(true);
    }

    void setClientPhoneNumber(String phoneNumber) {
        phoneNumberField.setText(phoneNumber);
        clientFormFieldValidator.setIsPhoneNumberValid(true);
    }

    void setClientAddress(Address address) {
        countryField.setText(address.getCountry());
        clientFormFieldValidator.setIsCountryValid(true);
        cityField.setText(address.getCity());
        clientFormFieldValidator.setIsCityValid(true);
        streetField.setText(address.getStreet());
        clientFormFieldValidator.setIsStreetValid(true);
        zipCodeField.setText(address.getZipcode());
        clientFormFieldValidator.setIsZipCode(true);
    }
}
