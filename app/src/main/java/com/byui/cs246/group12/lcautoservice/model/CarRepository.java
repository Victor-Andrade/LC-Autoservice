package com.byui.cs246.group12.lcautoservice.model;

import java.util.ArrayList;
import java.util.List;

public class CarRepository {

    private ExcelManager manager;

    public List<String> getBrands(){
        return new ArrayList<>();
    }

    public List<String> getModels(String brand){
        return new ArrayList<>();
    }

    public List<String> getYears(String brand, String model){
        return new ArrayList<>();
    }

    public List<String> getKilometers(String model, String year) {
        return new ArrayList<>();
    }

    private List<String> getProcedures(Car car) {
        return null;
    }
}
