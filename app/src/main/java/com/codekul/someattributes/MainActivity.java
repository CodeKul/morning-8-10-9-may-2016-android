package com.codekul.someattributes;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    // http://stackoverflow.com/questions/2025282/difference-between-px-dp-dip-and-sp-on-android
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clicked(View v){
        logIt("clicked");
    }


    public void logIt(String msg){
        Log.i("@codekul",msg);
    }
}
