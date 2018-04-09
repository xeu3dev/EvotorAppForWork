package com.example.sprischepko.evotorappforwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivityEvotor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_evotor);
        Log.d("TAG", "Start Main Activity");
    }
    public void onClickDeviceInfo(View view){
        Intent intent = new Intent(MainActivityEvotor.this, InfoDeviceActivity.class);
        startActivity(intent);
        Log.d("TAG", "Click Device Info");
    }
    public void onClickCustomCheck(View view){
        Intent intent = new Intent(MainActivityEvotor.this,CheckSettingsActivity.class);
        startActivity(intent);
        Log.d("TAG", "Click Custom Check");
    }
    public void onClickCashRegister(View view){
        Intent intent = new Intent(MainActivityEvotor.this,CashRegisterActivity.class);
        startActivity(intent);
        Log.d("TAG", "Click Custom Check");

    }
}
