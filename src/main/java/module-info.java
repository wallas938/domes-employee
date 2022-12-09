module fr.greta.domes {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens fr.greta.domes to javafx.fxml;
    exports fr.greta.domes;
    exports fr.greta.domes.controller;
    opens fr.greta.domes.controller to javafx.fxml;
}