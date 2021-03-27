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
import java.util.ArrayList;
import java.util.List;

public class CarInfoActivity extends AppCompatActivity {
    private static final String TAG = "CarInfoActivity";
    public static final String SHARED_PREF_NAME = "autoInfo";
    Spinner tradeMarkSpinner, modelSpinner, yearSpinner, kilometersSpinner;
    private ExcelManager manager;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);
        gson = new Gson();
        manager = gson.fromJson(getIntent().getStringExtra("ExcelManager"), ExcelManager.class);
        Log.v(TAG, "Loading screen");
        startSpinners();
    }

    private void startSpinners() {
        //Test list
        //This list will come from the Excel class

        List<String> trademarkList = manager.getBrands();

        List<String> modelList = new ArrayList<>();

        ArrayList<String> yearsList = new ArrayList<>();

        ArrayList<String> kilometersList = new ArrayList<>();


        //This is the 1st spinner
        tradeMarkSpinner = findViewById(R.id.tradeMarkSpinner);
        ArrayAdapter<String> arrayAdapter_parent = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, trademarkList);
        arrayAdapter_parent.setDropDownViewResource(android.R.layout.simple_spinner_item);
        tradeMarkSpinner.setAdapter(arrayAdapter_parent);

        //This is the 2nd spinner
        modelSpinner = findViewById(R.id.modelSpinner);
        ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, modelList);
        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        modelSpinner.setAdapter(modelAdapter);

        //This is the 3rd spinner
        yearSpinner = findViewById(R.id.yearSpinner);
        ArrayAdapter<String> yearsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, yearsList);
        yearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        yearSpinner.setAdapter(yearsAdapter);

        //This is the 4th spinner
        kilometersSpinner = findViewById(R.id.kilometersSpinner);
        ArrayAdapter<String> kilometersAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, kilometersList);
        kilometersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        kilometersSpinner.setAdapter(kilometersAdapter);

        tradeMarkSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //When select something, we want to search on the database to check the list of the
                // models from this brand.
                //Then we want to update the content for the next spinner
                String item = parent.getItemAtPosition(position).toString();
                modelList.clear();
                modelList.addAll(manager.getModels(item));
                modelAdapter.notifyDataSetChanged();
                Log.i(TAG, "Brand Spinner selected " + item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        modelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                yearsList.clear();
                yearsList.addAll(manager.getYears(tradeMarkSpinner.getSelectedItem().toString(), item));
                yearsAdapter.notifyDataSetChanged();
                Log.i(TAG, "Model Spinner selected " + item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                kilometersList.clear();
                List<String> receivedKilometers = manager.getKilometers(tradeMarkSpinner.getSelectedItem().toString(), modelSpinner.getSelectedItem().toString(), item);
                kilometersList.addAll(receivedKilometers);
                kilometersAdapter.notifyDataSetChanged();
                Log.i(TAG, "Kilometers Spinner selected " + item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
        String brand = tradeMarkSpinner.getSelectedItem().toString();
        String model = modelSpinner.getSelectedItem().toString();
        int year = Integer.parseInt(yearSpinner.getSelectedItem().toString());
        int kilometers = Integer.parseInt(kilometersSpinner.getSelectedItem().toString());
        Intent newintent = new Intent(this, QuoteActivity.class);
        Car car = new Car(brand, model, year, kilometers);
        String managerJson = gson.toJson(manager);
        String carJson = gson.toJson(car);
        Log.i(TAG, carJson);
        newintent.putExtra("ExcelManager", managerJson);
        newintent.putExtra(SHARED_PREF_NAME, carJson);
        startActivity(newintent);
    }
}