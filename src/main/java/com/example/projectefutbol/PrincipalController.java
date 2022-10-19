package com.example.projectefutbol;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PrincipalController {
    @FXML
    private Label welcomeText;
    public TextField nameTF;
    public TextField surnameTF;
    public TextField ageTF;
    public TextField cercaTF;
    public DatePicker contractEndDP;
    public ComboBox teamCB;
    public ComboBox positionCB;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void saveBTN(ActionEvent actionEvent) {
    }

    public void updateBTN(ActionEvent actionEvent) {
    }

    public void delateBTN(ActionEvent actionEvent) {
    }

    public void tableEditableBTN(ActionEvent actionEvent) {
    }

    public void logsBTN(ActionEvent actionEvent) {
    }

    public void butonBTN(ActionEvent actionEvent) {
    }
}