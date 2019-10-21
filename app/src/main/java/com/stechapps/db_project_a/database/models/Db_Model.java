package com.stechapps.db_project_a.database.models;

import android.text.TextUtils;
import android.util.Log;

import java.util.List;

public class Db_Model {
    private String table_name;
    private List<String[]> table_attr;
    private int ID;
    private String TAG="******************************************ERROR IN mSQL HElper******************************";
    private Db_Model() {

    }

    public Db_Model(String table_name, List<String[]> table_attr, int ID) {
        this.table_name = table_name;
        this.table_attr = table_attr;
        this.ID = ID;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public List<String[]> getTable_attr() {
        return table_attr;
    }

    public void setTable_attr(List<String[]> table_attr) {
        this.table_attr = table_attr;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public String CreateTable(){
        StringBuilder createTable= new StringBuilder("CREATE TABLE " + table_name + "(");
        for (int i=0;i<table_attr.size();i++){
            Log.d(TAG, "CreateTable: ");
            createTable.append(TextUtils.join(" ", table_attr.get(i)));
            if (i != table_attr.size()-1)
                createTable.append(" , ");
        }
        createTable.append(")");
        Log.d(TAG, "table val: "+createTable+")");
        return createTable.toString();

    }
}
