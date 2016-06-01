package com.codekul.dialogdemo;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnAlert).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                MyDialog dialog = new MyDialog();
                dialog.show(getSupportFragmentManager(),
                        MyDialog.KEY_ALERT_DIALOG);
            }
        });

        findViewById(R.id.btnDatePicker).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MyDialog dialog = new MyDialog();
                dialog.show(getSupportFragmentManager(),
                        MyDialog.KEY_DATEPICKER_DIALOG);
            }
        });

        findViewById(R.id.btnTimePicker).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MyDialog dialog = new MyDialog();
                dialog.show(getSupportFragmentManager(),
                        MyDialog.KEY_TIMEPICKER_DIALOG);
            }
        });

        findViewById(R.id.btnProgress).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MyDialog dialog = new MyDialog();
                dialog.show(getSupportFragmentManager(),
                        MyDialog.KEY_PROGRESS_DIALOG);
            }
        });

        findViewById(R.id.btnCustom).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MyDialog dialog = new MyDialog();
                dialog.show(getSupportFragmentManager(),
                        MyDialog.KEY_CUSTOM_DIALOG);
            }
        });
    }
}
