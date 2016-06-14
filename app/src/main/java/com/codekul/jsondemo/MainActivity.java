package com.codekul.jsondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Log.i("@codekul",getMeJsonData());

            JSONObject jsonObj =
                    new JSONObject(getMeJsonData());

            JSONArray arr = jsonObj.getJSONArray("geonames");
            for(int i = 0 ; i < arr.length() ;i++){

                JSONObject innerObj = arr.getJSONObject(i);

                Log.i("@codekul",innerObj.getString("toponymName"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private String getMeJsonData() throws Exception{

        InputStream is = getAssets()
                .open("my.json");

        StringBuilder builder =
                new StringBuilder();

        while(true){

            int ch = is.read();
            if(ch == -1) break;
            else builder.append(""+(char)ch);
        }

        return builder.toString();
    }
}
