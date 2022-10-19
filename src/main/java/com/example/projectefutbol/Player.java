package com.example.projectefutbol;

import java.time.LocalDate;

public class Player {
    private int playerID;
    private String name;
    private String surname;
    private int age;
    private FieldPosition fieldPosition;
    private LocalDate endOfContract;
    private Team team;

    public Player(int playerID, String name, String surname, int age, FieldPosition fieldPosition, LocalDate endOfContract, Team team) {
        this.playerID = playerID;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.fieldPosition = fieldPosition;
        this.endOfContract = endOfContract;
        this.team = team;
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

    public FieldPosition getFieldPosition() {
        return fieldPosition;
    }

    public void setFieldPosition(FieldPosition fieldPosition) {
        this.fieldPosition = fieldPosition;
    }

    public LocalDate getEndOfContract() {
        return endOfContract;
    }

    public void setEndOfContract(LocalDate endOfContract) {
        this.endOfContract = endOfContract;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
