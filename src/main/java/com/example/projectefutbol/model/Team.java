package com.example.projectefutbol.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Team {
    private int teamID;
    private String teamName;

    public Team(int teamID,String teamName) {
        this.teamID = teamID;
        this.teamName = teamName;
    }
    public Team() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public ObservableList<Team> getTeams(){
        ObservableList<Team> teams = FXCollections.observableArrayList();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol_projecte", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM teams");
            while (rs.next()) {
                Team team = new Team(rs.getInt(1),rs.getString(2));
                teams.add(team);
            }
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return teams;
    }
    public ObservableList<String> getTeamsName(){
        ObservableList<Team> teams = getTeams();
        ObservableList<String> teamsName = FXCollections.observableArrayList();
        for (Team team: teams) {
            teamsName.add(team.getTeamName());
        }
        return teamsName;
    }
    public int getTeamID(String teamName){
        ObservableList<Team> teams = getTeams();
        int teamID = 0;
        for (Team team: teams) {
            if(team.getTeamName().equals(teamName)){
                teamID = team.teamID;
            }
        }
        return teamID;
    }
    public String getTeamName(int teamID){
        ObservableList<Team> teams = getTeams();
        String teamName = "";
        for (Team team: teams) {
            if(team.teamID == teamID){
                teamName = team.getTeamName();
            }
        }
        return teamName;
    }
}
