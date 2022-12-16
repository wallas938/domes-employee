package fr.greta.domes.controller;
import fr.greta.domes.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private HomeController homeController;
    @FXML private Button submitButton;
    @FXML private TextField email;
    @FXML private TextField password;
    @FXML protected void login(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Main.class.getResource("views/home.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        try {
            scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("css/home.css")).toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }
}