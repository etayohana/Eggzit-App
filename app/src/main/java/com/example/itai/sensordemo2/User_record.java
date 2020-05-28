package com.example.itai.sensordemo2;

public class User_record {
    private String name;
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User_record(String name, int score) {

        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "User_record{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
