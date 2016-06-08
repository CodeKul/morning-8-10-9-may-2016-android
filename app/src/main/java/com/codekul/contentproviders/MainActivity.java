package com.codekul.contentproviders;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listContacts = (ListView) findViewById(R.id.listContacts);

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {};
        String selection = "";
        String[] selectionArgs = {};
        String sortOrder = "";

        ContentResolver resolver = getContentResolver();
        Cursor cursor =resolver
                .query(uri,
                        projection,
                        selection,
                        selectionArgs,
                        sortOrder);

        final DbHelper helper = new DbHelper(this,"myDb",null,1);

        ArrayList<String> dataSet = new ArrayList<>();

        SQLiteDatabase sqDb = helper.getWritableDatabase();
        while(cursor.moveToNext()){

            String name = cursor.getString(
                    cursor.getColumnIndex(ContactsContract
                            .CommonDataKinds
                            .Phone
                            .DISPLAY_NAME));

            String number = cursor.getString(cursor.getColumnIndex(ContactsContract
                    .CommonDataKinds
                    .Phone
                    .NUMBER));

            dataSet.add(name +"\n"+number);

            ContentValues values = new ContentValues();
            values.put("myName",name);
            values.put("myNumber",number);

            sqDb.insert("myTab",null,values);
        }
        sqDb.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataSet);
        listContacts.setAdapter(adapter);
    }
}
