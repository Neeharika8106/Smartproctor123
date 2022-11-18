package com.example.smartproctor.teacherclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.smartproctor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Overalldata extends AppCompatActivity {
    ListView listView1,listView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overalldata);
        listView1=findViewById(R.id.rollno);
        listView2=findViewById(R.id.percentage);
        FirebaseUser cu= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference db= FirebaseDatabase.getInstance().getReference().child("Attendance2");
        ArrayList<String> list1=new ArrayList<String>();
        ArrayList<String> list2=new ArrayList<String>();
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()) {
                    String s = snapshot1.getValue().toString();
                    s=s+"%";
                    String s2 = snapshot1.getKey();
                    list1.add(s);
                    list2.add(s2);

                }
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(Overalldata.this, android.R.layout.simple_list_item_1, list1);
                listView2.setAdapter(adapter3);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Overalldata.this, android.R.layout.simple_list_item_1, list2);
                listView1.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}