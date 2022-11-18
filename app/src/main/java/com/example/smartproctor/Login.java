package com.example.smartproctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private FirebaseAuth auth;
    private ImageView btnLogout,tbutton,pbutton;
    private String str,role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        btnLogout = findViewById(R.id.btnlogin);
        tbutton = findViewById(R.id.button);
        pbutton = findViewById(R.id.button2);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                role="student";
                login1();

            }
        });
        tbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                role="teacher";
                login1();

            }

        });
        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                role="parent";
                login1();
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentuser = auth.getCurrentUser();
        Intent intent;
        String s;
        if (currentuser != null) {
            String email=currentuser.getEmail();
            if(email.endsWith("@vrsec.ac.in"))
                role="student";
            else if(email.endsWith("@gmail.com"))
                role="teacher";
            else
                role="parent";
            if (role.equals("student")) {
                intent = new Intent(Login.this, Studentinterface.class);
                startActivity(intent);
            } else if (role.equals("teacher")) {
                intent = new Intent(Login.this, Teacherinterface.class);
                startActivity(intent);
            } else {
                intent = new Intent(Login.this, Parentinterface.class);
                startActivity(intent);
            }
            intent.putExtra("role", role);

        }
    }
        public void login1 () {

            Intent intent = new Intent(Login.this, Loginpage.class);
            intent.putExtra("role", role);
            startActivity(intent);

        }
    }