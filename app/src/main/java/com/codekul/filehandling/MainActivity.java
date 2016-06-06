package com.codekul.filehandling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnOpenFileOutput).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // /data/data/<your-package>/files
                // /data/data/<your-package>/shared_prefs

                try {
                    FileOutputStream fos =
                            openFileOutput("my.txt",MODE_PRIVATE);
                    fos.write("This is android internal storage".getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btnOpenFileInput).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                try {
                    FileInputStream fis = openFileInput("my.txt");
                    StringBuilder builder = new StringBuilder();
                    while(true){

                        int ch = fis.read();
                        if(ch == -1) break;
                        else builder.append((char)ch);
                    }
                    Log.i("@codekul",builder.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btnFileList).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

//                String []listFiliNames = fileList();
//
//                for (String filiName : listFiliNames) {
//                    Log.i("@codekul",filiName);
//                }

                File file = new File("/");
                File[]files = file.listFiles();

                ArrayList<String> dataSet = new ArrayList<String>();
                for (File f : files) {
                    dataSet.add(f.isDirectory() ? " * "+f.getName(): " - "+f.getName());
                }

                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,dataSet);
                ((ListView)findViewById(R.id.listFiles)).setAdapter(adapter);
            }
        });

        findViewById(R.id.btnGetFilesDir).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                // /data/data/<package/files
                Log.i("@codekul","Get Files Dir - "+
                        getFilesDir().getPath());

                File file = new File(getFilesDir(),"m1.txt");
                try {
                    Log.i("@codekul",file.createNewFile() ? "File created successfully ..":"Problem in creating file");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btnGetExternalFilesDir).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                File file = new File(getExternalFilesDir("my"),"a.txt");
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write("Hello world".getBytes());

                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
