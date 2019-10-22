package com.stechapps.db_project_a.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


import com.stechapps.db_project_a.database.models.Db_Model;

import java.util.List;

public class mSQL_helper extends SQLiteOpenHelper {
    Cursor mCursor;
    private int DATABASE_VERSION=1;
    private Db_Model db_model;
    private String DATABASE_NAME="TEST_DATABASE";

    public mSQL_helper(Context context,String DATABASE_NAME,int DATABASE_VERSION,Db_Model db_model){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.DATABASE_VERSION=DATABASE_VERSION;
        this.DATABASE_NAME=DATABASE_NAME;
        this.db_model=db_model;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(db_model.CreateTable());
        Log.d("TABLE CREATED", "onCreate: "+db_model.CreateTable()+"  <<<<<<<<<<<<<<<<<<table name>>>>>>>"+db_model.getTable_name());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + db_model.getTable_name());
        onCreate(db);
    }

    public long insertData(List<String> data){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(db_model.getTable_attr().get(0)[0],data.get(0));
        values.put(db_model.getTable_attr().get(1)[0],data.get(1));

        long id=db.insert(db_model.getTable_name(),null,values);
        Log.d("ID+++++++++++++++++++++", "insertData: "+id);
        db.close();
        return id;

    }
    public void dropTable(String TableName){
        SQLiteDatabase db=this.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + db_model.getTable_name());

        Log.d("DROP TABLE ______", "dropTable: ");

    }
    public Cursor GetAllData(String TableName){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + db_model.getTable_name(), null);
        return cursor;

    }
    public void CreateTable(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + db_model.getTable_name());

        db.execSQL(db_model.CreateTable());
        Log.d("TABLE CREATED", "CreateTAble: "+db_model.CreateTable()+"  <<<<<<<<<<<<<<<<<<table name>>>>>>>"+db_model.getTable_name());

    }
}
