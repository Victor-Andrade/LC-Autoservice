package com.byui.cs246.group12.lcautoservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button goToCarInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button goToCarInfo = findViewById(R.id.goToCarInfoButton);
        goToCarInfo.setOnClickListener(v -> {
            Intent intent = new Intent(this, CarInfoActivity.class);
            startActivity(intent);
        });

    }
}