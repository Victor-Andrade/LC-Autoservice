package com.byui.cs246.group12.lcautoservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

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
        ImageView goFacebook = findViewById(R.id.goFacebook);
        goFacebook.setOnClickListener(v -> {
            SocialMediaHandler.goToFacebookProfile(this);
        });

        ImageView goWebsite = findViewById(R.id.goWebsite);
        goWebsite.setOnClickListener(v -> {
            SocialMediaHandler.goToTheWebsite(this);
        });

        ImageView goInstagram = findViewById(R.id.goInstagram);
        goInstagram.setOnClickListener(v -> {
            SocialMediaHandler.goToInstagramProfile(this);
        });
    }
}