package fr.greta.domes.utils;

import com.dlsc.formsfx.model.validators.RegexValidator;
import com.dlsc.formsfx.model.validators.ValidationResult;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleStringProperty;

public class Utils {

    static RegexValidator regexValidator = RegexValidator.forPattern("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", "");

    static public boolean isEmailValid(String email) {
        StringExpression value = new SimpleStringProperty(email);
        ValidationResult validate = regexValidator.validate(value.getValue());

        return validate.getResult();

    }

    public static int intParser(String value) {
        int i = 1;
        try {
            i = Integer.parseInt(value);
            return i;
        } catch (NumberFormatException e) {
            i = 1;
            return i;
        }
    }

    public static double doubleParser(String value) {
        int d = 1;
        try {
            d = Integer.parseInt(value);
            return d;
        } catch (NumberFormatException e) {
            d = 1;
            return d;
        }
    }
}
