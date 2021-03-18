package com.byui.cs246.group12.lcautoservice.model;

import java.util.ArrayList;
import java.util.List;

/**
 * CarRepository abstracts and handle the readings from the  {@link ExcelManager ExcelManager}.
 */
public class CarRepository {
    private ExcelManager manager;

    /**
     *
     * @return Return a list of all the brands in the repository.
     */
    public List<String> getBrands(){
        return new ArrayList<>();
    }

    /**
     * This handles the model solicitations.
     * @param brand The brand that you want the list of models.
     * @return Return the list of the models based on the received brand.
     */
    public List<String> getModels(String brand){
        return new ArrayList<>();
    }

    /**
     *
     * @param brand The brand of the car.
     * @param model The model of the car.
     * @return Return the years that the app has in catalog.
     */
    public List<String> getYears(String brand, String model){
        return new ArrayList<>();
    }

    /**
     * @param brand The brand of the car.
     * @param model The model of the car.
     * @param year The year of the car.
     * @return Returns list of kilometers that the car needs to be checked
     */
    public List<String> getKilometers(String brand, String model, String year) {
        return new ArrayList<>();
    }

    /**
     * List the procedures needed for {@link Car Car}.
     * @param car The {@link Car Car}. to check the procedures.
     * @return Return the list of procedures for the received {@link Car Car}.
     */
    private List<String> getProcedures(Car car) {
        return null;
    }
}
