package com.example.smartproctor.teacherclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.smartproctor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Enterattend extends AppCompatActivity {
    Spinner rollno,subject;
    EditText present,total;
    Button save,discard;
    String year,selecteddate,sem,sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterattend);
        rollno=findViewById(R.id.rollno);
        present=findViewById(R.id.present);
        total=findViewById(R.id.total);
        save=findViewById(R.id.save);
        discard=findViewById(R.id.discard);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            year = extras.getString("year");
            selecteddate=extras.getString("date");
            sem= extras.getString("sem");
            //The key argument here must match that used in the other activity
        }
        if (year.equals("1st Year"))
            year="1styear";
        else if(year.equals("2nd Year"))
            year="2ndyear";
        else if(year.equals("3rd Year"))
            year="3rdyear";
        else
            year="4thyear";
        if(sem.equals("1st Sem"))
            sem="1stsem";
        else
            sem="2ndsem";


        FirebaseUser cu= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference db= FirebaseDatabase.getInstance().getReference().child("list2").child(year);
        ArrayList<String> list1=new ArrayList<String>();

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1:snapshot.getChildren()) {
                    String s = snapshot1.getValue().toString();
                    list1.add(s);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Enterattend.this, android.R.layout.simple_list_item_1, list1);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                rollno.setAdapter(adapter);
                subject();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String total2=total.getText().toString();
                String present2=present.getText().toString();

                               FirebaseDatabase.getInstance().getReference("Attendance").child(year).child(sem).child(sub).child("Total Hours").setValue(total2)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Enterattend.this,"Saved!",Toast.LENGTH_LONG).show();

                                }
                                else{
                                    Toast.makeText(Enterattend.this,"Something went wrong!",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                FirebaseDatabase.getInstance().getReference("Attendance").child(year).child(sem).child(sub).child("Present Hours").setValue(present2)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Enterattend.this,"Saved!",Toast.LENGTH_LONG).show();

                                }
                                else{
                                    Toast.makeText(Enterattend.this,"Something went wrong!",Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });

    }
    public void subject()
    {   Toast.makeText(Enterattend.this,""+year+""+sem,Toast.LENGTH_SHORT).show();
        DatabaseReference db2= FirebaseDatabase.getInstance().getReference("Subjects").child(year).child(sem);
        ArrayList<String> list2=new ArrayList<String>();
        db2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()) {
                    String s = snapshot1.getValue().toString();
                    Toast.makeText(Enterattend.this,""+s,Toast.LENGTH_SHORT).show();
                    list2.add(s);
                }
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Enterattend.this, android.R.layout.simple_list_item_1, list2);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subject.setAdapter(adapter2);
                sub=subject.getSelectedItem().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}