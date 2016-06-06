package com.codekul.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnSave).setOnClickListener(new
                View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        SharedPreferences prefs =
                                getSharedPreferences("myPrefs",MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("key_my_string","codekul");
                        editor.putInt("key_my_int",2);
                        editor.putFloat("key_my_double",2.5f);
                        editor.commit();
                    }
                });

        findViewById(R.id.btnDisplay).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                SharedPreferences prefs = getSharedPreferences("myPrefs",MODE_PRIVATE);
                String myString = prefs.getString("key_my_string","default");
                int myInt = prefs.getInt("key_my_int",-2);
                float myFloat =prefs.getFloat("key_my_double",-5f);

                Log.i("@codekul","String - "+myString);
                Log.i("@codekul","Int - "+myInt);
                Log.i("@codekul","Float - "+myFloat);
            }
        });
    }
}
