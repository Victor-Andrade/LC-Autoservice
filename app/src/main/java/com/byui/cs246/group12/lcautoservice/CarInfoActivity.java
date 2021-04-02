package com.byui.cs246.group12.lcautoservice;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.byui.cs246.group12.lcautoservice.model.Car;
import com.byui.cs246.group12.lcautoservice.model.ExcelManager;
import com.google.gson.Gson;
import java.util.List;
import java.util.concurrent.Callable;

public class CarInfoActivity extends AppCompatActivity {
    private static final String TAG = "CarInfoActivity";
    public static final String SHARED_PREF_NAME = "autoInfo";
    private Spinner tradeMarkSpinner, modelSpinner, yearSpinner, kilometersSpinner;
    private String trademark, model, year, kilometers;
    private ExcelManager manager;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);
        gson = new Gson();
        manager = gson.fromJson(getIntent().getStringExtra("ExcelManager"), ExcelManager.class);
        Log.v(TAG, "Loading screen");
        startSpinners();
    }

    private void startSpinners(){
        List<String> trademarkList = manager.getBrands();
        tradeMarkSpinner = findViewById(R.id.tradeMarkSpinner);
        modelSpinner = findViewById(R.id.modelSpinner);
        yearSpinner = findViewById(R.id.yearSpinner);
        kilometersSpinner = findViewById(R.id.kilometersSpinner);

        //Start first Spinner
        //Each spinner will update the next one data
        spinnerAdapterFactory(tradeMarkSpinner, trademarkList, trademarkListener());
    }

    private AdapterView.OnItemSelectedListener trademarkListener(){
        return listenerGenerator(()->{
                    trademark = tradeMarkSpinner.getSelectedItem().toString();
                    List<String> newModels = manager.getModels(trademark);
                    resetSpinners(yearSpinner, kilometersSpinner, modelSpinner);
                    spinnerAdapterFactory(modelSpinner, newModels, modelListener());
                    Log.i(TAG, "new models " + newModels);
                    return null;
        });
    }

    private AdapterView.OnItemSelectedListener modelListener(){
        return listenerGenerator(()->{
            model = modelSpinner.getSelectedItem().toString();
            List<String> newYears = manager.getYears(trademark, model);
            resetSpinners(yearSpinner, kilometersSpinner);
            Log.i(TAG, "new years:  " + newYears);
            spinnerAdapterFactory(yearSpinner, newYears, yearListener());
            return null;
        });
    }

    private AdapterView.OnItemSelectedListener yearListener(){
        return listenerGenerator(()->{
            year = yearSpinner.getSelectedItem().toString();
            List<String> newKilometers = manager.getKilometers(trademark, model, year);
            resetSpinners(kilometersSpinner);
            Log.i(TAG, "new kilometers:  " + newKilometers);
            spinnerAdapterFactory(kilometersSpinner, newKilometers, listenerGenerator(()->{
                        kilometers = kilometersSpinner.getSelectedItem().toString();
                        Log.i(TAG, "kilometers Spinner selected " + kilometers);
                        return null;
                    })
            );
            return null;
        });
    }

    private AdapterView.OnItemSelectedListener listenerGenerator(Callable<Void> itemSelected){
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    itemSelected.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String spinnerData = tradeMarkSpinner.getSelectedItem().toString();
        editor.putString("tradeMarkSpinner",spinnerData);
        editor.apply();
    }

    public void navToProc(View view) {
        Intent newintent = new Intent(this, QuoteActivity.class);
        Car car = new Car(trademark, model, Integer.parseInt(year), Integer.parseInt(kilometers));
        String managerJson = gson.toJson(manager);
        String carJson = gson.toJson(car);
        Log.i(TAG, carJson);
        newintent.putExtra("ExcelManager", managerJson);
        newintent.putExtra(SHARED_PREF_NAME, carJson);
        startActivity(newintent);
    }

    //Creating spinner adapter factory
    /*
    IT should load the spinner according to received data.
    it will receive a list and a spinner. it will populate the spinner with the received data.
     */
    private void spinnerAdapterFactory(Spinner spinner, List<String> data, AdapterView.OnItemSelectedListener listener){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.selected_item, data);
        adapter.setDropDownViewResource(R.layout.dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(listener);
    }

    /*
    Spinner reseter
    it will reset and delete the data from one spinner
     */
    private void resetSpinners(Spinner... spinners){
        for (Spinner spinner : spinners) {
            spinner.setAdapter(null);
        }
    }

}