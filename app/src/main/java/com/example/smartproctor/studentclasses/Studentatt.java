package com.example.smartproctor.studentclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.smartproctor.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Studentatt extends AppCompatActivity {
    private Spinner selectyear,sem;
   private Button check;
  private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentatt);
        selectyear = findViewById(R.id.selectyear);
        check = findViewById(R.id.check);
        sem = findViewById(R.id.sem);
        listView = findViewById(R.id.listview);
        //adding years to spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Studentatt.this, R.array.Years_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectyear.setAdapter(adapter);
        //adding sem items to spinner
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(Studentatt.this, R.array.Sem_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sem.setAdapter(adapter2);
        //adding list items from database
        DatabaseReference fd = FirebaseDatabase.getInstance().getReference().child("Cse");

        ArrayList<String> list = new ArrayList<String>();

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fd.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            String s = snapshot1.getValue().toString();
                            list.add(s);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(Studentatt.this, android.R.layout.simple_list_item_1, list);
                listView.setAdapter(adapter3);
            }

        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}