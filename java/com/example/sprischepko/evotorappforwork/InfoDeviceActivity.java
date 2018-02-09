package com.example.sprischepko.evotorappforwork;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class InfoDeviceActivity extends AppCompatActivity {
    TextView ResultView;
    Button Clear;
    Button Copy;
    Button Send;
    Button GetImei;
    Button GetModel;
    Button GetPOS;
    Button GetWall;
    Button GetSS;
    Button GetDS;
    Button GetInstalledApps;
    Button GetVersion;
    String Result;
    TelephonyManager TelephonyManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_device);

        ResultView = findViewById(R.id.device_info_result);
        GetImei = findViewById(R.id.get_imei_button);
        GetModel = findViewById(R.id.get_model_button);
        GetPOS = findViewById(R.id.get_POS_button);
        GetWall = findViewById(R.id.get_wall_button);
        GetSS = findViewById(R.id.get_SS_button);
        GetDS = findViewById(R.id.get_DS_button);
        GetInstalledApps = findViewById(R.id.get_installed_apps_button);
        GetVersion = findViewById(R.id.get_android_version_button);
        Clear = findViewById(R.id.clear_result_button);
        Copy = findViewById(R.id.copy_result_button);
        Send = findViewById(R.id.send_result);

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
                Toast toast = Toast.makeText(getApplicationContext(), R.string.copied, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            }
        });

        // Send Button
        Send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
            String text = ResultView.getText().toString();
            if(text.isEmpty()) return;
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, text);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
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

        // Get Model Butoon
        GetModel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Result = android.os.Build.MODEL;
                ResultView.setText(Result);
            }
        });

        // Get Evotor POS
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo("ru.atol.tabletpos", 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        final android.content.pm.PackageInfo finalPackageInfoPOS = packageInfo;
        GetPOS.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                Result = finalPackageInfoPOS.versionName;
                ResultView.setText(Result);
                }
            });

        // Get EvoWall
        try {
            packageInfo = getPackageManager().getPackageInfo("ru.evotor.proxy", 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        final android.content.pm.PackageInfo finalPackageInfoWall = packageInfo;
        GetWall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                Result = finalPackageInfoWall.versionName;
                ResultView.setText(Result);
            }
        });

        // Get system service
        try {
            packageInfo = getPackageManager().getPackageInfo("ru.atol.systemservice", 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        final android.content.pm.PackageInfo finalPackageInfoSS = packageInfo;
        GetSS.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                Result = finalPackageInfoSS.versionName;
                ResultView.setText(Result);
            }
        });

        //Get device service
        try {
            packageInfo = getPackageManager().getPackageInfo("ru.evotor.devices", 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        final android.content.pm.PackageInfo finalPackageInfoDS = packageInfo;
        GetDS.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                Result = finalPackageInfoDS.versionName;
                ResultView.setText(Result);
            }
        });

        // Get Installed Apps
        GetInstalledApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoDeviceActivity.this, VersionAppsActivity.class);
                startActivity(intent);
            }
        });

        // Get Version
        GetVersion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Result = Build.VERSION.RELEASE;
                ResultView.setText(Result);
            }
        });
    }
}
