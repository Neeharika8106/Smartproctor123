package com.example.smartproctor.teacherclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartproctor.R;

public class addstudent extends AppCompatActivity {
    EditText rollno;
    Button add;
    private String addorremove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);
        rollno=findViewById(R.id.rollno);
        add=findViewById(R.id.add);
        String s=rollno.getText().toString();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            addorremove= extras.getString("msg");
            //The key argument here must match that used in the other activity
        }

        if(addorremove.equals("add")) {
            add.setText(R.string.add);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s=rollno.getText().toString();
                    Toast.makeText(addstudent.this, "Added:" + s, Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            add.setText(R.string.remove1);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s=rollno.getText().toString();
                    if(s.isEmpty())
                        Toast.makeText(addstudent.this, "Enter a number to remove", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(addstudent.this, "Removed:"+s, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}