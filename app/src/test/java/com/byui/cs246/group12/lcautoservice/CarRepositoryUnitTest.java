package com.byui.cs246.group12.lcautoservice;

import com.byui.cs246.group12.lcautoservice.model.Car;
import com.byui.cs246.group12.lcautoservice.model.CarRepository;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CarRepositoryUnitTest {
    @Test
    public void new_car() {
        Car car = new Car("Mitsubishi","L500",2015,10);
        assertEquals("Mitsubishi", car.getBrand());
        assertEquals("L500", car.getModel());
        assertEquals(2015, car.getYear());
        assertEquals(10, car.getKilometers());
    }

    @Test
    public void repositoryUnitTest(){
        CarRepository carRepository = new CarRepository();
        List<String> brands = carRepository.getBrands();
        String[] expectBrands = {"HONDA", "MITSUBISH"};
        assertArrayEquals( expectBrands ,brands.toArray());
        List<String> models = carRepository.getModels("HONDA");
        String[] expectModels = {"L200"};
        assertArrayEquals(expectModels, models.toArray());
    }


}
