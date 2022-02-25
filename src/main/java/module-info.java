module com.example.irrigationapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.graphics;
    requires java.sql;

    opens view to javafx.fxml;
    exports view;
    exports controller;
    opens controller to javafx.fxml;
    exports model;
    opens model to javafx.fxml;

}