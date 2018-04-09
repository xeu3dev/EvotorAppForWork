package com.example.sprischepko.evotorappforwork;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class CashRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get CashRegister
        Intent intent = getPackageManager().getLaunchIntentForPackage("ru.evotor.cashregisterutility");
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
}
