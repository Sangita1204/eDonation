package com.example.edonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.edonation.utils.ExtraUtils;
import com.google.android.material.navigation.NavigationView;


public class activity_organisationDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    private DrawerLayout drawer;
    NavigationView navigationView;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation_dashboard);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        email = getIntent().getStringExtra(ExtraUtils.EXTRA_EMAIL); // Passing Data
        Log.i("EmailPassCheck","Activity Email: "+email);

        drawer= findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Bundle bundle = new Bundle();
        bundle.putString(ExtraUtils.EXTRA_EMAIL, email); // Passing Data
        switch (item.getItemId()){
            /*case R.id.nav_home:
               // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new activity_home_donor()).commit();
                break;*/
            case R.id.nav_addDonor:
                activity_add_donor add_donor = new activity_add_donor();
                add_donor.setArguments(bundle); // Passing Data
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, add_donor).commit();
                break;
            case R.id.nav_listDonor:
                activity_donorDashboard donorDashboard = new activity_donorDashboard();
                donorDashboard.setArguments(bundle); // Passing Data
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, donorDashboard).commit();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}