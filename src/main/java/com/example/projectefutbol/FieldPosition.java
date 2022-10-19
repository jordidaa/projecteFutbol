package com.example.projectefutbol;

public class FieldPosition {
    private int positionId;
    private String position;
    private String side;

    public FieldPosition(int positionId, String position, String side) {
        this.positionId = positionId;
        this.position = position;
        this.side = side;
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
}
