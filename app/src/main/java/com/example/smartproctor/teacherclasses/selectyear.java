package com.example.smartproctor.teacherclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smartproctor.R;

public class selectyear extends AppCompatActivity {
    Button year1,year2,year3,year4;
    private String year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectyear);
        year1=findViewById(R.id.year1);
        year2=findViewById(R.id.year2);
        year3=findViewById(R.id.year3);
        year4=findViewById(R.id.year4);
        year1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(selectyear.this,Listingstudents.class);
                year="1styear";
                intent.putExtra("year",year);
                startActivity(intent);
            }
        });
        year2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(selectyear.this,Listingstudents.class);
                year="2ndyear";
                intent.putExtra("year",year);
                startActivity(intent);
            }
        });
        year3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(selectyear.this,Listingstudents.class);
                year="3rdyear";
                intent.putExtra("year",year);
                startActivity(intent);
            }
        });
        year4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(selectyear.this,Listingstudents.class);
                year="4thyear";
                intent.putExtra("year",year);
                startActivity(intent);
            }
        });

    }
}