package com.byui.cs246.group12.lcautoservice;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.byui.cs246.group12.lcautoservice.model.Car;
import com.byui.cs246.group12.lcautoservice.model.ExcelManager;
import com.byui.cs246.group12.lcautoservice.model.PdfManager;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class QuoteActivity extends AppCompatActivity {
    private static final String TAG = "QuoteActivity";
    private Car car;
    private Gson gson;
    private ExcelManager manager;
    List<String> excel;

    /**
     * This method will display the procedures based on the car selection made by the end user.
     * @param savedInstanceState will display and save the car information and procedures.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        Intent receivedIntent = getIntent();
        gson = new Gson();
        car = gson.fromJson(receivedIntent.getStringExtra(CarInfoActivity.SHARED_PREF_NAME), Car.class);
        manager = gson.fromJson(receivedIntent.getStringExtra("ExcelManager"), ExcelManager.class);

        manageExcel();
    }

    private void manageExcel() {
         excel = new ArrayList<>();
        try {
            Log.d(TAG, car.toString());
            Log.d(TAG, manager.toString());
            excel = manager.getProcedures(car);
        } catch (Exception e) {
            Log.e(TAG, "Catch main", e);
        }
        if(!excel.isEmpty()){
            ListView excelList = findViewById(R.id.list);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, excel);
            Toast.makeText(this, excel.get(2), Toast.LENGTH_SHORT).show();
            excelList.setAdapter(adapter);
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
        //manager.buttonShareFile(this);
        manager.generatePdf(car,excel, this);
    }

    public void shareFile(View view) {
        PdfManager manager = new PdfManager();
        manager.buttonShareFile(this);
    }
}