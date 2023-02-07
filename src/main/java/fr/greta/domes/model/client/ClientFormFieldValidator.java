package fr.greta.domes.model.client;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class ClientFormFieldValidator {
    private final BooleanProperty isLastnameValid = new SimpleBooleanProperty();
    private final BooleanProperty isFirstnameValid = new SimpleBooleanProperty();
    private final BooleanProperty isEmailValid = new SimpleBooleanProperty();
    private final BooleanProperty isPhoneNumberValid = new SimpleBooleanProperty();
    private final BooleanProperty isCountryValid = new SimpleBooleanProperty();
    private final BooleanProperty isCityValid = new SimpleBooleanProperty();
    private final BooleanProperty isStreetValid = new SimpleBooleanProperty();
    private final BooleanProperty isZipCode = new SimpleBooleanProperty();

    public ClientFormFieldValidator() {
    }

    public BooleanBinding isClientFormIsValid() {

        return getIsLastnameValid()
                .and(getIsFirstnameValid())
                .and(getIsPhoneNumberValid())
                .and(getIsEmailValid())
                .and(getIsCountryValid())
                .and(getIsCityValid())
                .and(getIsStreetValid())
                .and(getIsZipCode());
    }

    public BooleanProperty getIsLastnameValid() {
        return isLastnameValid;
    }

    public void setIsLastnameValid(boolean status) {
        this.isLastnameValid.set(status);
    }

    public BooleanProperty getIsFirstnameValid() {
        return isFirstnameValid;
    }

    public void setIsFirstnameValid(boolean status) {
        this.isFirstnameValid.set(status);
    }

    public BooleanProperty getIsEmailValid() {
        return isEmailValid;
    }

    public void setIsEmailValid(boolean status) {
        this.isEmailValid.set(status);
    }

    public BooleanProperty getIsPhoneNumberValid() {
        return isPhoneNumberValid;
    }

    public void setIsPhoneNumberValid(boolean status) {
        this.isPhoneNumberValid.set(status);
    }

    public BooleanProperty getIsCountryValid() {
        return isCountryValid;
    }

    public void setIsCountryValid(boolean status) {
        this.isCountryValid.set(status);
    }

    public BooleanProperty getIsCityValid() {
        return isCityValid;
    }

    public void setIsCityValid(boolean status) {
        this.isCityValid.set(status);
    }

    public BooleanProperty getIsStreetValid() {
        return isStreetValid;
    }

    public void setIsStreetValid(boolean status) {
        this.isStreetValid.set(status);
    }

    public BooleanProperty getIsZipCode() {
        return isZipCode;
    }

    public void setIsZipCode(boolean status) {
        this.isZipCode.set(status);
    }

    public void resetData() {
        setIsLastnameValid(false);
        setIsFirstnameValid(false);
        setIsPhoneNumberValid(false);
        setIsEmailValid(false);
        setIsCountryValid(false);
        setIsCityValid(false);
        setIsStreetValid(false);
        setIsZipCode(false);
    }
}
