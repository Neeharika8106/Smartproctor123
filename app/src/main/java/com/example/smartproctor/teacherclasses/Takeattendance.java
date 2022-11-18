package com.example.smartproctor.teacherclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class Takeattendance extends AppCompatActivity {
    private Button markpresent,markabsent,save,submit;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takeattendance);
        listView=findViewById(R.id.studentlist2);
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
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(Takeattendance.this, android.R.layout.simple_list_item_1, list1);
                listView.setAdapter(adapter3);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}