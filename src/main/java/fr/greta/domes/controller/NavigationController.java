package fr.greta.domes.controller;

import fr.greta.domes.model.Navigation;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class NavigationController implements Initializable {

    private static final ObjectProperty<Navigation> currentNavigation = new SimpleObjectProperty<>();
    @FXML
    private Button toProducts;
    @FXML
    private Button toClients;
    @FXML
    private Button toOrders;
    @FXML
    private Button toProfile;
    @FXML
    private Button logout;
    @FXML
    private Button toPartners;
    @FXML
    private Button toCategories;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initListeners();
        setCurrentNavigation(Navigation.TO_ANIMALS);
    }

    private void initListeners() {
        currentNavigation.addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case TO_ANIMALS -> {
                    if (!toProducts.getStyleClass().contains("nav-item-products"))
                        toProducts.getStyleClass().add("nav-item-products");
                    toOrders.getStyleClass().remove("nav-item-orders");
                    toClients.getStyleClass().remove("nav-item-clients");
                    toProfile.getStyleClass().remove("nav-item-profile");
                    toPartners.getStyleClass().remove("nav-item-partners");
                    toCategories.getStyleClass().remove("nav-item-categories");
                }
                case TO_CLIENTS -> {
                    if (!toClients.getStyleClass().contains("nav-item-clients"))
                        toClients.getStyleClass().add("nav-item-clients");
                    toOrders.getStyleClass().remove("nav-item-orders");
                    toProducts.getStyleClass().remove("nav-item-products");
                    toProfile.getStyleClass().remove("nav-item-profile");
                    toPartners.getStyleClass().remove("nav-item-partners");
                    toCategories.getStyleClass().remove("nav-item-categories");
                }
                case TO_ORDERS -> {
                    if (!toOrders.getStyleClass().contains("nav-item-orders"))
                        toOrders.getStyleClass().add("nav-item-orders");
                    toProducts.getStyleClass().remove("nav-item-products");
                    toClients.getStyleClass().remove("nav-item-clients");
                    toProfile.getStyleClass().remove("nav-item-profile");
                    toPartners.getStyleClass().remove("nav-item-partners");
                    toCategories.getStyleClass().remove("nav-item-categories");
                }
                case TO_PROFILE -> {
                    if (!toProfile.getStyleClass().contains("nav-item-profile"))
                        toProfile.getStyleClass().add("nav-item-profile");
                    toOrders.getStyleClass().remove("nav-item-orders");
                    toClients.getStyleClass().remove("nav-item-clients");
                    toProducts.getStyleClass().remove("nav-item-products");
                    toPartners.getStyleClass().remove("nav-item-partners");
                    toCategories.getStyleClass().remove("nav-item-categories");
                }
                case TO_CATEGORIES -> {
                    if (!toCategories.getStyleClass().contains("nav-item-categories"))
                        toCategories.getStyleClass().add("nav-item-categories");
                    toProfile.getStyleClass().remove("nav-item-profile");
                    toOrders.getStyleClass().remove("nav-item-orders");
                    toClients.getStyleClass().remove("nav-item-clients");
                    toProducts.getStyleClass().remove("nav-item-products");
                    toPartners.getStyleClass().remove("nav-item-partners");
                }
                case TO_PARTNERS -> {
                    if (!toPartners.getStyleClass().contains("nav-item-partners"))
                        toPartners.getStyleClass().add("nav-item-partners");
                    toProfile.getStyleClass().remove("nav-item-profile");
                    toOrders.getStyleClass().remove("nav-item-orders");
                    toClients.getStyleClass().remove("nav-item-clients");
                    toProducts.getStyleClass().remove("nav-item-products");
                    toCategories.getStyleClass().remove("nav-item-categories");
                }
            }
        });
        toProducts.setOnAction(event -> {
            setCurrentNavigation(Navigation.TO_ANIMALS);
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
        toCategories.setOnAction(event -> {
            setCurrentNavigation(Navigation.TO_CATEGORIES);
//            updateView(Navigation.TO_PROFILE);
        });
        toPartners.setOnAction(event -> {
            setCurrentNavigation(Navigation.TO_PARTNERS);
//            updateView(Navigation.TO_PROFILE);
        });
    }

    public static ObjectProperty<Navigation> getCurrentNavigation() {
        return currentNavigation;
    }

    public static void setCurrentNavigation(Navigation navigation) {
        currentNavigation.setValue(navigation);
    }
}
