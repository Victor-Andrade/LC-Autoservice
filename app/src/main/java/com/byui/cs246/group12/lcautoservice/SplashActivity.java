package com.byui.cs246.group12.lcautoservice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import com.byui.cs246.group12.lcautoservice.model.ExcelManager;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Objects;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Objects.requireNonNull(getSupportActionBar()).hide();
        FilesLoader filesLoader = new FilesLoader();
        filesLoader.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class FilesLoader extends AsyncTask<Void, Void, ExcelManager> {
        private ExcelManager manager = null;
        @Override
        protected void onPostExecute(ExcelManager aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            Gson gson = new Gson();
            String managerJson = gson.toJson(manager);
            intent.putExtra("ExcelManager", managerJson);
            startActivity(intent);
            finish();
        }

        @Override
        protected ExcelManager doInBackground(Void... Voids) {
            try {
                manager = ExcelManager.startManager(getApplicationContext());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return manager;
        }
    }
}