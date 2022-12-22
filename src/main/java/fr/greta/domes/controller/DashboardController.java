package fr.greta.domes.controller;

import fr.greta.domes.controller.service.AnimalService;
import fr.greta.domes.model.enums.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable{
    public BorderPane dashboard;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NavigationController.getCurrentNavigation().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case "TO_PRODUCTS" -> dashboard.setCenter(Model.getInstance().getViewFactory().getProductView());
                case "TO_CLIENTS" -> dashboard.setCenter(Model.getInstance().getViewFactory().getClientView());
                case "TO_ORDERS" -> dashboard.setCenter(Model.getInstance().getViewFactory().getOrderView());
                case "TO_PROFILE" -> dashboard.setCenter(Model.getInstance().getViewFactory().getProfileView());
            }
        });
    }
}
