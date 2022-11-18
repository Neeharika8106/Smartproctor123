package com.example.smartproctor.teacherclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.smartproctor.R;

public class Proctorstart extends AppCompatActivity {
    private ImageView add,view,remove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proctorstart);
        add=findViewById(R.id.addstudent);
        view=findViewById(R.id.viewstudent);
        remove=findViewById(R.id.remove);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Proctorstart.this,selectyear.class));
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s="add";
                Intent intent=new Intent(Proctorstart.this,addstudent.class);
                intent.putExtra("msg",s);
                startActivity(intent);
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s="remove";
                Intent intent=new Intent(Proctorstart.this,addstudent.class);
                intent.putExtra("msg",s);
                startActivity(intent);
            }
        });
    }
}