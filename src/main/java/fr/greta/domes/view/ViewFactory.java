package fr.greta.domes.view;

import fr.greta.domes.Main;
import fr.greta.domes.controller.AuthenticationController;
import fr.greta.domes.model.Model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class ViewFactory {
    private AnchorPane animalView;
    private AnchorPane animalDetailView;
    private AnchorPane animalFormView;
    private AnchorPane clientView;
    private AnchorPane clientDetailView;
    private AnchorPane clientFormView;
    private AnchorPane orderView;
    private AnchorPane profileView;
    private AnchorPane categoriesView;
    private AnchorPane partnersView;

    public ViewFactory() {
    }

    /*
     * Views
     * */

    //Animal Views
    public AnchorPane getAnimalView() {
        if (animalView == null) {
            try {
                animalView = new FXMLLoader(Main.class.getResource("views/dashboard/animal/animalView.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return animalView;
    }
    public AnchorPane getAnimalDetailView() {
        if (animalDetailView == null) {
            try {
                animalDetailView = new FXMLLoader(Main.class.getResource("views/dashboard/animal/animalDetailView.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return animalDetailView;
    }
    public AnchorPane getAnimalFormView() {
        if (animalFormView == null) {
            try {
                animalFormView = new FXMLLoader(Main.class.getResource("views/dashboard/animal/animalFormView.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return animalFormView;
    }

    //Client Views
    public AnchorPane getClientView() {
        if (clientView == null) {
            try {
                clientView = new FXMLLoader(Main.class.getResource("views/dashboard/client/clientView.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientView;
    }
    public AnchorPane getClientDetailView() {
        if (clientDetailView == null) {
            try {
                clientDetailView = new FXMLLoader(Main.class.getResource("views/dashboard/client/clientDetailView.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientDetailView;
    }
    public AnchorPane getClientFormView() {
        if (clientFormView == null) {
            try {
                clientFormView = new FXMLLoader(Main.class.getResource("views/dashboard/client/clientFormView.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientFormView;
    }

    //Order Views
    public AnchorPane getOrderView() {
        if (orderView == null) {
            try {
                orderView = new FXMLLoader(Main.class.getResource("views/dashboard/order/orderView.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderView;
    }

    //Profile Views
    public AnchorPane getProfileView() {
        if (profileView == null) {
            try {
                profileView = new FXMLLoader(Main.class.getResource("views/dashboard/profile/profileView.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return profileView;
    }

    //Categories Views
    public AnchorPane getCategoriesView() {
        if (categoriesView == null) {
            try {
                categoriesView = new FXMLLoader(Main.class.getResource("views/dashboard/category/categoryView.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return categoriesView;
    }

    //Partners Views
    public AnchorPane getPartnersView() {
        if (partnersView == null) {
            try {
                partnersView = new FXMLLoader(Main.class.getResource("views/dashboard/partner/partnerView.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return partnersView;
    }

    /*
     * Windows
     * */
    public void showDashboardWindow() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/dashboard/dashboard.fxml"));
        createStage(loader, "css/dashboard.css");
    }

    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/login.fxml"));
        createStage(loader, "css/login.css");
    }

    /*
     * Methods
     * */
    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResource("images/logo.png")).toString()));
        stage.show();
    }

    private void createStage(FXMLLoader loader, String stylesheetPath) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        assert scene != null;
        scene.getStylesheets().add((Objects.requireNonNull(Main.class.getResource(stylesheetPath))).toString());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResource("images/logo.png")).toString()));
        stage.centerOnScreen();
        stage.show();
    }

    public void closeCurrentWindow(Stage stage) {
        stage.close();
    }
}
