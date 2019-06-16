package com.dbse.android.spendemon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import static com.dbse.android.spendemon.Summary.entries;

public class BalanceActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    ArrayList<Float> iData = new ArrayList<>();
    ArrayList<Float> eData = new ArrayList<>();
    float balance;

    private DrawerLayout drawer1;
    private int backKey = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        Toolbar toolbar = findViewById(R.id.toolBarLayoutAll);
        setSupportActionBar(toolbar);

        drawer1 = findViewById(R.id.drawer_layout_pie);


        NavigationView navigationView1 = findViewById(R.id.navigation_view1_Pie);

        NavigationView navigationView2 = findViewById(R.id.navigation_view2_Pie);

        navigationView1.setNavigationItemSelectedListener(this);

        navigationView2.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle1 = new ActionBarDrawerToggle(this, drawer1, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer1.addDrawerListener(toggle1);
        toggle1.syncState();

        TextView textView = findViewById(R.id.balanceTextView);

        for (com.dbse.android.spendemon.model.Entry entry : entries) {
            if(entry.getType().equals("Incomes")){
                iData.add((float)entry.getAmount());
            }else if (entry.getType().equals("Expenses")){
                eData.add((float)entry.getAmount());
            }
        }
        balance = 0;
        for(float index : iData){
            balance += index;
        }
        for(float index : eData){
            balance -= index;
        }
//        string should only have two numbers after the float
        String string = String.valueOf((float) ((int) ( balance * 100)) / 100);

        if (balance > 0){
            string = "+  " + string;
            textView.setTextColor(Color.parseColor("green"));
        }else if (balance < 0){
            string = "-  " + string.substring(1);
            textView.setTextColor(Color.parseColor("red"));
        }else{
            textView.setTextColor(Color.parseColor("black"));
        }
        textView.setText(string);






    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_daily:

                /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DailyFragment()).commit();*/
                Intent intent_daily = new Intent(getApplicationContext(), PieChartDailyActivity.class);
                startActivity(intent_daily);
                break;
            case R.id.nav_balance:
                Intent intent_balance = new Intent(getApplicationContext(), BalanceActivity.class);
                startActivity(intent_balance);
//                Intent intent_month = new Intent(getApplicationContext(), ChartMonthActivity.class);
//                startActivity(intent_month);
                /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new WeeklyFragment()).commit();*/
                break;
            case R.id.nav_monthly:
                Intent intent_month = new Intent(getApplicationContext(), ChartMonthActivity.class);
                startActivity(intent_month);
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new MonthlyFragment()).commit();
                break;
            case R.id.nav_total:
                Intent intent_total = new Intent(getApplicationContext(), PieChartActivity.class);
                intent_total.putExtra("Duration", "All");
                startActivity(intent_total);
                /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TotalFragment()).commit();*/
                break;
            case R.id.nav_trendLine:
                Intent intent_trendLine = new Intent(getApplicationContext(), TrendLineActivity.class);
                startActivity(intent_trendLine);
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
                Intent intent_set = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent_set);
                break;
            case R.id.nav_aboutUs:
                Toast.makeText(this, "About Us", Toast.LENGTH_LONG).show();
                break;
        }

        drawer1.closeDrawer(GravityCompat.START);
        return true;
    }


    public void onBackPressed() {

        if (drawer1.isDrawerOpen(GravityCompat.START)) {
            drawer1.closeDrawer(GravityCompat.START);
        } else if (drawer1.isDrawerOpen(GravityCompat.END)) {
            drawer1.closeDrawer(GravityCompat.END);
        } else {
            backKey++;
            if (backKey == 1) {
                Toast.makeText(BalanceActivity.this, "Click one more time to exist app", Toast.LENGTH_SHORT).show();
            } else {
                //exit app to home screen
                Intent homeScreenIntent = new Intent(Intent.ACTION_MAIN);
                homeScreenIntent.addCategory(Intent.CATEGORY_HOME);
                homeScreenIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(homeScreenIntent);
            }
//            super.onBackPressed();
        }

    }
}
