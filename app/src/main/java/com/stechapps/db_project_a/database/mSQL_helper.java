package com.stechapps.db_project_a.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class mSQL_helper extends SQLiteOpenHelper {
    Cursor mCursor;
    private int DATABASE_VERSION=1;
    private String DATABASE_NAME="TEST_DATABASE";

    public mSQL_helper(Context context,String DATABASE_NAME,int DATABASE_VERSION){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.DATABASE_VERSION=DATABASE_VERSION;
        this.DATABASE_NAME=DATABASE_NAME;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
