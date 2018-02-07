package com.example.sprischepko.evotorappforwork;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoDeviceActivity extends AppCompatActivity {
    TextView ResultView;
    Button Clear;
    Button Copy;
    Button GetImei;
    String Result;
    TelephonyManager TelephonyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_device);

        ResultView = findViewById(R.id.device_info_result);
        GetImei = findViewById(R.id.get_imei_button);
        Clear = findViewById(R.id.clear_result_button);
        Copy = findViewById(R.id.copy_result_button);

        // Clear Button
        Clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ResultView.setText("");
            }
        });

        // Copy Button
        Copy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", ResultView.getText().toString());
                clipboard.setPrimaryClip(clip);
            }
        });

        // Get IMEI Button
        TelephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        GetImei.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Result = TelephonyManager.getDeviceId();

                ResultView.setText(Result);
            }
        });

    }
}
