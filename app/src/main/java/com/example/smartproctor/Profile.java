package com.example.smartproctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class Profile extends AppCompatActivity {
    FirebaseAuth mAuth;
    String s,role;
    TextView name,rno,phno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        name=findViewById(R.id.name);
        rno=findViewById(R.id.rno);
        phno=findViewById(R.id.phno);
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mdb = FirebaseDatabase.getInstance();
        //Database connnection for profile option
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            role = extras.getString("regno");
            role=role.toLowerCase(Locale.ROOT);
            //The key argument here must match that used in the other activity
        }
        DatabaseReference reference=mdb.getReference().child("Users").child("student").child(mAuth.getCurrentUser().getUid());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                s=snapshot.child("fullname").getValue().toString();
                name.setText(s);
                rno.setText(snapshot.child("registerno").getValue().toString().toUpperCase());
                phno.setText(snapshot.child("phn").getValue().toString());

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}