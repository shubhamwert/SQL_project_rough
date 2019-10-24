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
    String TAG="TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_activty);

//        student structure: 10 students
        Intinazie();
        ArrayList<String> s=new ArrayList<>();
        s.add("SHUBHAM");
        s.add("AMANDEEP");
        insertData(s);
    }

    private void insertData(ArrayList<String> s) {
        List<String> Attendence=new ArrayList<String>();
        Attendence.add("21-09-2019");
        List<String> l=new ArrayList<>();
        for (int i=0;i<mhelper.getDb_model().getTable_attr().size();i++){
            l.add(mhelper.getDb_model().getTable_attr().get(i)[0]);
        }
        Log.d(TAG, "insertData: "+l.indexOf("SHUBHAM"));
        for (int i=0;i<s.size();i++){
            int pos=l.indexOf(s.get(i));
            if (pos!=-1){
                
            }else{
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            }
        }
        mhelper.insertData(Attendence);

    }

    private void Intinazie() {
        String[] s1={"Date"," varchar PRIMARY KEY"};
        String[] s2={"SHUBHAM","KSHITIJ","AMANDEEP","VINEET","SOOD"};

        List<String[]> sample_data=new ArrayList<>();
        sample_data.add(s1);
        for (int i=0;i<s2.length;i++){
            String[] st={s2[i]," varchar(20)"};
            sample_data.add(st);
            Log.d("Students", "onCreate: added student "+sample_data.get(i)[0]+"\n");

        }

        base_model = new Db_Model(" Attendence ",sample_data,1);
        mhelper = new mSQL_helper(this,"Student",1,base_model);
        mhelper.dropTable();
        mhelper.CreateTable();

    }

    public void GetData(View view) {
        Cursor data=mhelper.GetAllData();

        TextView tv=findViewById(R.id.tv2);
        tv.setText("");
        for (int i=0;i<mhelper.getDb_model().getTable_attr().size();i++) {
            tv.append(" ");
            tv.append(mhelper.getDb_model().getTable_attr().get(i)[0]);
        }
        tv.append("\n");

        while (data.moveToNext()){

            for (int i=0;i<mhelper.getDb_model().getTable_attr().size();i++) {
                if (data.getString(i)!=null){
                tv.append(" ");
                tv.append(data.getString(i));}
                else{
                    tv.append("NULL");
                    tv.append(" ");

                }
            }
            tv.append("\n");


        }
        data.close();
    }
}
