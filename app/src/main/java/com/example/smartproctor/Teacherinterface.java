package com.example.smartproctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.smartproctor.teacherclasses.Overalldata;
import com.example.smartproctor.teacherclasses.Proctorstart;
import com.example.smartproctor.teacherclasses.Selectmarks;
import com.example.smartproctor.teacherclasses.Teacheratt;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Teacherinterface extends AppCompatActivity {
    private ImageView att,marks,oatt,proctor;
    FirebaseAuth auth;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;
    TextView profname,profmail;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherinterface);
        //declaration
        att=findViewById(R.id.attendence);
        oatt=findViewById(R.id.overall);
        proctor=findViewById(R.id.proctor);
        marks=findViewById(R.id.marks);
        profname=findViewById(R.id.profname);
        profmail=findViewById(R.id.profmail);
        auth= FirebaseAuth.getInstance();
        FirebaseUser cu=auth.getCurrentUser();
        NavigationView navigationView1 = (NavigationView) findViewById(R.id.navigationview);
        View headerView = navigationView1.getHeaderView(0);
        TextView profname = (TextView) headerView.findViewById(R.id.profname);
         TextView mail=(TextView) headerView.findViewById(R.id.profmail);
        //navigation menu
        drawerLayout=findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.navigationview);
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.menu_open,R.string.menu_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {

                    case R.id.nav_home:
                        Log.i("MENU DRAWER TAG","Home item is clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(Teacherinterface.this,Teacherinterface.class));
                        break;
                    case R.id.nav_profile:
                        Log.i("MENU DRAWER TAG","Profile item is clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_proctor:
                        Log.i("MENU DRAWER TAG","Proctor item is clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(Teacherinterface.this,Proctorstart.class));
                        break;
                    case R.id.nav_settings:
                        Log.i("MENU DRAWER TAG","settings item is clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_contact:
                        Log.i("MENU DRAWER TAG","About the app item is clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_logout:
                        Log.i("MENU DRAWER TAG","Logout item is clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        auth.signOut();
                        startActivity(new Intent(Teacherinterface.this,MainActivity.class));
                        break;
                }
                return true;
            }
        });
        //proctor Button Listener
        proctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Teacherinterface.this, Proctorstart.class));
            }
        });
        //attendence button listener
        att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Teacherinterface.this, Teacheratt.class));
            }
        });
        //marks listener
        marks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Teacherinterface.this, Selectmarks.class));
            }
        });
        //overall data
        oatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Teacherinterface.this, Overalldata.class));
            }
        });
        String userid=cu.getUid();
        DatabaseReference dbf = FirebaseDatabase.getInstance().getReference().child("Users").child("teacher").child(userid);
        dbf.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.child("fullname").getValue().toString();
                profname.setText(s);
                mail.setText(snapshot.child("email").getValue().toString());

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
       //Profile


    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.manudrawer, menu);

        return true;

    }
}