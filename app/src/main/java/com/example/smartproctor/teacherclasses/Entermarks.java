package com.example.smartproctor.teacherclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.smartproctor.Loginpage;
import com.example.smartproctor.Marks;
import com.example.smartproctor.Newuser;
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

public class Entermarks extends AppCompatActivity {
    Spinner rollno;
    EditText marks;
    Button save,discard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entermarks);
       rollno=findViewById(R.id.rollno);
       marks=findViewById(R.id.marks);
       save=findViewById(R.id.save);
       discard=findViewById(R.id.discard);
        FirebaseUser cu= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference db= FirebaseDatabase.getInstance().getReference().child("list2").child("3rdyear");
        ArrayList<String> list1=new ArrayList<String>();
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()) {
                    String s = snapshot1.getValue().toString();
                    list1.add(s);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Entermarks.this, android.R.layout.simple_list_item_1, list1);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                rollno.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String m=marks.getText().toString();
               if(m.isEmpty())
                   Toast.makeText(Entermarks.this,"Enter marks!",Toast.LENGTH_SHORT).show();
               else
               {   String rn=rollno.getSelectedItem().toString();

                   Marks mark=new Marks(m,rn);
                   FirebaseDatabase.getInstance().getReference("Marks").child("3rdyear").child("1stsem").child("Assignment-I").child("20CS5404B").child(rn).setValue(m)
                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(Entermarks.this,"Saved!",Toast.LENGTH_LONG).show();

                           }
                           else{
                               Toast.makeText(Entermarks.this,"Something went wrong!",Toast.LENGTH_LONG).show();
                           }
                       }
                   });
               }
           }
       });
    }
}