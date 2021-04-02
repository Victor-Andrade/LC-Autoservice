package com.byui.cs246.group12.lcautoservice.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * This class handle the reading of the excel files with the car info.
 */
public class ExcelManager implements Serializable {
    private static final String TAG = "ExcelManager";
    private final HashMap<String, HashMap<String, HashMap<String, HashMap<String, List<String>>>>> carsData;

    private ExcelManager(HashMap<String, HashMap<String, HashMap<String, HashMap<String, List<String>>>>> map) {
        this.carsData = map;
    }

    public List<String> getBrands() {
        return new ArrayList<>(carsData.keySet());
    }

    public List<String> getModels(String brand) {
        return new ArrayList<>(Objects.requireNonNull(carsData.get(brand)).keySet());
    }

    public List<String> getYears(String brand, String model) {
        return new ArrayList<>(Objects.requireNonNull(Objects.requireNonNull(carsData.get(brand)).get(model)).keySet());
    }

    public List<String> getKilometers(String brand, String model, String year) {
        return new ArrayList<>(Objects.requireNonNull
                (Objects.requireNonNull
                        (Objects.requireNonNull
                                (carsData.get(brand))
                                .get(model))
                        .get(year))
                .keySet());
    }

    /**
     * This method return the procedures needed for a car.
     *
     * @param car The car that the user want to know the procedures.
     * @return Returns a list of procedures.
     */
    public List<String> getProcedures(Car car) {
        return Objects.requireNonNull(Objects.requireNonNull(Objects.requireNonNull(carsData.get(car.getBrand()))
                .get(car.getModel()))
                .get(String.valueOf(car.getYear())))
                .get(String.valueOf(car.getKilometers()));
    }

    private static List<String[]> readFile(AssetManager manager, String filePath) throws IOException {
        String line;
        String[] data;
        InputStream f = manager.open(filePath);
        InputStreamReader reader = new InputStreamReader(f);
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<String[]> list = new ArrayList<>();
        while (reader.ready()) {
            line = bufferedReader.readLine();
            data = line.split(",");
            Log.d(TAG, Arrays.toString(data));
            list.add(data);
        }
        bufferedReader.close();
        return list;
    }

    private static List<String> listCsv(Context context) throws IOException {
        String[] files;
        List<String> csvFiles = new ArrayList<>(20);
        AssetManager assetManager = context.getAssets();
        files = assetManager.list("");
        for (String file : files) {
            if (file.endsWith(".CSV")) {
                csvFiles.add(file);
            }
        }
        return csvFiles;
    }

    /**
     *
     * @param context of the assets.
     * @return Returns a collection of HashMaps to store the car information.
     * @throws IOException to add labels to the different dropdown options.
     */
    public static ExcelManager startManager(Context context) throws IOException {
        List<String> files = listCsv(context);
        AssetManager assetManager = context.getAssets();
        /*
         A collection of nested HashMaps to store the car info, following this pattern(starting by the external value)
         Store the hashmaps according to:
         1. Brand
         2. Model
         3. Year
         4. Kilometers
          After it, will be a list of procedures for this car.
         */
        HashMap<String, HashMap<String, HashMap<String, HashMap<String, List<String>>>>> carInfo = new HashMap<>();
        for (String file : files) {
            Log.d(TAG, "File: " + file);
            List<String[]> csvData = readFile(assetManager, file);
            int len = csvData.size();
            for (int i = 1; i < len; i++) {
                //First, veify if the trademark is registered.
                String[] line = csvData.get(i);
                Log.d(TAG, Arrays.toString(line));
                String brand = line[0];
                String model = line[1];
                String year = line[2];
                String km = line[3];
                String procedure = line[4];
                if (!carInfo.containsKey(brand)) {
                    Log.d(TAG, "Adding new brand - " + brand);
                    carInfo.put(brand, new HashMap<>());
                }
                HashMap<String, HashMap<String, HashMap<String, List<String>>>> models = carInfo.get(brand);
                assert models != null;
                if (!models.containsKey(model)) {
                    Log.d(TAG, "Adding new model - " + model);
                    models.put(model, new HashMap<>());
                }
                HashMap<String, HashMap<String, List<String>>> years = models.get(model);
                assert years != null;
                if (!years.containsKey(year)) {
                    Log.d(TAG, "Adding new year - " + year);
                    years.put(year, new HashMap<>());
                }
                HashMap<String, List<String>> kilometers = years.get(year);
                assert kilometers != null;
                if (!kilometers.containsKey(km)) {
                    Log.d(TAG, "Adding new kilometer - " + km);
                    kilometers.put(km, new ArrayList<>(20));
                }
                List<String> procedures = kilometers.get(km);
                Log.v(TAG, "Adding procedure: " + procedure);
                assert procedures != null;
                procedures.add(procedure);
            }
        }
        return new ExcelManager(carInfo);
    }
}
