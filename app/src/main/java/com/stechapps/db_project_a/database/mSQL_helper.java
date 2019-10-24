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
    public mSQL_helper(Context context,String DATABASE_NAME){
        super(context, DATABASE_NAME, null, 2);
        DATABASE_VERSION=2;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(db_model.CreateTable());
        Log.d("TABLE CREATED", "onCreate: "+db_model.CreateTable()+"  <<<<<<<<<<<<<<<<<<table name>>>>>>>"+db_model.getTable_name());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//        db.execSQL("DROP TABLE IF EXISTS " + db_model.getTable_name());
        onCreate(db);
    }

    public Db_Model getDb_model() {
        return db_model;
    }

    public long insertData(List<String> data){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        for (int i=0;i<data.size();i++) {
            values.put(db_model.getTable_attr().get(i)[0], data.get(i));
        }
        long id=db.insert(db_model.getTable_name(),null,values);
        Log.d("ID==", "insertData: "+id);
        db.close();
        return id;

    }
    public void dropTable(){
        SQLiteDatabase db=this.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + db_model.getTable_name());

        Log.d("DROP TABLE ______", "dropTable: "+db_model.getTable_name());

    }
    public Cursor GetAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + db_model.getTable_name(), null);
        return cursor;

    }
    public void CreateTable(){
        SQLiteDatabase db=this.getWritableDatabase();
        try {


        db.execSQL(db_model.CreateTable());
            Log.d("TABLE CREATED", "CreateTAble: "+db_model.CreateTable()+"  <<<<<<<<<<<<<<<<<<table name>>>>>>>"+db_model.getTable_name());
        }
        catch (Exception e){
            Log.d("TABLE ALREADY EXIST", "CreateTable: "+e);
        }
        Log.d("QUERY", "CreateTable:<<<<<<<<<<<<<<<<<<<<<<<<<<+"+db_model.CreateTable()+">>>>>>>>>>>>>>>>>>>>>> ");

    }
    public Cursor GetColumn(String ColumnName){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("SELECT + "+ColumnName+" FROM "+db_model.getTable_name(),null);


    }
}
