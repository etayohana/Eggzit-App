package com.example.itai.sensordemo2;

public class Fendss {


    public Fendss() {

    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getStarty() {
        return starty;
    }

    public void setStarty(int starty) {
        this.starty = starty;
    }

    public int getEndy() {
        return endY;
    }

    public void setEndy(int endy) {
        this.endY = endy;
    }

    public Fendss(int startX, int endX, int starty, int endy) {

        this.startX = startX;
        this.endX = endX;
        this.starty = starty;
        this.endY = endy;
    }

    private int startX;
    private int endX;
    private int starty;
    private int endY;


}
