package com.example.smartproctor.teacherclasses;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.smartproctor.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Studentdetails extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference mdb;
    String s1,s2;
    String rollno;
    TextView name,rno,phno,details;
    TabLayout tabLayout;
    ViewPager2 viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentdetails);
        mAuth= FirebaseAuth.getInstance();
        name=findViewById(R.id.name);
        rno=findViewById(R.id.rno);
        phno=findViewById(R.id.Phoneno);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager2)findViewById(R.id.viewPager);





        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //Database connnection for profile option
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rollno = extras.getString("rollno");
            //The key argument here must match that used in the other activity

        }
        DatabaseReference reference1=FirebaseDatabase.getInstance().getReference().child("userid");
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                s1=snapshot.child(rollno).getValue().toString();
                Toast.makeText(Studentdetails.this, " "+s1, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference reference2=FirebaseDatabase.getInstance().getReference().child("Users").child("student").child("4guwvmjSmtTF9f8ENzrvjunfjww1");
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                s2=snapshot.child("fullname").getValue().toString();
                name.setText(s2);
                s2=snapshot.child("registerno").getValue().toString().toUpperCase();
                rno.setText(s2);
                s2=snapshot.child("phn").getValue().toString();
                phno.setText(s2);

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        rno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Studentdetails.this, ""+rollno, Toast.LENGTH_SHORT).show();
            }
        });
    }
}