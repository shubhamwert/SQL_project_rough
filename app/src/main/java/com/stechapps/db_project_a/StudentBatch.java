package com.stechapps.db_project_a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.stechapps.db_project_a.database.models.Db_Model;

import java.util.ArrayList;
import java.util.List;

public class StudentBatch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_batch);
        Bundle b=getIntent().getExtras();
        String Table_Name=b.getString("TableName");
        List<String[]> s=new ArrayList<String[]>();
        String[] p={" Date "," varchar(8) PRIMARY KEY "};
        s.add(p);

        Db_Model base=new Db_Model();
    }
}
