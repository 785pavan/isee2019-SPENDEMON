package com.dbse.android.spendemon;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import static com.dbse.android.spendemon.Summary.entries;

public class TrendLineActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static String TAG = "TrendLine";
    LineChart lineChart;
    float data = 0;
    int i = 0;
    /*private float[] yExpense = {10f, 20.6f, 30.76f, 44.32f, 46.0f, 50.8f, 66.6f};
    private float[] yIncome = {10.6f, 66.76f, 44.32f, 46.0f, 16.8f, 23.6f, 25.4f};*/
    private ArrayList<Float> yExpense = new ArrayList<>();
    private ArrayList<Float> yIncome = new ArrayList<>();
    private ArrayList<String> xCategoryI = new ArrayList<>();
    private ArrayList<String> xCategoryE = new ArrayList<>();
    private float Idata = 0;
    private float Edata = 0;

    private int backKey = 0;
    private DrawerLayout drawer1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend_line);
        Log.d(TAG, "onCreate: started");

        lineChart = findViewById(R.id.idTrend);
        lineChart.setTouchEnabled(true);
        lineChart.setPinchZoom(true);

        Description desc = new Description();
        desc.setText("Expenditure");
        lineChart.setDescription(desc);

        addDataSet();


        Toolbar toolbar = findViewById(R.id.toolBarLayoutTrendLine);
        setSupportActionBar(toolbar);

        drawer1 = findViewById(R.id.drawer_layout_TrendLine);


        NavigationView navigationView1 = findViewById(R.id.navigation_view1_TrendLine);

        NavigationView navigationView2 = findViewById(R.id.navigation_view2_TrendLine);

        navigationView1.setNavigationItemSelectedListener(this);

        navigationView2.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle1 = new ActionBarDrawerToggle(this, drawer1, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer1.addDrawerListener(toggle1);
        toggle1.syncState();



    }

    private void getData() {
        for (com.dbse.android.spendemon.model.Entry entry : entries) {
            if (entry.getType().equals("Incomes")) {
                Idata += entry.getAmount();
                yIncome.add(Idata);
                xCategoryI.add(entry.getCategory());
            } else if (entry.getType().equals("Expenses")) {
                Edata += entry.getAmount();
                yExpense.add(Edata);
                xCategoryE.add(entry.getCategory());
            }
        }
    }

    private void addDataSet() {
        Log.d(TAG, "addDataSet called");
        getData();
        ArrayList<Entry> yAxesEx = new ArrayList<>();
        ArrayList<Integer> xAxes = new ArrayList<>();
        ArrayList<Entry> yAxesIn = new ArrayList<>();

        int i = 0, j = 0;
        for (float data : yExpense) {
            yAxesEx.add(new Entry(i++, data));
            xAxes.add(i);
            i++;
        }
        for (float data : yIncome) {
            yAxesIn.add(new Entry(j++, data));
        }
        String[] xaxes = new String[xAxes.size()];
        for (int k = 0; k < xAxes.size(); k++) {
            xaxes[k] = xAxes.get(k).toString();
        }
        LineDataSet lineDataSet;
        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            lineDataSet = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            lineDataSet.setValues(yAxesEx);
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            lineDataSet = new LineDataSet(yAxesEx, "Expenses");
            lineDataSet.setDrawIcons(false);
            lineDataSet.enableDashedLine(10f, 5f, 0f);
            lineDataSet.enableDashedHighlightLine(10f, 5f, 0f);
            lineDataSet.setColor(Color.DKGRAY);
            lineDataSet.setCircleColor(Color.DKGRAY);
            lineDataSet.setLineWidth(1f);
            lineDataSet.setCircleRadius(3f);
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setValueTextSize(9f);
            lineDataSet.setDrawFilled(true);
            lineDataSet.setFormLineWidth(1f);
            lineDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            lineDataSet.setFormSize(15.f);
            if (Utils.getSDKInt() >= 18) {
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_blue);
                lineDataSet.setFillDrawable(drawable);
            } else {
                lineDataSet.setFillColor(Color.DKGRAY);
            }
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(lineDataSet);
            LineData data = new LineData(dataSets);
            lineChart.setData(data);
        }LineDataSet lineDataSet3;
        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            lineDataSet3 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            lineDataSet3.setValues(yAxesEx);
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            lineDataSet3 = new LineDataSet(yAxesEx, "Incomes");
            lineDataSet3.setDrawIcons(false);
            lineDataSet3.enableDashedLine(10f, 5f, 0f);
            lineDataSet3.enableDashedHighlightLine(10f, 5f, 0f);
            lineDataSet3.setColor(Color.DKGRAY);
            lineDataSet3.setCircleColor(Color.DKGRAY);
            lineDataSet3.setLineWidth(1f);
            lineDataSet3.setCircleRadius(3f);
            lineDataSet3.setDrawCircleHole(false);
            lineDataSet3.setValueTextSize(9f);
            lineDataSet3.setDrawFilled(true);
            lineDataSet3.setFormLineWidth(1f);
            lineDataSet3.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            lineDataSet3.setFormSize(15.f);
            if (Utils.getSDKInt() >= 18) {
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
                lineDataSet3.setFillDrawable(drawable);
            } else {
                lineDataSet3.setFillColor(Color.DKGRAY);
            }
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(lineDataSet3);
            LineData data = new LineData(dataSets);
            lineChart.setData(data);
        }


        /*ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();
        LineDataSet lineDataSet1 = new LineDataSet(yAxesEx, "Expenses");
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.BLUE);

        LineDataSet lineDataSet2 = new LineDataSet(yAxesIn, "Incomes");
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setColor(Color.RED);

        lineDataSets.add(lineDataSet1);
        lineDataSets.add(lineDataSet2);

        lineChart.setData(new LineData(lineDataSet2));

        lineChart.setVisibleXRangeMaximum(65f);*/

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
                intent_share.setType("application/vnd.android.package-archive");
                String shareBody = "We are team SPENDEMON. " +
                        "\n Please check out our App at:" +
                        "\n https://github.com/DBSE-teaching/isee2019-SPENDEMON/releases/download/V0.02/Spendemon.apk";
                String shareSubject = "The Link to SPENDEMON App:";
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
                break;
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
                Intent intent_aboutUs = new Intent(Intent.ACTION_VIEW, Uri.parse("https://dbse-teaching.github.io/isee2019-SPENDEMON"));
                startActivity(intent_aboutUs);
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
                Toast.makeText(TrendLineActivity.this, "Click one more time to exist app", Toast.LENGTH_SHORT).show();
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
