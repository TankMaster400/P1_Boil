module com.example.p1_boil {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.p1_boil to javafx.fxml;
    exports com.example.p1_boil;
}