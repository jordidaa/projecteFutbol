package com.example.projectefutbol;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class LogsController {
    private Stage stage;
    private Scene scene;
    public void tableEditable(ActionEvent actionEvent) {
    }

    public void inici(ActionEvent actionEvent) throws IOException {
        Alert missage = new Alert(Alert.AlertType.INFORMATION);
        missage.setTitle("Has retornat a la pantalla principal");
        missage.setContentText("Ok!");
        missage.setHeaderText("Resultat");
        missage.show();
        Parent root = FXMLLoader.load(getClass().getResource("formJugadors.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
