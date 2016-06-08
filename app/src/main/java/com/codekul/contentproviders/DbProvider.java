package com.codekul.contentproviders;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by aniruddha on 8/6/16.
 */
public class DbProvider extends ContentProvider{
    @Override
    public boolean onCreate() {

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        DbHelper helper = new DbHelper(getContext(),"myDb",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query("myTab",projection,selection,selectionArgs,null,null,sortOrder);

        helper = null;
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public synchronized Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public synchronized int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public synchronized int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
