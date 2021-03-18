package com.byui.cs246.group12.lcautoservice.model;

/**
 * This class represent the car info
 */
public class Car {
    private String brand;
    private String model;
    private int year;
    private int kilometers;

    /**
     *
     * @param brand Represents the car brand.
     * @param model Represents the car model.
     * @param year Represents the car year.
     * @param kilometers Represents the car kilometers.
     */
    public Car(String brand, String model, int year, int kilometers) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.kilometers = kilometers;
    }

    /**
     *
     * @return The brand of the car.
     */
    public String getBrand() {
        return brand;
    }

    /**
     *
     * @param brand The brand of the car.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     *
     * @return The car model.
     */
    public String getModel() {
        return model;
    }

    /**
     *
     * @param model The model of the car.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     *
     * @return The car year.
     */
    public int getYear() {
        return year;
    }

    /**
     *
     * @param year The year of the car assembly.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     *
     * @return Return how many kilometers the car has been used.
     */
    public int getKilometers() {
        return kilometers;
    }

    /**
     *
     * @param kilometers Set how many kilometers the car has been used.
     */
    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }
}
