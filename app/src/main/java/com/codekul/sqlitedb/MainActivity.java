package com.codekul.sqlitedb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DbHelper helper = new DbHelper(this,"codekul",null,1);

        findViewById(R.id.btnInsert).setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {

                        final EditText edtCarName = (EditText) findViewById(R.id.edtCarName);
                        final EditText edtCarOwner = (EditText) findViewById(R.id.edtCarOwner);

                        SQLiteDatabase sqDb =
                                helper.getWritableDatabase();

                        sqDb.execSQL("insert into carTab values('bmw','android')");

                        ContentValues values = new ContentValues();
                        values.put("caName",
                                edtCarName.getText().toString());
                        values.put("carOwner",
                                edtCarOwner.getText().toString());

                        sqDb.insert("carTab",null,values);
                        sqDb.close();
                    }
                });

        findViewById(R.id.btnDisplay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText edtCarName = (EditText) findViewById(R.id.edtCarName);

                SQLiteDatabase sqDb = helper.getReadableDatabase();

                String tableName = "carTab";
                String[] columns = {"carOwner"};
                String selection = "caName = ?";
                String[] selectionArgs = {edtCarName.getText().toString()};
                String groupBy = null;
                String having = null;
                String orderBy = null;

                Cursor cursor = sqDb.query(tableName,
                        columns,
                        selection,selectionArgs,
                        groupBy,having,orderBy);

                while(cursor.moveToNext()){

                    //String carName = cursor.getString(0);
                    String carOwner = cursor.getString(cursor.getColumnIndex("carOwner"));

                    //Log.i("@codekul","Car Name = "+carName);
                    Log.i("@codekul","Car Owner = "+carOwner);
                }

                sqDb.close();
            }
        });

        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final EditText edtCarName = (EditText) findViewById(R.id.edtCarName);

                SQLiteDatabase sqDb = helper.getWritableDatabase();
                String tableName = "carTab";
                String whereCaluse = "caName = ?";
                String[] whereArgs = {edtCarName.getText().toString()};

                sqDb.delete(tableName,whereCaluse,whereArgs);

                sqDb.close();
            }
        });

        findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                final EditText edtCarName = (EditText) findViewById(R.id.edtCarName);
                final EditText edtCarOwner = (EditText) findViewById(R.id.edtCarOwner);

                // update owner from car name
                SQLiteDatabase sqDb = helper.getWritableDatabase();
                String table = "";
                ContentValues values = new ContentValues();
                String whereClause= "";
                String[] whereArgs = {};

                sqDb.update(table,values,whereClause,whereArgs);

                sqDb.close();
            }
        });
    }
}
