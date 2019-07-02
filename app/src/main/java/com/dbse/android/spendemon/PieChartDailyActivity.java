package com.dbse.android.spendemon;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.github.mikephil.charting.charts.PieChart;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Calendar;

public class PieChartDailyActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private static String TAG = "PieChart";
    /* private float[] yData = {25.4f, 10.6f, 66.76f, 44.32f, 46.0f, 16.8f, 23.6f};
     private String[] xData = {"Mitch", "Jessica", "Md", "Kelsey", "Sam", "Robert", "Ashley"};*/
    ArrayList<Float> AmountValues = new ArrayList<>();
    ArrayList<String> Categories = new ArrayList<>();

    ArrayList<Float> yData = new ArrayList<>();
    ArrayList<String> xData = new ArrayList<>();
    TextView tvDateDaily;
    PieChart pieChart;
    ImageView ivDone;
    String date;
    private DrawerLayout drawer;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private int backKey = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_daily);
        tvDateDaily = findViewById(R.id.tvDateDaily);
        ivDone = findViewById(R.id.ivDone);


        Toolbar toolbar = findViewById(R.id.toolBarLayoutDaily);
        setSupportActionBar(toolbar);


//        adding the navigation bar code:

        drawer = findViewById(R.id.drawer_layout_Day);


        NavigationView navigationView1 = findViewById(R.id.navigation_view1_Day);

        NavigationView navigationView2 = findViewById(R.id.navigation_view2_Day);

        navigationView1.setNavigationItemSelectedListener(this);

        navigationView2.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle1 = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle1);
        toggle1.syncState();


        tvDateDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PieChartDailyActivity.this, onDateSetListener,
                        year, month, day);
                dialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy: " + dayOfMonth + "/ " + month + "/ " + year);
                String date = dayOfMonth + "/" + month + "/" + year;
                tvDateDaily.setText(date);
                Log.d(TAG, "onDateSet: date " + tvDateDaily.getText().toString());
            }
        };

        ivDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                Toast.makeText(getApplicationContext(), "Done Clicked", Toast.LENGTH_LONG).show();
                date = tvDateDaily.getText().toString().equals("") ? ("" + day + "/" + month + "/" + year) : tvDateDaily.getText().toString();
                Intent intent = new Intent(getApplicationContext(), PieChartActivity.class);
                intent.putExtra("Duration","Day");
                intent.putExtra("Date", date);
                startActivity(intent);


            }
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_daily:
                Intent intent_daily = new Intent(getApplicationContext(), PieChartDailyActivity.class);
                startActivity(intent_daily);
                break;
            case R.id.nav_balance:
                Intent intent_balance = new Intent(getApplicationContext(), BalanceActivity.class);
                startActivity(intent_balance);
                break;
            case R.id.nav_monthly:
                Intent intent_month = new Intent(getApplicationContext(), ChartMonthActivity.class);
                startActivity(intent_month);
                break;
            case R.id.nav_total:
                Intent intent_total = new Intent(getApplicationContext(), PieChartActivity.class);
                intent_total.putExtra("Duration", "All");
                startActivity(intent_total);
                break;
            case R.id.nav_trendLine:
                Intent intent_trendLine = new Intent(getApplicationContext(), TrendLineActivity.class);
                startActivity(intent_trendLine);
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_LONG).show();
                Intent intent_share = new Intent(Intent.ACTION_SEND);
                intent_share.setType("text/plain");
                String shareBody = "We are team SPENDEMON. " +
                        "\n Please check out our App at:" +
                        "\n https://github.com/DBSE-teaching/isee2019-SPENDEMON/blob/master/app/release/app-release.apk";
//                String shareSubject = "The Link to SPENDEMON App:";
//                intent_share.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
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
            case R.id.nav_summary:
                Intent intent = new Intent(getApplicationContext(), Summary.class);
                startActivity(intent);
//                right drawer
                break;
            case R.id.nav_currency:
                Toast.makeText(this, "Feature Not Available in the free version", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_Calculator:
                Toast.makeText(this, "Feature Not Available in the free version", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_settings:
                Intent intent_set = new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(intent_set);
                break;
            case R.id.nav_aboutUs:
                Toast.makeText(this, "About Us", Toast.LENGTH_LONG).show();
                Intent intent_aboutUs = new Intent(Intent.ACTION_VIEW, Uri.parse("https://dbse-teaching.github.io/isee2019-SPENDEMON"));
                startActivity(intent_aboutUs);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);


        return true;
    }


    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            backKey++;
            if (backKey == 1) {
//                Toast.makeText(PieChartDailyActivity.this, "Click one more time to exit app", Toast.LENGTH_SHORT).show();
                super.onBackPressed();
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
