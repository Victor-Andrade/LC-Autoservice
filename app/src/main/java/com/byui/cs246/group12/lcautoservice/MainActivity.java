package com.byui.cs246.group12.lcautoservice;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import com.byui.cs246.group12.lcautoservice.model.ExcelManager;
import com.byui.cs246.group12.lcautoservice.model.SocialMediaHandler;
import java.io.IOException;

/**
 * This class shows the first screen with the sponsor's main social media links and
 * the button that will navigate to the selection screen.
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button goToCarInfo;

    /**
     * Customer can visit the sponsor's main sites or click to enter car information button.
     * @param savedInstanceState will save the instance of the app during load.
     */

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
        goFacebook.setOnClickListener(v -> SocialMediaHandler.goToFacebookProfile(this));

        ImageView goWebsite = findViewById(R.id.goWebsite);
        goWebsite.setOnClickListener(v -> SocialMediaHandler.goToTheWebsite(this));

        ImageView goInstagram = findViewById(R.id.goInstagram);
        goInstagram.setOnClickListener(v -> SocialMediaHandler.goToInstagramProfile(this));
        try {
            ExcelManager.startManager(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}