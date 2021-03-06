package com.byui.cs246.group12.lcautoservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.byui.cs246.group12.lcautoservice.model.SocialMediaHandler;

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
        Button testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(v -> {
            SocialMediaHandler.goToInstagramProfile(this);
        });
    }
}