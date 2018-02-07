package com.example.sprischepko.evotorappforwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivityEvotor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_evotor);
    }
    public void onClickDeviceInfo(View view){
        Intent intent = new Intent(MainActivityEvotor.this, InfoDeviceActivity.class);
        startActivity(intent);
    }
}
