package com.codekul.jsondemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new WebTask()
                .execute("http://api.geonames.org/citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=de&username=demo");


        // 2xx - request reached to server and processed successfully
        // 3xx - redirection,
        // 4xx - bad request or resource problem
        // 5xx - internal server error

    }

    private final String getMeJsonData() throws Exception{

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

    private final void parseJsonFromFile(){

        ArrayList<String> dataSet = new ArrayList<>();

        try {
            Log.i("@codekul",getMeJsonData());

            JSONObject jsonObj =
                    new JSONObject(getMeJsonData());

            JSONArray arr = jsonObj.getJSONArray("geonames");
            for(int i = 0 ; i < arr.length() ;i++){

                JSONObject innerObj = arr.getJSONObject(i);

                Log.i("@codekul",innerObj
                        .getString("toponymName"));
                dataSet.add(innerObj.getString("toponymName")+"\n"+innerObj.getString("lat")+" "+innerObj.getString("lng"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        ListView listGeoNames = (ListView)
                findViewById(R.id.listGeoNames);
        ArrayAdapter<String> arrayAdapter = new
                ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataSet);

        listGeoNames.setAdapter(arrayAdapter);
    }

    private final String parseJsonFromWeb(String url) throws Exception{

        //http://api.geonames.org/citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=de&username=demo"
        URL urlConnection = new URL(url);

        HttpURLConnection connection = (HttpURLConnection)
                urlConnection.openConnection();
        connection.setRequestMethod("GET");
        //e6g5j0m2
        Log.i("@codekul","Response Code is - "
                +connection.getResponseCode());

        InputStream connectionStream
                = connection.getInputStream();

        StringBuilder builder =
                new StringBuilder();

        while(true){

            int ch = connectionStream.read();
            if(ch == -1) break;
            else builder.append(""+(char)ch);
        }

        Log.i("@codekul",builder.toString());

        connection.disconnect();
        connectionStream.close();

        return builder.toString();
    }

    private final ArrayList<String> parseAndCreateList(String json)
            throws Exception{

        ArrayList<String> dataSet = new ArrayList<>();

        Log.i("@codekul",json);

        JSONObject jsonObj =
                new JSONObject(json);

        JSONArray arr = jsonObj.getJSONArray("geonames");
        for(int i = 0 ; i < arr.length() ;i++) {

            JSONObject innerObj = arr.getJSONObject(i);

            Log.i("@codekul", innerObj
                    .getString("toponymName"));
            dataSet.add(innerObj.getString("toponymName") + "\n" + innerObj.getString("lat") + " " + innerObj.getString("lng"));
        }

        return  dataSet;
    }

    private final class WebTask extends AsyncTask<String,Void,ArrayList<String>>{

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = ProgressDialog
                    .show(MainActivity.this,"Geo Names","Fetching Geonames");
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {

            ArrayList<String> dataSet = new ArrayList<>();
            try {
                String json = parseJsonFromWeb(params[0]);

                dataSet = parseAndCreateList(json);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return dataSet;
        }

        @Override
        protected void onPostExecute(ArrayList<String> dataSet) {
            super.onPostExecute(dataSet);

            dialog.dismiss();

            ListView listGeoNames = (ListView)
                    findViewById(R.id.listGeoNames);

            ArrayAdapter<String> arrayAdapter = new
                    ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    dataSet);

            listGeoNames.setAdapter(arrayAdapter);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
