package fr.ybonnel.model;

import java.io.Serializable;

public class PlayerInfo implements Serializable {

    private String pseudo;
    private String id;
    private int score;
    private int nbSuccess;
    private int nbLose;
    private int[] peopleWaitingTheElevator;
    private int elevatorAtFloor;
    private int peopleInTheElevator;
    private boolean doorIsOpen;
    private String lastErrorMessage;
    private String state;
    private String photo;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAverageScore() {
        return ((double) score) / (nbSuccess == 0 ? 1 : nbSuccess);
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getNbLose() {
        return nbLose;
    }

    public void setNbLose(int nbLose) {
        this.nbLose = nbLose;
    }

    public int getNbSuccess() {
        return nbSuccess;
    }

    public void setNbSuccess(int nbSuccess) {
        this.nbSuccess = nbSuccess;
    }
}
