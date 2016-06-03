package com.codekul.intentanfilters;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent intent = new Intent();
                intent.setAction("com.codekul.action.sports");
                intent.addCategory("com.codekul.category.football");
                intent.setData(Uri.parse("http://www.codekul.com/android-training-institute-in-pune.html"));
                startActivity(intent);*/

                /*Intent intentDial = new Intent(Intent.ACTION_DIAL);
                startActivity(intentDial);*/

                /*

                    Intent intentCall = new Intent(Intent.ACTION_CALL);
                    intentCall.setData(Uri.parse("tel://9579938535"));

                    if (ActivityCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE},
                                100);
                    }
                    else
                    startActivity(intentCall);*/

                /*Intent intentSettings = new Intent(
                        android.provider.Settings.ACTION_SETTINGS);
                startActivity(intentSettings);*/

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                startActivity(intent);

            }
        });
    }
}
