package com.byui.cs246.group12.lcautoservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuoteActivity extends AppCompatActivity {
    private static final String TAG = "QuoteActivity";
    Car car;

    /**
     * This method will display the procedures based on the car selection made by the end user.
     * @param savedInstanceState will display and save the car information and procedures.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        car = new Car("MITSUBISHI","L200",2019,300);

        manageExcel();
    }

    private void manageExcel() {
        //Not working, needs Correction
//        List<String> excel = new ArrayList<>();
//        try {
//            ExcelManager manager = new ExcelManager(this);
//            excel = manager.getProcedures(car);
//        } catch (IOException e) {
//            Log.e(TAG, "Catch main", e);
//        }
//        if(!excel.isEmpty()){
//            ListView excelList = (ListView) findViewById(R.id.list);
//            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, excel);
//            Toast.makeText(this, excel.get(2), Toast.LENGTH_SHORT).show();
//            excelList.setAdapter(adapter);
//        }
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
    }
}