package com.byui.cs246.group12.lcautoservice;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class QuoteActivity extends AppCompatActivity {
    /**
     * This method will display the procedures based on the car selection made by the end user.
     * @param savedInstanceState will display and save the car information and procedures.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        SharedPreferences preferences = getSharedPreferences(CarInfoActivity.SHARED_PREF_NAME, MODE_PRIVATE);
        String storedPreference = preferences.getString("tradeMarkSpinner", "newString");
        TextView showPref = findViewById(R.id.textView);
        showPref.setText(storedPreference);
    }
}