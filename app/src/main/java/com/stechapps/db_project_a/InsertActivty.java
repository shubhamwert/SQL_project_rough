package com.stechapps.db_project_a;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
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

public class InsertActivty extends AppCompatActivity {
    mSQL_helper mhelper;
    Db_Model base_model;
    String TAG="TEST";
    int i=1;
    TextView tv,tvo;
    ArrayList<String> Atten;
    static int mkl=0;
    int j;
    boolean newEdit=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_activty);

        Intinazie();
        Button bt=findViewById(R.id.next);
        Button bt2=findViewById(R.id.abs);
        tvo=findViewById(R.id.tv_test);
        tv = findViewById(R.id.test_field);
        Atten = new ArrayList<String>();

        Atten.add(" "+mkl);
        mkl++;
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateList(true);
                tvo.setText(mhelper.getDb_model().getTable_attr().get(i)[0]);
                i++;

            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateList(false);
                tvo.setText(mhelper.getDb_model().getTable_attr().get(i)[0]);
                i++;

            }
        });


    }
    public void previous(View view) {
        j = i;
        i--;
        newEdit=true;
        tvo.setText(mhelper.getDb_model().getTable_attr().get(i)[0]);


    }
    public void UpdateList(boolean c){
    if (!newEdit){
        if (c){
            Atten.add("P");

                    }
        else{
            Atten.add("A");
        }
        tv.setText(Atten.toString());
    }
    else if(newEdit){
        if (c){
            Atten.set(i,"P");

        }
        else{
            Atten.set(i,"A");
        }
        i=j;
        tvo.setText(mhelper.getDb_model().getTable_attr().get(i)[0]);
        tv.setText(Atten.toString());

        newEdit=false;

        }

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

    public void btup(View view) {
        mhelper.insertData(Atten);
    }


    public void dropTable(View view) {
        mhelper.dropTable();
    }

    public void createIt(View view) {
        mhelper.CreateTable();
    }
}
