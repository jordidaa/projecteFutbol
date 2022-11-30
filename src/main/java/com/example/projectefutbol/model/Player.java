package com.example.projectefutbol.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Player {
    private String name,surname,teamStr,posicionStr;
    private int playerID,age,team,position;
    private LocalDate endOfContract;

    public Player( String name, String surname, int age, LocalDate endOfContract, int team, int position) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.position = position;
        this.endOfContract = endOfContract;
        this.team = team;

    }
    public Player(){

    }

    public Player(String name, String surname, String teamStr, String posicionStr, int playerID, int age, LocalDate endOfContract) {
        this.name = name;
        this.surname = surname;
        this.teamStr = teamStr;
        this.posicionStr = posicionStr;
        this.playerID = playerID;
        this.age = age;
        this.endOfContract = endOfContract;
    }

    public String getTeamStr() {
        return teamStr;
    }

    public void setTeamStr(String teamStr) {
        this.teamStr = teamStr;
    }

    public String getPosicionStr() {
        return posicionStr;
    }

    public void setPosicionStr(String posicionStr) {
        this.posicionStr = posicionStr;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }



    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFieldPosition() {
        return position;
    }

    public void setFieldPosition(int fieldPosition) {
        this.position = fieldPosition;
    }

    public LocalDate getEndOfContract() {
        return endOfContract;
    }

    public void setEndOfContract(LocalDate endOfContract) {
        this.endOfContract = endOfContract;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public void savePlayer(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol_projecte", "root", "");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO players set name = '" + getName() + "', surname = '" + getSurname() + "'" +
                    ", age = '" + getAge() + "' , contract = '" + getEndOfContract() + "'" +
                    ", team = '" + getTeam() + "' , position = '" + getFieldPosition() + "'");
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<Player> getAllPlayers(){
        ObservableList<Player> players = FXCollections.observableArrayList();
        Team team = new Team();
        FieldPosition position = new FieldPosition();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol_projecte", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM players");
            while (rs.next()){

                Player player = new Player(rs.getString("name"),rs.getString("surname"),team.getTeamName(rs.getInt("team"))
                        ,position.getStringPosition(rs.getInt("position")),rs.getInt("id"),rs.getInt("age"),rs.getDate("contract").toLocalDate());
                players.add(player);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return players;
    }
    public void deletePlayer(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol_projecte", "root", "");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM players WHERE id = " + this.playerID);
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updatePlayer(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol_projecte", "root", "");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE players set name = '" + this.getName() + "', surname = '" + this.getSurname() + "'" +
                    ", age = '" + this.getAge() + "' , contract = '" + this.getEndOfContract() + "'" +
                    ", team = '" + this.getTeam() + "' , position = '" + this.getFieldPosition() + "' WHERE id = " + this.getPlayerID());
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public ObservableList<Player> getAllPlayersBus(String name){
        ObservableList<Player> players = FXCollections.observableArrayList();
        Team team = new Team();
        FieldPosition position = new FieldPosition();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol_projecte", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM players WHERE name LIKE '%"+name+"%' or surname LIKE '%"+name+"%'");
            while (rs.next()){

                Player player = new Player(rs.getString("name"),rs.getString("surname"),team.getTeamName(rs.getInt("team"))
                        ,position.getStringPosition(rs.getInt("position")),rs.getInt("id"),rs.getInt("age"),rs.getDate("contract").toLocalDate());
                players.add(player);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return players;
    }
}
