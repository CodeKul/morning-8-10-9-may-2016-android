package com.codekul.intentanfilters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class SportsACtivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_activity);

        Intent responsibleIntent = getIntent();

        Log.i("@codekul","Action - "+responsibleIntent.getAction());
        for(String cat : responsibleIntent.getCategories()){
            Log.i("@codekul","Category - "+cat);
        }
        Log.i("@codekul",responsibleIntent.getData().toString());;

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(responsibleIntent.getData().toString());

    }
}
