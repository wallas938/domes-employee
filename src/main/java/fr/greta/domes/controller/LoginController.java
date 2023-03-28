package fr.greta.domes.controller;
import fr.greta.domes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML private Button submitButton;
    @FXML private TextField email;
    @FXML private TextField password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submitButton.setOnAction(event -> {
            submit();
        });
    }

    public void login(ActionEvent event) {
        Stage stage = (Stage) submitButton.getScene().getWindow();
        Model.getInstance().getViewFactory().closeCurrentWindow(stage);
        Model.getInstance().getViewFactory().showDashboardWindow();
    }

    private void submit() {
        if(!email.getText().isBlank() && !password.getText().isBlank())
        System.out.println(email.getText());
        System.out.println(password.getText());
    }
}