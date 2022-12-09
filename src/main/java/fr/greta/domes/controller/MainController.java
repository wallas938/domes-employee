package fr.greta.domes.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController {
    @FXML private Button submitButton;

    @FXML private TextField email;

    @FXML private TextField password;

    @FXML protected void onHelloButtonClick() {
        System.out.println("Welcome to JavaFX Application!");
    }
}