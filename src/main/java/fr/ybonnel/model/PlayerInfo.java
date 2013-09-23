package fr.ybonnel.model;

import java.io.Serializable;

public class PlayerInfo implements Serializable {

    private String pseudo;
    private String email;
    private int score;
    private int[] peopleWaitingTheElevator;
    private int elevatorAtFloor;
    private int peopleInTheElevator;
    private boolean doorIsOpen;
    private String lastErrorMessage;
    private String state;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int[] getPeopleWaitingTheElevator() {
        return peopleWaitingTheElevator;
    }

    public void setPeopleWaitingTheElevator(int[] peopleWaitingTheElevator) {
        this.peopleWaitingTheElevator = peopleWaitingTheElevator;
    }

    public int getElevatorAtFloor() {
        return elevatorAtFloor;
    }

    public void setElevatorAtFloor(int elevatorAtFloor) {
        this.elevatorAtFloor = elevatorAtFloor;
    }

    public int getPeopleInTheElevator() {
        return peopleInTheElevator;
    }

    public void setPeopleInTheElevator(int peopleInTheElevator) {
        this.peopleInTheElevator = peopleInTheElevator;
    }

    public boolean isDoorIsOpen() {
        return doorIsOpen;
    }

    public void setDoorIsOpen(boolean doorIsOpen) {
        this.doorIsOpen = doorIsOpen;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public void setLastErrorMessage(String lastErrorMessage) {
        this.lastErrorMessage = lastErrorMessage;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
