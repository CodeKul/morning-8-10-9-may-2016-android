package com.codekul.asynctask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                new MyTask().execute(/*new String[]{"10"}*/ /*Params*/);
            }
        });
    }

    private void assignForLoop(){

        final TextView textView = (TextView)findViewById(R.id.textView);

        new Thread(new Runnable() {
            @Override
            public void run() { // worker thread

                for (int i = 0; i < 10; i++){

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //textView.setText(""+i);
                }
            }
        }).start();
    }

    private final class MyTask extends AsyncTask</*Params*/String,/*Progress*/Integer,/*Result*/Double>{

        private ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // UI thread

            dialog = ProgressDialog.show(MainActivity.this,"Title","Message");
        }

        @Override
        protected Double/*Result*/ doInBackground(String... params/*Params*/) {
            // worker thread

            for (int i = 0; i < 10; i++){

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //textView.setText(""+i);
                publishProgress(new Integer[]{i} /*Progress*/);
            }
            return 12.5d;
        }

        @Override
        protected void onPostExecute(Double aDouble/*Result*/) {
            super.onPostExecute(aDouble);

            // UI thread

            dialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values/*Progress*/) {
            super.onProgressUpdate(values);

            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(""+values[0]);
            // UI thread
        }
    }
}
