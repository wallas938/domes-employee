package fr.greta.domes.view;

import fr.greta.domes.Main;
import fr.greta.domes.controller.LoginController;
import fr.greta.domes.model.Navigation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ViewFactory {
    private AnchorPane productView;
    private AnchorPane clientView;
    private AnchorPane orderView;
    private AnchorPane profileView;

    public ViewFactory() {
    }

    /*
     * Views
     * */
    public AnchorPane getProductView() {
        if (productView == null) {
            try {
                productView = new FXMLLoader(Main.class.getResource("views/dashboard/animal/animalView.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return productView;
    }

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

    /*
     * Windows
     * */
    public void showDashboardWindow() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/dashboard/dashboard.fxml"));
        createStage(loader, "css/dashboard.css");
    }

    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/login.fxml"));
        LoginController loginController = new LoginController();
        loader.setController(loginController);
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
        stage.getIcons().add(new Image(Main.class.getResource("images/logo.png").toString()));
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
        if (!stylesheetPath.isEmpty())
            scene.getStylesheets().add(Main.class.getResource(stylesheetPath).toString());

        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Main.class.getResource("images/logo.png").toString()));
        stage.centerOnScreen();
        stage.show();
    }

    public void closeCurrentWindow(Stage stage) {
        stage.close();
    }
}
