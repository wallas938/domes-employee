package fr.greta.domes.model.animal;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class AnimalFormFieldValidator {

    private final BooleanProperty isAgeValid = new SimpleBooleanProperty();
    private final BooleanProperty isPriceValid = new SimpleBooleanProperty();
    private final BooleanProperty isCategoryValid = new SimpleBooleanProperty();
    private final BooleanProperty isSpecieValid = new SimpleBooleanProperty();
    private final BooleanProperty isDescriptionValid = new SimpleBooleanProperty();

    public AnimalFormFieldValidator() {
        setDescription(true);
        setCategoryValid(true);
        setSpecieValid(true);
        setPriceValid(true);
        setAgeValid(true);
    }

    public BooleanBinding isAnimalFormIsValid() {

        return getIsAgeValid()
                .and(getIsPriceValid())
                .and(getIsCategoryValid())
                .and(getIsSpecieValid())
                .and(getIsDescriptionValid());
    }

    public BooleanProperty getIsDescriptionValid() {
        return isDescriptionValid;
    }

    public void setDescription(boolean status) {
        this.isDescriptionValid.set(status);
    }

    public BooleanProperty getIsAgeValid() {
        return isAgeValid;
    }

    public void setAgeValid(boolean status) {
        isAgeValid.set(status);
    }

    public BooleanProperty getIsPriceValid() {
        return isPriceValid;
    }

    public void setPriceValid(boolean status) {
        isPriceValid.set(status);
    }

    public BooleanProperty getIsCategoryValid() {
        return isCategoryValid;
    }

    public void setCategoryValid(boolean status) {
        isCategoryValid.set(status);
    }

    public BooleanProperty getIsSpecieValid() {
        return isSpecieValid;
    }

    public void setSpecieValid(boolean status) {
        isSpecieValid.set(status);
    }

    public void resetData() {
        setDescription(false);
        setCategoryValid(false);
        setSpecieValid(false);
        setPriceValid(false);
        setAgeValid(false);
    }

    @Override
    public String toString() {
        return "AnimalFormFieldValidator{" +
                "isAgeValid=" + isAgeValid.getValue() +
                ", isPriceValid=" + isPriceValid.getValue() +
                ", isCategoryValid=" + isCategoryValid.getValue() +
                ", isSpecieValid=" + isSpecieValid.getValue() +
                ", description=" + isDescriptionValid.getValue() +
                '}';
    }
}
