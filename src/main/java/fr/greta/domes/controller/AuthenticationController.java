package fr.greta.domes.controller;

import fr.greta.domes.model.Model;
import fr.greta.domes.model.auth.AuthenticationToken;
import fr.greta.domes.service.AuthService;
import fr.greta.domes.service.AuthServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthenticationController implements Initializable {
    @FXML
    private Label errorMessage;
    @FXML
    private Button submitButton;
    @FXML
    private TextField email;
    @FXML
    private TextField password;

    private final AuthService authService = new AuthServiceImpl();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.submitButton.setOnAction(event -> {
            try {
                login();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void login() throws IOException {
        if (email.getText().isBlank() && password.getText().isBlank()) {
            return;
        }
        authService.login(email.getText(), password.getText()).ifPresentOrElse(authenticationToken -> {
            Model.setAuthenticationToken(authenticationToken);
            Stage stage = (Stage) submitButton.getScene().getWindow();
            Model.getInstance().getViewFactory().closeCurrentWindow(stage);
            Model.getInstance().getViewFactory().showDashboardWindow();
        }, () -> {
            System.out.println("Error");
        });
    }
}