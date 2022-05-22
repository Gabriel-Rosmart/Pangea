package com.backend;

public class RecordSystem {
    private int points;

    public RecordSystem(){
        this.points = 0;
    }

    public void increasePoints(int quantity){
        this.points += quantity;
    }

    public int getPoints(){
        return this.points;
    }

    public void resetPoints(){
        this.points = 0;
    }

    public void setPoints(int quantity){
        this.points = quantity;
    }
}
