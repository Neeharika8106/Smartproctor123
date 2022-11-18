package com.example.smartproctor.studentclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartproctor.R;
import com.example.smartproctor.teacherclasses.Selectmarks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class Studentmarks extends AppCompatActivity {
    private Spinner selectyear,sem,exam;
    FirebaseDatabase db;
    FirebaseAuth auth;
    Button b;
    TextView sub2,marks2;
    String regno,year,exam2,sem2,sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentmarks);
        selectyear=findViewById(R.id.selectyear);
        sem=findViewById(R.id.sem);
        exam=findViewById(R.id.exam);
        sub2=findViewById(R.id.subject);
        marks2=findViewById(R.id.marks);
        b=findViewById(R.id.check);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Studentmarks.this, R.array.Years_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectyear.setAdapter(adapter);
        //adding sem items to spinner
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(Studentmarks.this, R.array.Sem_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sem.setAdapter(adapter2);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(Studentmarks.this, R.array.exam_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exam.setAdapter(adapter3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    regno = extras.getString("regno");
                    regno=regno.toUpperCase(Locale.ROOT);
                    //The key argument here must match that used in the other activity
                }
                String s1 = selectyear.getSelectedItem().toString();
                String s2 = sem.getSelectedItem().toString();
                String s3 = exam.getSelectedItem().toString();
                Toast.makeText(Studentmarks.this, "Selected:" + s1 + "," + s2 + "," + s3, Toast.LENGTH_SHORT).show();
                if (s1.equals("1st Year"))
                    year="1styear";
                else if(s1.equals("2nd Year"))
                    year="2ndyear";
                else if(s1.equals("3rd Year"))
                    year="3rdyear";
                else
                    year="4thyear";

                if(s2.equals("1st Sem"))
                    sem2="1stsem";
                else
                    sem2="2ndsem";
                exam2=s3;

                DatabaseReference dbf1 = FirebaseDatabase.getInstance().getReference("Subjects").child(year).child(sem2);
                dbf1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        sub=snapshot.child("Advanced Java Programming").getValue().toString();
                        Toast.makeText(Studentmarks.this,"sub:"+sub+" "+regno,Toast.LENGTH_SHORT).show();
                        marks(sub,regno);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Studentmarks.this,"Data not found",Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }

    private void marks(String sub,String regno) {
        DatabaseReference dbf2 = FirebaseDatabase.getInstance().getReference("Marks").child(year).child(sem2).child(exam2).child(sub);
        dbf2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.child(regno).getValue().toString();
                marks2.setText(s);
                sub2.setText(sub);
                Toast.makeText(Studentmarks.this, " " + s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Studentmarks.this, "Data not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}