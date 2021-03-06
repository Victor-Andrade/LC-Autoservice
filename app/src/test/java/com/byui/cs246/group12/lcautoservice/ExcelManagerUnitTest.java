package com.byui.cs246.group12.lcautoservice;

import com.byui.cs246.group12.lcautoservice.model.Car;
import com.byui.cs246.group12.lcautoservice.model.ExcelManager;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class ExcelManagerUnitTest {
    @Test
    public void procedurestest(){
        ExcelManager manager = new ExcelManager();
        Car car = new Car("Mitsubishi","L500",2015,10);
        List<String> procedures = manager.getProcedures(car);
        String[] expectedProcedures = {"procedure1", "procedure2", "procedure3"};
        assertArrayEquals(expectedProcedures, procedures.toArray());
    }
}
