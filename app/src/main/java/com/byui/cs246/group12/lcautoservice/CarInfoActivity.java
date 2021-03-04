package com.byui.cs246.group12.lcautoservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.byui.cs246.group12.lcautoservice.model.ExcelManager;

import java.util.ArrayList;
import java.util.List;

public class CarInfoActivity extends AppCompatActivity {
    Spinner tradeMarkSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);

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
                Toast.makeText(CarInfoActivity.this, item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}