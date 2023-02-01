package fr.greta.domes.controller;

import fr.greta.domes.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable{
    public BorderPane dashboard;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NavigationController.getCurrentNavigation().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case TO_ANIMALS -> dashboard.setCenter(Model.getInstance().getViewFactory().getAnimalView());
                case TO_ANIMAL_DETAIL -> dashboard.setCenter(Model.getInstance().getViewFactory().getAnimalDetailView());
                case TO_ANIMALS_FORM -> dashboard.setCenter(Model.getInstance().getViewFactory().getAnimalFormView());
                case TO_CLIENTS -> dashboard.setCenter(Model.getInstance().getViewFactory().getClientView());
                case TO_CLIENT_DETAIL -> dashboard.setCenter(Model.getInstance().getViewFactory().getClientDetailView());
                case TO_ORDERS -> dashboard.setCenter(Model.getInstance().getViewFactory().getOrderView());
                case TO_CATEGORIES -> dashboard.setCenter(Model.getInstance().getViewFactory().getCategoriesView());
                case TO_PARTNERS -> dashboard.setCenter(Model.getInstance().getViewFactory().getPartnersView());
                case TO_PROFILE -> dashboard.setCenter(Model.getInstance().getViewFactory().getProfileView());
            }
        });
    }
}
