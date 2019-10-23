package com.stechapps.db_project_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.stechapps.db_project_a.database.mSQL_helper;
import com.stechapps.db_project_a.database.models.Db_Model;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Db_Model  dbs;
    mSQL_helper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1=findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1();
            }
        });



    }

    public void button1() {
        Toast.makeText(this, "Button 1 clicked", Toast.LENGTH_SHORT).show();
        String[] s1={"COLUMN1"};
        String[] s2={"COLUMN2"};
        List<String[]> l= new ArrayList<String[]>();
        l.add(s1);
        l.add(s2);

        dbs = new Db_Model("test_table_1",l,1);
        Log.d("Main Activity++", "button1: "+dbs.getTable_name());
        helper=new mSQL_helper(this,"DATABASE_TEST",1,dbs);
        helper.CreateTable();
    }

    public void button2(View view) {

        helper.dropTable(dbs.getTable_name());
        Toast.makeText(this,"TBALE DROPPED"+dbs.getTable_name(),Toast.LENGTH_SHORT).show();
    }

    public void button3(View view) {
        List<String> s=new ArrayList<String>();
        s.add("sample1");
        s.add("sample2");
        helper.insertData(s);
    }

    public void nutton3(View view) {
        Cursor data=helper.GetAllData(dbs.getTable_name());
        int i=0;
        TextView tv=findViewById(R.id.tv1);
        while (data.moveToNext()){
            Toast.makeText(this, ""+data, Toast.LENGTH_SHORT).show();
            tv.append(data.getString((data.getColumnIndexOrThrow(dbs.getTable_attr().get(0)[0]))));
            tv.append(" "+data.getString((data.getColumnIndexOrThrow(dbs.getTable_attr().get(1)[0]))));
            tv.append("\n");
            i++;

        }
        data.close();
    }

    public void InsertActivity(View view) {
        startActivity(new Intent(this,InsertActivty.class));
    }
}
