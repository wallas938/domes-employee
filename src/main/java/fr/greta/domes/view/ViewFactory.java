package fr.greta.domes.view;

import fr.greta.domes.Main;
import fr.greta.domes.controller.DashboardController;
import fr.greta.domes.controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class ViewFactory {
    private AnchorPane productView;
    public ViewFactory() {
    }
    /*
    * Views
    * */
    public AnchorPane getProductView() {
        if(productView == null) {
            try {
                productView = new FXMLLoader(getClass().getResource("views/dashboard/productView.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return productView;
    }

    /*
     * Windows
     * */
    public void showDashboardWindow() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/dashboard/dashboard.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
            scene.getStylesheets().add(Main.class.getResource("css/dashboard.css").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }

    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/login.fxml"));
        LoginController loginController = new LoginController();
        loader.setController(loginController);
        createStage(loader);
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
        stage.show();
    }
}
