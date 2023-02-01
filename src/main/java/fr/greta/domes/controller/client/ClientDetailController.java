package fr.greta.domes.controller.client;

import fr.greta.domes.controller.NavigationController;
import fr.greta.domes.model.Navigation;
import fr.greta.domes.model.animal.Animal;
import fr.greta.domes.model.client.Client;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientDetailController implements Initializable {

    private static Client currentClient;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFields(currentClient);
        NavigationController.getCurrentNavigation().addListener((observable, oldValue, newValue) -> {
//            if (newValue.equals(Navigation.TO_CLIENT_DETAIL))
                setFields(currentClient);
        });
    }

    public static void setCurrentClient(Client client) {
        currentClient = client;
    }

    public void setFields(Client currentClient) {
//        setAnimalRef(currentAnimal.getId().toString());
//        setAge(currentAnimal.getAge());
//        setPrice(currentAnimal.getPrice());
//        setDescription(currentAnimal.getDescription());
//        setMainPicture(currentAnimal.getMainPicture());
//        setSecondImage(currentAnimal.getSecondPicture());
//        setThirdImage(currentAnimal.getThirdPicture());
//        setFourthImage(currentAnimal.getFourthPicture());
//        setCategory(currentAnimal.getCategory().getName());
//        setSpecie(currentAnimal.getSpecie().getName());
    }

    private void setClientId(String id) {

    }
}
