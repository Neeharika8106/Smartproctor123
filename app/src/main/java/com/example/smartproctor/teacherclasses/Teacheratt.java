package com.example.smartproctor.teacherclasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.smartproctor.R;

import java.text.DateFormat;
import java.util.Calendar;

public class Teacheratt extends AppCompatActivity {
    Spinner selectyear,selectsec,selectsem;
    Button add,view,overall;
    ImageButton calendar;
    DatePickerDialog picker;
    public EditText fromdate,todate;
    String date,year,sem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacheratt);
        selectyear = findViewById(R.id.spinner1);
        selectsec = findViewById(R.id.spinneryear);
        selectsem = findViewById(R.id.spinnerSE);
        add = findViewById(R.id.buttonsubmit);
        view = findViewById(R.id.viewAttendancebutton);
        overall = findViewById(R.id.viewTotalAttendanceButton);
        calendar = findViewById(R.id.DateImageButton);
        fromdate = findViewById(R.id.DateEditText);
        todate=findViewById(R.id.toDateEditText);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Teacheratt.this, R.array.Years_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectyear.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(Teacheratt.this, R.array.Sec_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectsec.setAdapter(adapter2);
        year=selectyear.getSelectedItem().toString();
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(Teacheratt.this, R.array.Sem_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectsem.setAdapter(adapter3);
        sem=selectsem.getSelectedItem().toString();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Teacheratt.this, "you clicked view attendance", Toast.LENGTH_SHORT).show();
            }
        });
        overall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Teacheratt.this, "Yo clicked overall attendance", Toast.LENGTH_SHORT).show();
            }
        });
        fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Teacheratt.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                fromdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                date=fromdate.getText().toString();
                                date=date+'-';
                            }
                        }, year, month, day);
                picker.show();
            }
        });
       todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Teacheratt.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                               todate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                               date=date+todate.getText().toString();
                               Toast.makeText(Teacheratt.this," "+date,Toast.LENGTH_SHORT).show();
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Teacheratt.this, Enterattend.class);
                intent.putExtra("date",date);
                intent.putExtra("year",year);
                intent.putExtra("sem",sem);
                Toast.makeText(Teacheratt.this,""+year+""+sem,Toast.LENGTH_SHORT).show();
                        startActivity(intent);
            }
        });

    }

}