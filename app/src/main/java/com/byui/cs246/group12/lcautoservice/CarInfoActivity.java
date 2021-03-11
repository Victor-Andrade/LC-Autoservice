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
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class CarInfoActivity extends AppCompatActivity {
    private static final String TAG = "CarInfoActivity";
    public static final String SHARED_PREF_NAME = "autoInfo";
    Spinner tradeMarkSpinner;

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
        List<String> list = new ArrayList<>();
        list.add("Honda");
        list.add("Fiat");

        //This is the 1º spinner
        tradeMarkSpinner = findViewById(R.id.tradeMarkSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tradeMarkSpinner.setAdapter(adapter);
        tradeMarkSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //When select something, we want to search on the database to check the list of the
                // models from this brand.
                //Then we want to update the content for the next spinner

                String item = parent.getItemAtPosition(position).toString();
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