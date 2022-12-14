module fr.greta.domes {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires de.jensd.fx.glyphs.fontawesome;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;

    opens fr.greta.domes to javafx.fxml;
    opens fr.greta.domes.controller to javafx.fxml;
    opens fr.greta.domes.controller.animal to javafx.fxml;

    opens fr.greta.domes.model to javafx.base;
    opens fr.greta.domes.model.specie to javafx.base;
    opens fr.greta.domes.model.animal to javafx.base;
    opens fr.greta.domes.model.category to javafx.base;

    exports fr.greta.domes.model.category to com.fasterxml.jackson.databind;
    exports fr.greta.domes.model.specie to com.fasterxml.jackson.databind;
    exports fr.greta.domes.model.animal to com.fasterxml.jackson.databind;
    exports fr.greta.domes.model to com.fasterxml.jackson.databind;

    exports fr.greta.domes;



}