package com.stechapps.db_project_a;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.stechapps.db_project_a.database.mSQL_helper;
import com.stechapps.db_project_a.database.models.Db_Model;

import java.util.ArrayList;
import java.util.List;

public class InsertActivty extends AppCompatActivity {
    mSQL_helper mhelper;
    Db_Model base_model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_activty);

//        student structure: 10 students
        Intinazie();
        insertData();
    }

    private void insertData() {
        List<String> Attendence=new ArrayList<String>();
        Attendence.add("21-09-2019");
        for (int i=0;i<7;i++){
            Attendence.add("p");
        }
        mhelper.insertData(Attendence);

    }

    private void Intinazie() {
        String[] s1={"Date"," varchar PRIMARY KEY"};
        String[] s2={"ram","shayam","ramu","jamu","kamu","mamu","qumu"};

        List<String[]> sample_data=new ArrayList<>();
        sample_data.add(s1);
        for (int i=0;i<7;i++){
            String[] st={s2[i]," varchar "};
            sample_data.add(st);
            Log.d("Students", "onCreate: added student "+sample_data.get(i)[0]+"\n");

        }

        base_model = new Db_Model("Attendence",sample_data,1);
        mhelper = new mSQL_helper(this,"Student",1,base_model);
        mhelper.CreateTable();

    }

    public void GetData(View view) {
        Cursor data=mhelper.GetAllData(base_model.getTable_name());

        TextView tv=findViewById(R.id.tv2);
        tv.setText("");
        for (int i=0;i<7;i++) {
            tv.append(" ");
            tv.append(base_model.getTable_attr().get(i)[0]);

        }
        while (data.moveToNext()){

            for (int i=0;i<7;i++) {
                tv.append(" ");
                tv.append(data.getString((data.getColumnIndexOrThrow(base_model.getTable_attr().get(i)[0]))));

            }
            tv.append("\n");


        }
        data.close();
    }
}
