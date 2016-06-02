package com.codekul.assestsandresources;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_STR_TIME = "time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getWindow().setBackgroundDrawableResource(R.drawable.my);

        int pureWhite = getResources().getColor(R.color.pureWhite);
        pureWhite = ContextCompat.getColor(this,R.color.pureWhite);

        float btnLeftPading = getResources().getDimension(R.dimen.btnLeftPasdding);

        mt("OnCreate");

        findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                TextView text = (TextView) findViewById(R.id.textStatus);
                text.setText(""+System.currentTimeMillis());
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        mt("OnRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mt("OnDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(KEY_STR_TIME,((TextView)findViewById(R.id.textStatus)).getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null){
            ((TextView)findViewById(R.id.textStatus)).setText(savedInstanceState.getString(KEY_STR_TIME));
        }
    }

    private void mt(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
