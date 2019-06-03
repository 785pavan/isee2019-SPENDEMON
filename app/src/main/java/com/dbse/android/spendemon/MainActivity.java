package com.dbse.android.spendemon;

import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.design.widget.NavigationView;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

//import android.support.v7.widget.Toolbar;

//import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.github.mikephil.charting.charts.PieChart;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

//    private Toolbar toolbar;

    private DrawerLayout drawer1;
    private Menu menu1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolBarLayout);
        setSupportActionBar(toolbar);

        drawer1 = findViewById(R.id.drawer_layout);


        NavigationView navigationView1 = findViewById(R.id.navigation_view2);

        NavigationView navigationView2 = findViewById(R.id.navigation_view1);

        navigationView1.setNavigationItemSelectedListener(this);

        navigationView2.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle1 = new ActionBarDrawerToggle(this, drawer1, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer1.addDrawerListener(toggle1);
        toggle1.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MonthlyFragment()).commit();
            navigationView1.setCheckedItem(R.id.nav_monthly);
        }


    }


    @Override
    public void onBackPressed() {
        if (drawer1.isDrawerOpen(GravityCompat.START)) {
            drawer1.closeDrawer(GravityCompat.START);
        } else if (drawer1.isDrawerOpen(GravityCompat.END)){
            drawer1.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_daily:

                /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DailyFragment()).commit();*/
                Intent intent_daily = new Intent(getApplicationContext(),PieChartDailyActivity.class);
                startActivity(intent_daily);
                break;
            case R.id.nav_weekly:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new WeeklyFragment()).commit();
                break;
            case R.id.nav_monthly:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MonthlyFragment()).commit();
                break;
            case R.id.nav_total:
                Intent intent_total = new Intent(getApplicationContext(), PieChartActivity.class);
                startActivity(intent_total);
                /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TotalFragment()).commit();*/
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_LONG).show();
                Intent intent_share = new Intent(Intent.ACTION_SEND);
                intent_share.setType("text/plain");
                String shareBody = "We are team SPENDEMON. Please check out our blog: https://dbse-teaching.github.io/isee2019-SPENDEMON/";
                String shareSubject = "The link for our Blog";
                intent_share.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                intent_share.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intent_share, "Share this by"));
                break;
            case R.id.nav_send:
                Toast.makeText(this, "Contact Us E-mail", Toast.LENGTH_LONG).show();
                Intent intent_contactUs = new Intent(Intent.ACTION_SEND);
                intent_contactUs.setType("message/rfc822");
                String contactBody = "Sent from SPENDEMON app.";
                String contactSubject = "Feedback for SPENDEMON team";
                intent_contactUs.putExtra(Intent.EXTRA_SUBJECT, contactSubject);
                intent_contactUs.putExtra(Intent.EXTRA_TEXT, contactBody);
                intent_contactUs.putExtra(Intent.EXTRA_EMAIL, new String[]{"pavan.kandapagari@st.ovgu.de",
                        "atrayee.neog@st.ovgu.de", "seyedbehnam.beladi@st.ovgu.de"});
                startActivity(Intent.createChooser(intent_contactUs, "Share this by"));
                break;
//                pavan.kandapagari@st.ovgu.de,atrayee.neog@st.ovgu.de,seyedbehnam.beladi@st.ovgu.de
            case R.id.nav_summary:
                Intent intent = new Intent(getApplicationContext(), Summary.class);
                startActivity(intent);
                /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SummaryFragment()).commit();*/
//                right drawer
            case R.id.nav_currency:
                Toast.makeText(this, "Currency", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_Calculator:
                Toast.makeText(this, "Calculator", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_aboutUs:
                Toast.makeText(this, "About Us", Toast.LENGTH_LONG).show();
                break;
        }

        drawer1.closeDrawer(GravityCompat.START);


        return true;
    }
}
