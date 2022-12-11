package fr.greta.domes.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button submitButton;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private Label errorMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submitButton.setDisable(true);
        errorMessage.setVisible(false);
        submitButton.disableProperty()
                .bind(
                        Bindings.when(
                                        email.textProperty()
                                                .isNotEmpty()
                                                .and(password.textProperty().isNotEmpty()))
                                .then(false).otherwise(true));
    }

    @FXML
    protected void submit() throws InterruptedException {
        /**
         * For wrong credentials after server checking
         * email.setBorder(Border.stroke(Paint.valueOf("FF0000")));
         * password.setBorder(Border.stroke(Paint.valueOf("FF0000")));
         * **/
        showErrorMessage(3500);
    }

    private void showErrorMessage(int timer) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                errorMessage.setVisible(true);
                try {
                    Thread.sleep(timer);
                    errorMessage.setVisible(false);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}