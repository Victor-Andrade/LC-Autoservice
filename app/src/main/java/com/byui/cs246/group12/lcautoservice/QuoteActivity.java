package com.byui.cs246.group12.lcautoservice;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.byui.cs246.group12.lcautoservice.model.Car;
import com.byui.cs246.group12.lcautoservice.model.PdfManager;
import com.google.gson.Gson;
import java.util.ArrayList;

public class QuoteActivity extends AppCompatActivity {
    private Car car;
    private static final String TAG= "QuoteActivity";
    ArrayList<String> procedures;

    /**
     * This method will display the procedures based on the car selection made by the end user.
     * @param savedInstanceState will display and save the car information and procedures.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        Intent receivedIntent = getIntent();
        Gson gson = new Gson();
        car = gson.fromJson(receivedIntent.getStringExtra(CarInfoActivity.SHARED_PREF_NAME), Car.class);
        procedures = receivedIntent.getStringArrayListExtra("Procedures");

        if(car != null){
            Log.d(TAG, car.toString());
            showCarInfo();
        }else{
            Toast.makeText(this, "Error loading the car", Toast.LENGTH_SHORT).show();
        }

        showProcedures();
    }

    private void showCarInfo() {
        TextView carBrand = findViewById(R.id.trademarkTextView);
        TextView carModel = findViewById(R.id.modelTextView);
        TextView carYear = findViewById(R.id.yearTextView);
        TextView carKm = findViewById(R.id.kilometersTextView);
        carBrand.setText(car.getBrand());
        carModel.setText(car.getModel());
        String year = String.valueOf(car.getYear());
        String km = String.valueOf(car.getKilometers());
        carYear.setText(year);
        carKm.setText(km);
    }

    private void showProcedures() {
        if(!procedures.isEmpty()){
            ListView proceduresList = findViewById(R.id.list);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, procedures);
            proceduresList.setAdapter(adapter);
        }
    }

    public void pdfPermissions(View view) {
        ActivityCompat.requestPermissions(this, new
                        String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        PdfManager manager = new PdfManager();
        manager.generatePdf(car,procedures, this);
    }

    public void shareFile(View view) {
        PdfManager manager = new PdfManager();
        manager.buttonShareFile(this);
    }
}