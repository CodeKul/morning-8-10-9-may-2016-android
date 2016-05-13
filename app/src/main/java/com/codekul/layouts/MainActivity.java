package com.codekul.layouts;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button btnCenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Click click = new Click();
        btnCenter = (Button) findViewById(R.id.btnCenter);
        btnCenter.setOnClickListener(click);
    }

    private class Click implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId() == btnCenter.getId()){

            }
        }
    }
}
