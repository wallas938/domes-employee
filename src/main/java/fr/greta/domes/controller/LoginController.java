package fr.greta.domes.controller;
import fr.greta.domes.Main;
import fr.greta.domes.model.category.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private DashboardController homeController;
    @FXML private Button submitButton;
    @FXML private TextField email;
    @FXML private TextField password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void login(ActionEvent event) {
        Model.getInstance().getViewFactory().showDashboardWindow();
//        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        try {
//            scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("css/dashboard.css")).toString());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        stage.setTitle("Dashboard");
//        stage.setScene(scene);
//        stage.show();
    }
}