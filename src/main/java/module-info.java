module com.example.projectefutbol {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projectefutbol to javafx.fxml;
    exports com.example.projectefutbol;
}