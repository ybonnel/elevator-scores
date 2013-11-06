package fr.ybonnel.model;

import java.io.Serializable;

public class PlayerInfo implements Serializable {

    private double averageScore;
    private int nbLoses;
    private int nbSuccess;
    private String photo;
    private int position;
    private String pseudo;
    private int score;

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public int getNbLoses() {
        return nbLoses;
    }

    public void setNbLoses(int nbLoses) {
        this.nbLoses = nbLoses;
    }

    public int getNbSuccess() {
        return nbSuccess;
    }

    public void setNbSuccess(int nbSuccess) {
        this.nbSuccess = nbSuccess;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
