package com.codekul.interactivitycomminication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class NewActivity extends Activity {

    public static final String KEY_BACK_DATA = "back";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Log.i("@codekul","on create");

        Intent responsibleIntent = getIntent();

        final EditText edtBackData = (EditText) findViewById(R.id.edtBackData);

        Bundle bundle = responsibleIntent.getExtras();
        if(bundle != null){
            String name = bundle.getString(MainActivity.KEY_DATA_NAME);
            if(name != null)
                edtBackData.setText(name + new Date());
        }

        final Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent dataTransporter = new Intent();

                //Bundle bundle = new Bundle();
                //bundle.putString("back",edtBackData.getText().toString());

                dataTransporter.putExtra(KEY_BACK_DATA,edtBackData.getText().toString());
                setResult(RESULT_OK,dataTransporter);
                finish();
            }
        });
    }
}
