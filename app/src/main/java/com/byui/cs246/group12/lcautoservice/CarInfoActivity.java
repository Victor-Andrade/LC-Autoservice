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
import java.util.ArrayList;

public class CarInfoActivity extends AppCompatActivity {
    private static final String TAG = "CarInfoActivity";
    public static final String SHARED_PREF_NAME = "autoInfo";
    Spinner tradeMarkSpinner, modelSpinner, yearSpinner, kilometersSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);

        Log.v(TAG, "Loading screen");
        startSpinners();
    }

    private void startSpinners() {
        //Test list
        //This list will come from the Excel class
        ArrayList<String> trademarkList = new ArrayList<>();
        trademarkList.add("Honda");
        trademarkList.add("Mitsubishi");

        ArrayList<String> modelList = new ArrayList<>();
        modelList.add("City");
        modelList.add("L200");

        ArrayList<String> yearsList = new ArrayList<>();
        yearsList.add("2019");
        yearsList.add("2020");
        yearsList.add("2021");

        ArrayList<String> kilometersList = new ArrayList<>();
        kilometersList.add("5");
        kilometersList.add("10");
        kilometersList.add("20");
        kilometersList.add("30");
        kilometersList.add("40");
        kilometersList.add("50");


        //This is the 1st spinner
        tradeMarkSpinner = findViewById(R.id.tradeMarkSpinner);
        ArrayAdapter<String> arrayAdapter_parent = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, trademarkList);
        arrayAdapter_parent.setDropDownViewResource(android.R.layout.simple_spinner_item);
        tradeMarkSpinner.setAdapter(arrayAdapter_parent);

        //This is the 2nd spinner
        modelSpinner = findViewById(R.id.modelSpinner);
        final ArrayAdapter[] arrayAdapter_model = new ArrayAdapter[]{new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, modelList)};
        arrayAdapter_model[0].setDropDownViewResource(android.R.layout.simple_spinner_item);
        modelSpinner.setAdapter(arrayAdapter_model[0]);

        //This is the 3rd spinner
        yearSpinner = findViewById(R.id.yearSpinner);
        ArrayAdapter<String> arrayAdapter_years = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, yearsList);
        arrayAdapter_years.setDropDownViewResource(android.R.layout.simple_spinner_item);
        yearSpinner.setAdapter(arrayAdapter_years);

        //This is the 4th spinner
        kilometersSpinner = findViewById(R.id.kilometersSpinner);
        ArrayAdapter<String> arrayAdapter_kilometers = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, kilometersList);
        arrayAdapter_kilometers.setDropDownViewResource(android.R.layout.simple_spinner_item);
        kilometersSpinner.setAdapter(arrayAdapter_kilometers);

        tradeMarkSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //When select something, we want to search on the database to check the list of the
                // models from this brand.
                //Then we want to update the content for the next spinner
                String item = parent.getItemAtPosition(position).toString();
                if(item.equals("Honda")) {
                    arrayAdapter_model[0] =new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,modelList);
                }
                modelSpinner.setAdapter(arrayAdapter_model[0]);

                Log.i(TAG, "EU Spinner selected " + item);
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
        Intent intent = new Intent(this, QuoteActivity.class);
        startActivity(intent);
    }
}