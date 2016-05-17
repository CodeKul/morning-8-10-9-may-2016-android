package com.codekul.interactivitycomminication;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    public static final int REQUEST_CODE_NEW = 456;
    public static final String KEY_DATA_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button btnOkay = (Button) findViewById(R.id.btnOkay);
        btnOkay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                EditText edtName = (EditText) findViewById(R.id.edtName);

                Intent intent = new Intent(MainActivity.this,NewActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString(KEY_DATA_NAME,edtName.getText().toString());
                intent.putExtras(bundle);

                //startActivity(intent);

                startActivityForResult(intent,REQUEST_CODE_NEW);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 456){

            if(resultCode == RESULT_OK){

                Bundle bundle = data.getExtras();
                String nameData = bundle.getString(NewActivity.KEY_BACK_DATA);

                EditText edtName = (EditText) findViewById(R.id.edtName);
                edtName.setText(nameData);
            }
        }
    }
}
