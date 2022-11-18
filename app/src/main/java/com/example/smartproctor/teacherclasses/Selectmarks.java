package com.example.smartproctor.teacherclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.smartproctor.R;

public class Selectmarks extends AppCompatActivity {
    private Spinner selectyear,sem;
    Button ass1,ass2,ses1,ses2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectmarks);
        selectyear=findViewById(R.id.selectyear);
        sem=findViewById(R.id.sem);
        ass1=findViewById(R.id.ass1);
        ass2=findViewById(R.id.ass2);
        ses1=findViewById(R.id.ses1);
        ses2=findViewById(R.id.ses2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Selectmarks.this, R.array.Years_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectyear.setAdapter(adapter);
        //adding sem items to spinner
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(Selectmarks.this, R.array.Sem_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sem.setAdapter(adapter2);
        ass1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(Selectmarks.this,Entermarks.class));
            }
        });


    }
}