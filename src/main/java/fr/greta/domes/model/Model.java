package fr.greta.domes.model;

import fr.greta.domes.model.auth.AuthenticationToken;
import fr.greta.domes.view.ViewFactory;

public class Model {
    private static Model model;

    private static AuthenticationToken authenticationToken;
    private final ViewFactory viewFactory;

    private Model() {
        this.viewFactory = new ViewFactory();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public static AuthenticationToken getAuthenticationToken() {
        return authenticationToken;
    }

    public static void setAuthenticationToken(AuthenticationToken authenticationToken) {
        Model.authenticationToken = authenticationToken;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }
}
