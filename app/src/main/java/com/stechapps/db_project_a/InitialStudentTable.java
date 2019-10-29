package com.stechapps.db_project_a;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.stechapps.db_project_a.database.mSQL_helper;
import com.stechapps.db_project_a.database.models.Db_Model;

import java.util.ArrayList;
import java.util.List;

public class InitialStudentTable extends AppCompatActivity {
ArrayList<String[]> ls;
    EditText ed;
    private EditText ed2;
    mSQL_helper mSQL_helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_student_table);
        Bundle b=getIntent().getExtras();
        String s1=b.getString("TableName");
        String[] s={" Roll_Number "," varchar(10) PRIMARY KEY"};
        String [] s2={" Name "," varchar(10) "};
        List<String[]> slist=new ArrayList<>();
        slist.add(s);
        slist.add(s2);
        Db_Model base_model=new Db_Model("s1",slist,1);
        mSQL_helper=new mSQL_helper(this,"Student",1,base_model);
        mSQL_helper.CreateTable();
        Button button=findViewById(R.id.next);
        ls=new ArrayList<>();
        ed = findViewById(R.id.ed_name);
        ed2=findViewById(R.id.roll);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!ed.getText().toString().equals("")) {
                    String[] temp={ed2.getText().toString(),ed.getText().toString()};
                    ls.add(temp);
                }
                else{
                    Toast.makeText(InitialStudentTable.this, "Enter a Name", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Button b2=findViewById(R.id.Submit);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < ls.size(); i++) {
                    ArrayList<String> s=new ArrayList<>();
                    s.add(ls.get(i)[0]);
                    s.add(ls.get(i)[1]);
                    mSQL_helper.insertData(s);
                }
                Cursor data=mSQL_helper.GetAllData();
                int i=0;
                TextView tv=findViewById(R.id.stduentList);
                tv.setText("");
                while (data.moveToNext()){
                    tv.append(data.getString(data.getColumnIndexOrThrow("Roll_Number")));
                    tv.append("                  ");
                    tv.append(data.getString(data.getColumnIndexOrThrow("Name")));
                    tv.append("\n");
                    i++;

                }
                data.close();
            }
        });
    }
}
