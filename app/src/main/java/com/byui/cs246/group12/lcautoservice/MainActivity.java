package com.byui.cs246.group12.lcautoservice;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import com.byui.cs246.group12.lcautoservice.model.ExcelManager;
import com.byui.cs246.group12.lcautoservice.model.SocialMediaHandler;
import com.google.gson.Gson;

/**
 * This class shows the first screen with the sponsor's main social media links and
 * the button that will navigate to the selection screen.
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ExcelManager manager;

    /**
     * Customer can visit the sponsor's main sites or click to enter car information button.
     * @param savedInstanceState will save the instance of the app during load.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button goToCarInfo = findViewById(R.id.goToCarInfoButton);
        Gson gson = new Gson();
        manager = gson.fromJson(getIntent().getStringExtra("ExcelManager"), ExcelManager.class);
        if(manager == null){
            Log.e(TAG, "Manager is null");
        }else{
            Log.d(TAG, "Manager received");
        }
        goToCarInfo.setOnClickListener(v -> {
            Intent intent = new Intent(this, CarInfoActivity.class);
            String managerJson = gson.toJson(manager);
            intent.putExtra("ExcelManager", managerJson);
            startActivity(intent);
        });
        ImageView goFacebook = findViewById(R.id.goFacebook);
        goFacebook.setOnClickListener(v -> SocialMediaHandler.goToFacebookProfile(this));

        ImageView goWebsite = findViewById(R.id.goWebsite);
        goWebsite.setOnClickListener(v -> SocialMediaHandler.goToTheWebsite(this));

        ImageView goInstagram = findViewById(R.id.goInstagram);
        goInstagram.setOnClickListener(v -> SocialMediaHandler.goToInstagramProfile(this));
    }
}