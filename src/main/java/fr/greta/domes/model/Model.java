package fr.greta.domes.model;

import fr.greta.domes.model.auth.AuthenticationToken;
import fr.greta.domes.view.ViewFactory;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Model {

    private static BooleanProperty refreshTokenExpired = new SimpleBooleanProperty(false);
    private static Model model;

    private static String subject;

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

    public static String getSubject() {
        return subject;
    }

    public static void setSubject(String subject) {
        Model.subject = subject;
    }

    public static AuthenticationToken getAuthenticationToken() {
        return authenticationToken;
    }

    public static void setAuthenticationToken(AuthenticationToken authenticationToken) {
        Model.authenticationToken = authenticationToken;
    }

    public static BooleanProperty isRefreshTokenExpired() {
        return refreshTokenExpired;
    }

    public static void setRefreshTokenExpired(boolean refreshTokenExpired) {
        Model.refreshTokenExpired.set(refreshTokenExpired);
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }
}
