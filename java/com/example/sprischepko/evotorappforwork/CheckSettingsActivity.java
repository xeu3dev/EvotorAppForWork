package com.example.sprischepko.evotorappforwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class CheckSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_settings);
        Log.d("TAG", "Start Chek Settings Activity");
    }
}
