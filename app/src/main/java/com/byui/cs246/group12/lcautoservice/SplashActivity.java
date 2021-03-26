package com.byui.cs246.group12.lcautoservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;

import com.byui.cs246.group12.lcautoservice.model.ExcelManager;
import com.google.gson.Gson;

import java.io.IOException;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        FilesLoader filesLoader = new FilesLoader();
        filesLoader.execute();
    }

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