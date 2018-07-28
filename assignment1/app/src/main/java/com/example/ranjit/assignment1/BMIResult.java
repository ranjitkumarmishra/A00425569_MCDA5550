package com.example.ranjit.assignment1;

public class BMIResult {
    private double height=1;
    private double weight=1;
    private String date;
    private double bmi;
    //TODO also add Date

    public BMIResult(){}

    public BMIResult(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getResult() {
        return weight/(height*height);
    }

    @Override
    public String toString() {
        return String.valueOf(getBmi())+"           "+getDate();
    }
}
