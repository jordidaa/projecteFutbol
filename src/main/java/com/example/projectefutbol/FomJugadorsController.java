package com.example.projectefutbol;

import com.example.projectefutbol.model.FieldPosition;
import com.example.projectefutbol.model.Player;
import com.example.projectefutbol.model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.ArrayList;

public class FomJugadorsController implements Initializable {
    @FXML
    private Label welcomeText;
    public TextField nameTF;
    public TextField surnameTF;
    public TextField ageTF;
    public TextField cercaTF;
    public DatePicker contractEndDP;
    public ComboBox teamCB;
    public ComboBox positionCB;
    private Stage stage;
    private Scene scene;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void saveBTN(ActionEvent actionEvent) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol_projecte", "root", "");
        Player player = new Player();
        player.setName(nameTF.getText());
        player.setSurname(surnameTF.getText());
        player.setAge(Integer.parseInt(ageTF.getText()));
        player.setEndOfContract(LocalDate.parse(contractEndDP.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO players set name = '" + player.getName() + "', surname = '" + player.getSurname() + "'" +
                ", age = '" + player.getAge() + "' , contract = '" + player.getEndOfContract() + "'");
    }

    public void updateBTN(ActionEvent actionEvent) {
    }

    public void delateBTN(ActionEvent actionEvent) {
    }

    public void tableEditableBTN(ActionEvent actionEvent) {
    }

    public void logsBTN(ActionEvent actionEvent)throws IOException {
        Alert missage = new Alert(Alert.AlertType.ERROR);
        missage.setTitle("El registre no s'ha pogut guardar");
        missage.setContentText("Fail!");
        missage.setHeaderText("Resultat");
        missage.show();
        Parent root = FXMLLoader.load(getClass().getResource("logs.fxml"));
        stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void butonBTN(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public ArrayList<Team> teams() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol_projecte", "root", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM teams");
        ArrayList<Team> teams = new ArrayList<>();
        while (rs.next()) {
            Team team = new Team(rs.getString("team"));
            teams.add(team);
        }
        return teams;
    }
}