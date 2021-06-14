package com.example.sqlquizmaham;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class showinfo extends AppCompatActivity {
TextView tvinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showinfo);
        tvinfo = findViewById(R.id.tvinfo);
        employeesdb eDB = new employeesdb(this);
        eDB.open();
        tvinfo.setText(eDB.getData());
        eDB.close();
    }
}