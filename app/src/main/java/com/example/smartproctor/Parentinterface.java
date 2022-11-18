package com.example.smartproctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Parentinterface extends AppCompatActivity {
     ImageView call,perf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parentinterface);
        call=findViewById(R.id.call);
        perf=findViewById(R.id.performance);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num="9989589816";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+num));
                if(ActivityCompat.checkSelfPermission(Parentinterface.this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED)
                {
                    return;
                }
                startActivity(intent);

            }
        });
        perf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Parentinterface.this,Childperformance.class));
            }
        });
    }
}