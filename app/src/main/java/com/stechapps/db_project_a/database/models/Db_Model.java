package com.stechapps.db_project_a.database.models;

import android.text.TextUtils;
import android.util.Log;

import java.util.List;

public class Db_Model {
    private  String table_name;
    private List<String[]> table_attr;
    private int ID;
    private Db_Model() {}

    public Db_Model(String table_name, List<String[]> table_attr, int ID) {
        this.table_name = table_name;
        this.table_attr = table_attr;
        this.ID = ID;
        CreateTable();
    }

    public String getTable_name() {
        return table_name;
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
            createTable.append(TextUtils.join(" ", table_attr.get(i)));
            if (i != table_attr.size()-1)
                createTable.append(" , ");
        }
        createTable.append(")");
        Log.d("Statement", "CreateTable: "+createTable.toString());
        return createTable.toString();

    }
    public String getAllData(){
        return " SELECT * FROM "+table_name;
    }
}
