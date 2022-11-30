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
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class FomJugadorsController implements Initializable {
    public TableColumn<Player,Integer> tblId;
    public TableColumn<Player,String> tblNom;
    public TableColumn<Player,String> tblCognom;
    public TableColumn<Player,Integer> tblEdat;
    public TableColumn<Player,String> tblPosition;
    public TableColumn<Player,LocalDate> tblEndContract;
    public TableColumn<Player,String> tblTeam;
    public TableView<Player> table;
    public TextField idTF;
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
    int index = -1;

    Player playerP = new Player();
    @FXML
    Button btnSave,btnDelete,btnUpdate;

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
        tblId.setCellValueFactory(new PropertyValueFactory<>("playerID"));
        tblNom.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCognom.setCellValueFactory(new PropertyValueFactory<>("surname"));
        tblEdat.setCellValueFactory(new PropertyValueFactory<>("age"));
        tblPosition.setCellValueFactory(new PropertyValueFactory<>("posicionStr"));
        tblEndContract.setCellValueFactory(new PropertyValueFactory<>("endOfContract"));
        tblTeam.setCellValueFactory(new PropertyValueFactory<>("teamStr"));
        table.setItems(player.getAllPlayers());

    }

    public void saveBTN(ActionEvent actionEvent) {
        Player player = new Player();
        player.setName(nameTF.getText());
        player.setSurname(surnameTF.getText());
        player.setAge(Integer.parseInt(ageTF.getText()));
        player.setEndOfContract(LocalDate.parse(contractEndDP.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        Team team = new Team();
        player.setTeam(team.getTeamID(teamCB.getValue()));
        FieldPosition fieldPosition = new FieldPosition();
        player.setFieldPosition(fieldPosition.getIdPosition(positionCB.getValue()));
        player.savePlayer();
        refreshTable();
        clear();
    }

    public void updateBTN(ActionEvent actionEvent) {
        Player player = new Player();
        player.setPlayerID(Integer.parseInt(idTF.getText()));
        player.setName(nameTF.getText());
        player.setSurname(surnameTF.getText());
        player.setAge(Integer.parseInt(ageTF.getText()));
        player.setEndOfContract(LocalDate.parse(contractEndDP.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        Team team = new Team();
        player.setTeam(team.getTeamID(teamCB.getValue()));
        FieldPosition fieldPosition = new FieldPosition();
        player.setFieldPosition(fieldPosition.getIdPosition(positionCB.getValue()));
        player.updatePlayer();
        refreshTable();
        clear();
    }

    public void delateBTN(ActionEvent actionEvent) {
        Player player = new Player();
        player.setPlayerID(Integer.parseInt(idTF.getText()));
        player.deletePlayer();
        refreshTable();
        clear();
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
        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void schareBTN(ActionEvent actionEvent) {
        Player player = new Player();
        table.setItems(player.getAllPlayersBus(cercaTF.getText()));
    }


    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {
        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        idTF.setDisable(true);
        idTF.setText(Integer.toString(tblId.getCellData(index)));
        nameTF.setText(tblNom.getCellData(index));
        surnameTF.setText(tblCognom.getCellData(index));
        ageTF.setText(tblEdat.getCellData(index).toString());
        contractEndDP.setValue(tblEndContract.getCellData(index));
        teamCB.setValue(tblTeam.getCellData(index));
        positionCB.setValue(tblPosition.getCellData(index));
        btnSave.setDisable(true);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
    }

    public void clearBTN(ActionEvent actionEvent) {
        clear();
    }
}