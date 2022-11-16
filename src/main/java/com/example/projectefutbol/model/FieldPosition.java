package com.example.projectefutbol.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class FieldPosition {
    private int positionId;
    private String position;
    private String side;

    public FieldPosition(int positionId, String position, String side) {
        this.positionId = positionId;
        this.position = position;
        this.side = side;
    }
    public FieldPosition() {
    }
    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public ObservableList<FieldPosition> getPositions(){
        ObservableList<FieldPosition> positions = FXCollections.observableArrayList();
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol_projecte", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM positions");

            while (rs.next()) {
                FieldPosition position = new FieldPosition(rs.getInt(1),rs.getString(2),rs.getString(3));
                positions.add(position);
            }
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return positions;
    }
    public ObservableList<String> getPositionsName(){
        ObservableList<FieldPosition> positions = getPositions();
        ObservableList<String> positionsName = FXCollections.observableArrayList();
        for (FieldPosition position: positions) {
            positionsName.add(position.getPosition());
        }
        return positionsName;
    }
    public int getIdPosition(String position){
        ObservableList<FieldPosition> positions = getPositions();
        int idPosition = 0;
        for (FieldPosition position1: positions) {
            if (position1.getPosition().equals(position)){
                idPosition = position1.getPositionId();
            }
        }
        return idPosition;
    }
    public String getStringPosition(int idPosition){
        ObservableList<FieldPosition> positions = getPositions();
        String position = "";
        for (FieldPosition position1: positions) {
            if (position1.getPositionId() == idPosition){
                position = position1.getPosition();
            }
        }
        return position;
    }
}
