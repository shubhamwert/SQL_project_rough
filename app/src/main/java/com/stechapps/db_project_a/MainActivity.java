package com.stechapps.db_project_a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.stechapps.db_project_a.database.models.Db_Model;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] s1={"COLUMN1"};
        String[] s2={"COLUMN2"};
        List<String[]> l= new ArrayList<String[]>();
        l.add(s1);
        l.add(s2);
        Db_Model dbs=new Db_Model("test_table_1",l,1);
        Log.d("MAIN ACTIVITY", "onCreate: "+dbs.CreateTable());
    }
}
