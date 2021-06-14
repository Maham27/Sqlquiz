package com.example.sqlquizmaham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 EditText etname,etid,etnumber,etdeparment;
 Button btnsaveddata,btninfo,btnupd,btndel;
 TextView tvdata;
 employeesdb eDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }
    public void init()
    {
        etname=findViewById(R.id.etname);
        etnumber=findViewById(R.id.etnumber);
        etdeparment=findViewById(R.id.etdepartment);
        btnsaveddata=findViewById(R.id.btnsavedata);
        btninfo=findViewById(R.id.btninfo);
        btnupd=findViewById(R.id.btnupd);
        btndel=findViewById(R.id.btndel);

    }
    public void Clear()
    {
        etname.setText("");
        etdeparment.setText("");
        etnumber.setText("");

    }


    public void btnsaveddata(View v)
    {
        String name = etname.getText().toString().trim();
        String number = etnumber.getText().toString().trim();
        String department = etdeparment.getText().toString().trim();

        employeesdb eDB = new employeesdb(this);
        eDB.open();
        eDB.createentry(name,number,department);
        eDB.close();
    }
    public void btnshowinfo(View v)
    {
        startActivity(new Intent(MainActivity.this,showinfo.class));
    }
    public void btnupdatedata(View v)
    {
        employeesdb eDB = new employeesdb(this);
        eDB.open();
        long totalUpdated =  eDB.updated("2", "maham", "03336879175","cs");
        Toast.makeText(this, ""+totalUpdated, Toast.LENGTH_SHORT).show();
        eDB.close();
    }

    public void btnDeldata(View v)
    {
        employeesdb eDB = new employeesdb(this);
        eDB.open();
        eDB.deleted("4");
        eDB.close();
    }



}