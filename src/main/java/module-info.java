module com.example.projectefutbol {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.projectefutbol to javafx.fxml;
    exports com.example.projectefutbol;
    exports com.example.projectefutbol.model;
    opens com.example.projectefutbol.model to javafx.fxml;
}