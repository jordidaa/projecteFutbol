package com.example.projectefutbol;

import com.example.projectefutbol.model.FieldPosition;
import com.example.projectefutbol.model.Player;
import com.example.projectefutbol.model.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class FomJugadorsController implements Initializable {
    public TableColumn<Player,String> tblNom;
    public TableColumn<Player,String> tblCognom;
    public TableColumn<Player,String> tblEdat;
    public TableColumn<Player,String> tblPosition;
    public TableColumn<Player,String> tblEndContract;
    public TableColumn<Player,String> tblTeam;
    public TableView<Player> table;
    @FXML
    private Label welcomeText;
    public TextField nameTF;
    public TextField surnameTF;
    public TextField ageTF;
    public TextField cercaTF;
    public DatePicker contractEndDP;
    public ComboBox<String> teamCB;
    public ComboBox<String> positionCB;
    private Stage stage;
    private Scene scene;

    @FXML
    protected void onHelloButtonClick() {
        ObservableList<FieldPosition> positions = FXCollections.observableArrayList();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Team team = new Team();
        ObservableList<String> teams = team.getTeamsName();
        this.teamCB.setItems(teams);
        FieldPosition fieldPosition = new FieldPosition();
        ObservableList<String> positions = fieldPosition.getPositionsName();
        this.positionCB.setItems(positions);
        Player player = new Player();
        tblNom.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCognom.setCellValueFactory(new PropertyValueFactory<>("surname"));
        tblEdat.setCellValueFactory(new PropertyValueFactory<>("age"));
        tblPosition.setCellValueFactory(new PropertyValueFactory<>("posicionStr"));
        tblEndContract.setCellValueFactory(new PropertyValueFactory<>("endOfContract"));
        tblTeam.setCellValueFactory(new PropertyValueFactory<>("teamStr"));
        table.setItems(player.getAllPlayers());
        table.getColumns().addAll(tblNom, tblCognom, tblEdat, tblPosition, tblEndContract, tblTeam);

    }

    public void saveBTN(ActionEvent actionEvent) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol_projecte", "root", "");
        Player player = new Player();
        player.setName(nameTF.getText());
        player.setSurname(surnameTF.getText());
        player.setAge(Integer.parseInt(ageTF.getText()));
        player.setEndOfContract(LocalDate.parse(contractEndDP.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        Team team = new Team();
        player.setTeam(team.getTeamID(teamCB.getValue()));
        FieldPosition fieldPosition = new FieldPosition();
        player.setFieldPosition(fieldPosition.getIdPosition(positionCB.getValue()));
        player.savePlayer(player);
        refreshTable();
        clear();
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

    public void refreshTable() {
        Player player = new Player();
        table.setItems(player.getAllPlayers());
    }
    public void clear() {
        nameTF.clear();
        surnameTF.clear();
        ageTF.clear();
        contractEndDP.setValue(null);
        teamCB.setValue(null);
        positionCB.setValue(null);
    }


}