package com.example.smartproctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.smartproctor.studentclasses.Studentatt;
import com.example.smartproctor.studentclasses.Studentmarks;
import com.example.smartproctor.teacherclasses.Proctorstart;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Studentinterface extends AppCompatActivity {
    private ImageView att,marks,oatt,proctor;
    FirebaseAuth auth;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;
    TextView profname,mail;
    String regno;
    String role;
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
        setContentView(R.layout.activity_studentinterface);
        att=findViewById(R.id.attendence);
        marks=findViewById(R.id.viewmarks);
        proctor=findViewById(R.id.myproctor);
        oatt=findViewById(R.id.oatt);
        auth= FirebaseAuth.getInstance();
        NavigationView navigationView1 = (NavigationView) findViewById(R.id.navigationview);
        View headerView = navigationView1.getHeaderView(0);
        TextView profname = (TextView) headerView.findViewById(R.id.profname);
        mail=(TextView) headerView.findViewById(R.id.profmail);
        FirebaseUser cu=auth.getCurrentUser();
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
                        startActivity(new Intent(Studentinterface.this,Teacherinterface.class));
                        break;
                    case R.id.nav_profile:
                        Log.i("MENU DRAWER TAG","Profile item is clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent intent=new Intent(Studentinterface.this, Profile.class);
                        role="student";
                        intent.putExtra("role",role);
                        startActivity(intent);
                        break;
                    case R.id.nav_report:
                        Log.i("MENU DRAWER TAG","Proctor item is clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(Studentinterface.this, Proctorstart.class));
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
                        startActivity(new Intent(Studentinterface.this,MainActivity.class));
                        break;
                }
                return true;
            }
        });
        String userid=cu.getUid();
        DatabaseReference dbf = FirebaseDatabase.getInstance().getReference().child("Users").child("student").child(userid);
        dbf.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.child("fullname").getValue().toString();
                profname.setText(s);
                mail.setText(snapshot.child("email").getValue().toString());
                regno=snapshot.child("registerno").getValue().toString();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Studentinterface.this, Studentatt.class));
            }
        });
        marks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Studentinterface.this, Studentmarks.class);
                intent.putExtra("regno",regno);
                startActivity(intent);
            }
        });
        proctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        oatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.manudrawer, menu);

        return true;


}

}
