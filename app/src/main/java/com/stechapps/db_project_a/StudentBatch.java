package com.stechapps.db_project_a;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import com.stechapps.db_project_a.database.mSQL_helper;
import com.stechapps.db_project_a.database.models.Db_Model;

import java.util.ArrayList;
import java.util.List;

public class StudentBatch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_batch);
        Bundle b=getIntent().getExtras();

        assert b != null;
        String Table_Name=b.getString("TableName");
        TextView tv=findViewById(R.id.table_head);

        try {


        tv.setText(Table_Name);}
        catch (Exception e){
            tv.setText(Table_Name);
        }

                List<String[]> s=new ArrayList<String[]>();
        String[] p={" Date "," varchar(8) PRIMARY KEY "};
        s.add(p);
        s.addAll(CreateAttributes(Table_Name));

        Db_Model base=new Db_Model(Table_Name,s,1);
        tv.setText(base.getTable_name());
        mSQL_helper mSQL_helper=new mSQL_helper(this,"Student",1,base);
        mSQL_helper.CreateTable();


    }

    private ArrayList<String[]> CreateAttributes(String TableName) {
        ArrayList<String[]> mlist=new ArrayList<>();
        String[] s={" Roll_Number "," varchar(10) PRIMARY KEY"};
        String [] s2={" Name "," varchar(10) "};
        List<String[]> slist=new ArrayList<>();
        slist.add(s);
        slist.add(s2);
        Db_Model base_model=new Db_Model("s1",slist,1);
        mSQL_helper mSQL_helper2;
        mSQL_helper2=new mSQL_helper(this,"Student",1,base_model);
        Cursor data=mSQL_helper2.GetAllData();
        int i=0;

        while (data.moveToNext()){

            String[] s1={data.getString(data.getColumnIndexOrThrow("Roll_Number"))," varchar "};
            mlist.add(s1);


        }
        data.close();
        return mlist;
    }
}
