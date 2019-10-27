package com.stechapps.db_project_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.stechapps.db_project_a.database.mSQL_helper;
import com.stechapps.db_project_a.database.models.Db_Model;

import java.util.ArrayList;
import java.util.List;

public class batches extends AppCompatActivity {
mSQL_helper mhelper;
EditText edtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batches);
        Db_Model db_model;
        List<String[]> sample_data=new ArrayList<>();
        String[] s={ " batches_name "," varchar "};
        sample_data.add(s);
        db_model=new Db_Model("BatchName",sample_data,1);
        mhelper = new mSQL_helper(this,"Student",1,db_model);
        mhelper.CreateTable();
        CreateList();
        edtv=findViewById(R.id.ed_batch_name);
    }

    private void CreateList() {
        Cursor c=mhelper.GetAllData();
        ArrayList a=new ArrayList();

        ListView ls=findViewById(R.id.lv_batches);

        while(c.moveToNext()){
          a.add(c.getString(c.getColumnIndexOrThrow("batches_name")));



        }

        ListAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, a);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor c=mhelper.GetRow(position);
                c.moveToNext();
                Intent i=new Intent(batches.this,StudentBatch.class);
                Bundle b=new Bundle();
                String s=c.getString(1);
                c.close();
                b.putString("TableName",s);
                startActivity(i,b);
            }
        });
        ls.setAdapter(adapter);
        c.close();
    }

    public void newBatch(View view) {
        List<String> ls=new ArrayList<String>();

        ls.add(edtv.getText().toString());
        mhelper.insertData(ls);
        UpdateList();

    }

    private void UpdateList() {
        Cursor c=mhelper.GetAllData();
        ArrayList a=new ArrayList();

        ListView ls=findViewById(R.id.lv_batches);

        while(c.moveToNext()){
            a.add(c.getString(c.getColumnIndexOrThrow("batches_name")));



        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, a);
        ls.setAdapter(adapter);
        c.close();
    }
}
