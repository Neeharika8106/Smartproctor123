package com.example.smartproctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {

    private Button code;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private EditText e;
    private TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mAuth = FirebaseAuth.getInstance();
        code=findViewById(R.id.sentcode);
        progressBar=findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);
        t=findViewById(R.id.textView4);
        t.setVisibility(View.GONE);
        e=findViewById(R.id.emailText);
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                t.setVisibility(View.VISIBLE);
                String email=e.getText().toString().trim();
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(forgot_password.this,"Reset link sent to mail",Toast.LENGTH_LONG).show();

                            progressBar.setVisibility(View.GONE);
                            t.setVisibility(View.GONE);
                            startActivity(new Intent(forgot_password.this,Loginpage.class));
                        }
                        else
                        {
                            Toast.makeText(forgot_password.this,"Something went wrong.Try again!!",Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            t.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
}