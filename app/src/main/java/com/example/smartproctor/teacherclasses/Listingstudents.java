package com.example.smartproctor.teacherclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartproctor.R;
import com.example.smartproctor.Studentinterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Listingstudents extends AppCompatActivity {
    private String value;
    ListView listView;
    int i=1;
    int i2=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listingstudents);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("year");
            //The key argument here must match that used in the other activity
        }
        listView=findViewById(R.id.studentlist);
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
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(Listingstudents.this, android.R.layout.simple_list_item_1, list1);
                listView.setAdapter(adapter3);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent=new Intent(Listingstudents.this, Studentdetails.class);
               String rollno=(String) listView.getItemAtPosition(position);
               intent.putExtra("rollno",rollno);
               startActivity(intent);


           }
       });

    }
}