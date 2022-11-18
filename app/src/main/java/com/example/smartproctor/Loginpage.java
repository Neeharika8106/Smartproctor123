package com.example.smartproctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Loginpage extends AppCompatActivity {

        private FirebaseAuth auth;
        private EditText email,password;
        private Button btnLogin;
        private TextView newUser;
        private TextView forgotpd;
        private String role,email2;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_loginpage);




            auth=FirebaseAuth.getInstance();
            email=findViewById(R.id.username);
            password=findViewById(R.id.password);
            btnLogin=findViewById(R.id.submit);
            newUser=findViewById(R.id.newUser);
            forgotpd=findViewById(R.id.forgotpd);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login();
                }
            });
            newUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Loginpage.this,Newuser.class));
                }
            });
            forgotpd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Loginpage.this,forgot_password.class));
                }
            });


        }


        private void login()
        {

            String user=email.getText().toString().trim();
            String pass=password.getText().toString().trim();

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                role = extras.getString("role");
                //The key argument here must match that used in the other activity
            }
            if(role.equals("student"))
            {
                user=user+"@vrsec.ac.in";
            }
            else if(role.equals("teacher"))
            {

                user="examplemail@gmail.com";
            }

            Toast.makeText(Loginpage.this,"hii"+role,Toast.LENGTH_SHORT).show();

            if(user.isEmpty())
            {
                email.setError("Username can't be empty");
            }
            if(pass.isEmpty())
            {
                password.setError("Password can't be empty");
            }
            else
            {
                auth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Loginpage.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                              if(role.equals("student"))
                                startActivity(new Intent(Loginpage.this, Studentinterface.class));
                              else if(role.equals("teacher"))
                                  startActivity(new Intent(Loginpage.this,Teacherinterface.class));
                              else
                                  startActivity(new Intent(Loginpage.this, Parentinterface.class));
                        }
                        else
                        {
                            Toast.makeText(Loginpage.this,"Login Failed!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
    }
}