module fr.greta.domes {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires de.jensd.fx.glyphs.fontawesome;

    opens fr.greta.domes to javafx.fxml;
    opens fr.greta.domes.controller to javafx.fxml;
    opens fr.greta.domes.model to javafx.base;
    exports fr.greta.domes;
    exports fr.greta.domes.controller;
    exports fr.greta.domes.controller.animal;
    opens fr.greta.domes.controller.animal to javafx.fxml;
    opens fr.greta.domes.model.animal to javafx.base;
}