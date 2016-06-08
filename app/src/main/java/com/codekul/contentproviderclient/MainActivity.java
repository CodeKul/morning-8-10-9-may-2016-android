package com.codekul.contentproviderclient;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = Uri.parse("content://com.codekul.content.mydata");
        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        ContentResolver resolver = getContentResolver();
        Cursor cursor =resolver
                .query(uri,
                        projection,
                        selection,
                        selectionArgs,
                        sortOrder);

        while(cursor.moveToNext()){

            String myName = cursor.getString(
                    cursor.getColumnIndex("myName"));
            String myNum = cursor.getString(
                    cursor.getColumnIndex("myNumber"));

            Log.i("@codekul","MyName - "+myName +" My Num - "+myNum);
        }
    }
}
