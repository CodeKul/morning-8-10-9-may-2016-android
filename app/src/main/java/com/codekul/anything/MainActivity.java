package com.codekul.anything;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private EditText edtName;
    private Button btnOkay;
    private TextView textState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        edtName = (EditText) findViewById(R.id.edtName);

        btnOkay = (Button) findViewById(R.id.btnOkay);
        btnOkay.setOnClickListener(new Click());

        /*btnOkay.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });*/

        textState = (TextView) findViewById(R.id.textState);
    }

    private class Click implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            if(v.getId() == btnOkay.getId()){

                String text = edtName.getText().toString();

                textState.setText(text.toUpperCase());
            }
        }
    }
}
