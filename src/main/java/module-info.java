module org.clinic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.dotenv;


    opens org.clinic.view to javafx.fxml;
    opens org.clinic.model to javafx.base;
    exports org.clinic.app;
}