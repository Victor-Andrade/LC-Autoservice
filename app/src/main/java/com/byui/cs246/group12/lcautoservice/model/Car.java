package com.byui.cs246.group12.lcautoservice.model;

public class Car {
    private String brand;
    private String model;
    private int year;
    private int kilometers;

    public Car(String brand, String model, int year, int kilometers) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.kilometers = kilometers;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }
}
