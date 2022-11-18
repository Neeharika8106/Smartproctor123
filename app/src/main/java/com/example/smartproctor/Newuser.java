package com.example.smartproctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Newuser extends AppCompatActivity {

    private Button register;
    private EditText name, email, password, phn, registerno,role;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newuser);
        mAuth = FirebaseAuth.getInstance();
        register = findViewById(R.id.button4);

        registerno = findViewById(R.id.Rn);
        name = findViewById(R.id.name);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        progressBar = findViewById(R.id.progressBar);
        role=findViewById(R.id.role);
        phn=findViewById(R.id.Phoneno);
        progressBar.setVisibility(View.GONE);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

    }



    private void registerUser() {
        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();
        String fullName = name.getText().toString().trim();
        String rn = registerno.getText().toString().trim();
        String phnno = phn.getText().toString().trim();
        String role2=role.getText().toString().trim();
        if (fullName.isEmpty()) {
            name.setError("Full name is required");
            name.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (rn.isEmpty()) {
            registerno.setError("Age is required");
            registerno.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (phnno.isEmpty()) {
            phn.setError("Age is required");
            phn.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;

        }

        if (passwordText.isEmpty()) {
            password.setError("Password can't be empty");
            password.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (passwordText.length() < 6) {
            password.setError("The password length must be more than 8");
            password.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if(role2.isEmpty())
        {
            role.setError("Enter Your role");
            role.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user=new User(fullName,rn,phnno,emailText,role2);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(role2).child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(Newuser.this,"User has registered Successfully!",Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                                startActivity(new Intent(Newuser.this,Loginpage.class));
                                            }
                                            else{
                                                Toast.makeText(Newuser.this,"Failed to register! Try again!",Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        }
                    }
                });
    }
}