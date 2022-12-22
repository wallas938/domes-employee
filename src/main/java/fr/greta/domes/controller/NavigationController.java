package fr.greta.domes.controller;

import fr.greta.domes.model.Navigation;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class NavigationController implements Initializable {

    private static final ObjectProperty<Navigation> currentNavigation = new SimpleObjectProperty<>();
    public Button toProducts;
    public Button toClients;
    public Button toOrders;
    public Button toProfile;
    public Button logout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initListeners();
        setCurrentNavigation(Navigation.TO_PRODUCTS);
    }

    public void setCurrentNavigation(Navigation currentNavigation) {
        this.currentNavigation.setValue(currentNavigation);
    }

private void initListeners () {
    currentNavigation.addListener((observable, oldValue, newValue) -> {
        switch (newValue) {
            case TO_PRODUCTS:
                if (!toProducts.getStyleClass().contains("nav-item-products"))
                    toProducts.getStyleClass().add("nav-item-products");
                toOrders.getStyleClass().remove("nav-item-orders");
                toClients.getStyleClass().remove("nav-item-clients");
                toProfile.getStyleClass().remove("nav-item-profile");
                break;
            case TO_CLIENTS:
                if (!toClients.getStyleClass().contains("nav-item-clients"))
                    toClients.getStyleClass().add("nav-item-clients");

                toOrders.getStyleClass().remove("nav-item-orders");
                toProducts.getStyleClass().remove("nav-item-products");
                toProfile.getStyleClass().remove("nav-item-profile");
                break;
            case TO_ORDERS:
                if (!toOrders.getStyleClass().contains("nav-item-orders"))
                    toOrders.getStyleClass().add("nav-item-orders");

                toProducts.getStyleClass().remove("nav-item-products");
                toClients.getStyleClass().remove("nav-item-clients");
                toProfile.getStyleClass().remove("nav-item-profile");
                break;
            case TO_PROFILE:
                if (!toProfile.getStyleClass().contains("nav-item-profile"))
                    toProfile.getStyleClass().add("nav-item-profile");

                toOrders.getStyleClass().remove("nav-item-orders");
                toClients.getStyleClass().remove("nav-item-clients");
                toProducts.getStyleClass().remove("nav-item-products");
                break;
            default:
                toProducts.getStyleClass().add("nav-item-products");
        }
    });
    toProducts.setOnAction(event -> {
        setCurrentNavigation(Navigation.TO_PRODUCTS);
//            updateView(Navigation.TO_PRODUCTS);
    });
    toClients.setOnAction(event -> {
        setCurrentNavigation(Navigation.TO_CLIENTS);
//            updateView(Navigation.TO_CLIENTS);
    });
    toOrders.setOnAction(event -> {
        setCurrentNavigation(Navigation.TO_ORDERS);
//            updateView(Navigation.TO_ORDERS);
    });
    toProfile.setOnAction(event -> {
        setCurrentNavigation(Navigation.TO_PROFILE);
//            updateView(Navigation.TO_PROFILE);
    });
}
    public static ObjectProperty<Navigation> getCurrentNavigation() {
        return currentNavigation;
    }
}
