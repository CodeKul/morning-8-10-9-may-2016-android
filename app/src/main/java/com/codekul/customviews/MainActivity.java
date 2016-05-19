package com.codekul.customviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                float cx = Float.parseFloat(((EditText)findViewById(R.id.edtCx)).getText().toString());
                float cy = Float.parseFloat(((EditText)findViewById(R.id.edtCy)).getText().toString());
                float rad = Float.parseFloat(((EditText)findViewById(R.id.edtRad)).getText().toString());

                MyView myView = (MyView) findViewById(R.id.myView);
                myView.changeCircle(cx, cy, rad);
            }
        });
+
    }

    private void uiViaCode(){
        MyView view = new MyView(this);
        setContentView(view);
    }
}
