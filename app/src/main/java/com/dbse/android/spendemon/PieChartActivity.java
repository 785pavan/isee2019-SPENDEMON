package com.dbse.android.spendemon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import static com.dbse.android.spendemon.Summary.entries;

public class PieChartActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static String TAG = "PieChart";
    /* private float[] yData = {25.4f, 10.6f, 66.76f, 44.32f, 46.0f, 16.8f, 23.6f};
     private String[] xData = {"Mitch", "Jessica", "Md", "Kelsey", "Sam", "Robert", "Ashley"};*/
    ArrayList<Float> AmountValues = new ArrayList<>();
    ArrayList<String> Categories = new ArrayList<>();
    ArrayList<Float> yData = new ArrayList<>();
    ArrayList<String> xData = new ArrayList<>();
    ArrayList<Float> yDataIn = new ArrayList<>();
    ArrayList<String> xDataIn = new ArrayList<>();
    Intent intent;
    PieChart pieChart;
    PieChart pieChartInput;
    private DrawerLayout drawer1;
    private float incomeSum;
    private float expenseSum;
    private int backKey = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

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


        intent = getIntent();
        Log.d(TAG, "onCreate: Start of creation of chart");
        pieChart = findViewById(R.id.idPieChart);
        Description desc = new Description();
        desc.setText("Expenditure");
        pieChart.setDescription(desc);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(25);
        pieChart.setCenterText("Category");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(true);

        pieChartInput = findViewById(R.id.idPieChartIncome);
        Description descIncome = new Description();
        descIncome.setText("Earnings");
        pieChartInput.setDescription(descIncome);
        pieChartInput.setRotationEnabled(true);
        pieChartInput.setHoleRadius(25f);
        pieChartInput.setTransparentCircleAlpha(25);
        pieChartInput.setCenterText("Category");
        pieChartInput.setCenterTextSize(10);
        pieChartInput.setDrawEntryLabels(true);


        addDataSet();
        Legend l = pieChart.getLegend();
        l.getEntries();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setYEntrySpace(xData.size());
        l.setXEntrySpace(yData.size());

        Legend L = pieChartInput.getLegend();
        L.getEntries();
        L.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        L.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        L.setDrawInside(false);
        L.setYEntrySpace(xData.size());
        L.setXEntrySpace(yData.size());

        addDataSetIncome();

//        Todo: Add extra piechart selectedlistener for income
        pieChartInput.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                int posIn = e.toString().indexOf("y: ");
                String cost = e.toString().substring(posIn + 3);

                for (int i = 0; i < yDataIn.size(); i++) {
                    if (yDataIn.get(i) == Float.parseFloat(cost)) {
                        posIn = i;
                        break;
                    }
                }
                String cat = xDataIn.get(posIn);
                Toast.makeText(PieChartActivity.this, "Category: " + cat + "\n" +
                        "spent: " + cost, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected() {

            }
        });


        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected from chart");
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());

                int pos1 = e.toString().indexOf("y: ");
                String cost = e.toString().substring(pos1 + 3);

                for (int i = 0; i < yData.size(); i++) {
                    if (yData.get(i) == Float.parseFloat(cost)) {
                        pos1 = i;
                        break;
                    }
                }
                String cat = xData.get(pos1);
                Toast.makeText(PieChartActivity.this, "Category: " + cat + "\n" +
                        "spent: " + cost, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    private void addDataSet() {

        getDataExpense();

        Log.d(TAG, "addDataSet called");
        ArrayList<PieEntry> yEntries = new ArrayList<>();
        ArrayList<String> xEntries = new ArrayList<>();

        int i = 0;
        for (float data : yData) {
            yEntries.add(new PieEntry(data, i++));
        }
        for (String name : xData) {
            xEntries.add(name);
        }


        PieDataSet pieDataSet = new PieDataSet(yEntries, "Cost");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //Colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        pieDataSet.setColors(colors);

        //add legend
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);

        //Pie data create
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }


    private void addDataSetIncome() {

        getDataIncome();

        Log.d(TAG, "addDataSetIncome called");
        ArrayList<PieEntry> yEntries = new ArrayList<>();
        ArrayList<String> xEntries = new ArrayList<>();

        int i = 0;
        for (float data : yDataIn) {
            yEntries.add(new PieEntry(data, i++));
        }
        for (String name : xDataIn) {
            xEntries.add(name);
        }

        PieDataSet pieDataSet = new PieDataSet(yEntries, "Cost");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //Colors

        ArrayList<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        pieDataSet.setColors(colors);

        //add legend
        Legend legendIncome = pieChartInput.getLegend();
        legendIncome.setForm(Legend.LegendForm.CIRCLE);

        //Pie data create

        PieData pieData = new PieData(pieDataSet);

        pieChartInput.setData(pieData);
        pieChartInput.invalidate();
    }


    private void getData() {
        for (com.dbse.android.spendemon.model.Entry entry : entries) {
            if ((intent.getStringExtra("Duration")).equals("Day")) {
                if (entry.getDate().equals(intent.getStringExtra("Date"))) {
                    AmountValues.add((float) entry.getAmount());
                    Categories.add(entry.getCategory());
                }
            } else if (intent.getStringExtra("Duration").equals("Month")) {
                String monthIntent = intent.getStringExtra("Month");
                String monthEntry = entry.getDate().substring(2, 3);
                if (entry.getDate().substring(1, 2).equals("/")) {
                    monthEntry = entry.getDate().substring(2, 3);
                } else if (entry.getDate().substring(2, 3).equals("/")) {
                    monthEntry = entry.getDate().substring(3, 4);
                }

                if (monthEntry.equals(monthIntent)) {
                    AmountValues.add((float) entry.getAmount());
                    Categories.add(entry.getCategory());
                }
            } else if (intent.getStringExtra("Duration").equals("All")) {
                AmountValues.add((float) entry.getAmount());
                Categories.add(entry.getCategory());
            }
        }
//        behnam code:
        boolean save_data;

        float data;
        for (int i = 0; i < Categories.size(); i++) {
            data = 0;
            for (int j = i; j < Categories.size(); j++) {
                if (Categories.get(i).equals(Categories.get(j))) {
                    data += AmountValues.get(j);
                }
            }
//            yData.add(data);
//            xData.add(Categories.get(i).equals("---") ? "Not Defined" : Categories.get(i));

            //            add data modification code:
            save_data = true;
            for (int k = 0; k < i; k++) {
                if (Categories.get(k).equals(Categories.get(i))) {
                    save_data = false;
                }
                Log.i("k value:", String.valueOf(k));
            }
            if (save_data) {
                yData.add(data);
                xData.add(Categories.get(i).equals("---") ? "Not Defined" : Categories.get(i));
            }



            /*if (Categories.get(i).equals(Categories.get(i + 1))) {
                data += AmountValues.get(i + 1);
                continue;
            }
            yData.add(data);
            xData.add(Categories.get(i));*/

        }
    }


    private void getDataIncome() {
        AmountValues.clear();
        Categories.clear();
        for (com.dbse.android.spendemon.model.Entry entry : entries) {
            if (entry.getType().equals("Incomes")) {

                if ((intent.getStringExtra("Duration")).equals("Day")) {
                    if (entry.getDate().equals(intent.getStringExtra("Date"))) {
                        AmountValues.add((float) entry.getAmount());
                        Categories.add(entry.getCategory());
                    }
                } else if (intent.getStringExtra("Duration").equals("Month")) {
                    String monthIntent = intent.getStringExtra("Month");
                    String monthEntry = entry.getDate().substring(2, 3);
                    if (entry.getDate().substring(1, 2).equals("/")) {
                        monthEntry = entry.getDate().substring(2, 3);
                    } else if (entry.getDate().substring(2, 3).equals("/")) {
                        monthEntry = entry.getDate().substring(3, 4);
                    }

                    if (monthEntry.equals(monthIntent)) {
                        AmountValues.add((float) entry.getAmount());
                        Categories.add(entry.getCategory());
                    }
                } else if (intent.getStringExtra("Duration").equals("All")) {
                    AmountValues.add((float) entry.getAmount());
                    Categories.add(entry.getCategory());
                }
            }
        }
//        behnam code:
        boolean save_data;

        float data;
        for (int i = 0; i < Categories.size(); i++) {
            data = 0;
            for (int j = i; j < Categories.size(); j++) {
                if (Categories.get(i).equals(Categories.get(j))) {
                    data += AmountValues.get(j);
                }
            }
//            yData.add(data);
//            xData.add(Categories.get(i).equals("---") ? "Not Defined" : Categories.get(i));

            //            add data modification code:
            save_data = true;
            for (int k = 0; k < i; k++) {
                if (Categories.get(k).equals(Categories.get(i))) {
                    save_data = false;
                }
                Log.i("k value:", String.valueOf(k));
            }
            if (save_data) {
                yDataIn.add(data);
                xDataIn.add(Categories.get(i).equals("---") ? "Not Defined" : Categories.get(i));
            }

            incomeSum = 0;
            for (float index : yDataIn) {
                incomeSum += index;
            }
            TextView textViewIncome = findViewById(R.id.textViewChartIncomeValue);
            String stringIncomeSum = String.valueOf((float) ((int) (incomeSum * 100)) / 100);
            stringIncomeSum = "+ " + stringIncomeSum;
            textViewIncome.setText(stringIncomeSum);
            /*if (Categories.get(i).equals(Categories.get(i + 1))) {
                data += AmountValues.get(i + 1);
                continue;
            }
            yData.add(data);
            xData.add(Categories.get(i));*/

        }
    }


    private void getDataExpense() {
        for (com.dbse.android.spendemon.model.Entry entry : entries) {
            if (entry.getType().equals("Expenses")) {

                if ((intent.getStringExtra("Duration")).equals("Day")) {
                    if (entry.getDate().equals(intent.getStringExtra("Date"))) {
                        AmountValues.add((float) entry.getAmount());
                        Categories.add(entry.getCategory());
                    }
                } else if (intent.getStringExtra("Duration").equals("Month")) {
                    String monthIntent = intent.getStringExtra("Month");
                    String monthEntry = entry.getDate().substring(2, 3);
                    if (entry.getDate().substring(1, 2).equals("/")) {
                        monthEntry = entry.getDate().substring(2, 3);
                    } else if (entry.getDate().substring(2, 3).equals("/")) {
                        monthEntry = entry.getDate().substring(3, 4);
                    }

                    if (monthEntry.equals(monthIntent)) {
                        AmountValues.add((float) entry.getAmount());
                        Categories.add(entry.getCategory());
                    }
                } else if (intent.getStringExtra("Duration").equals("All")) {
                    AmountValues.add((float) entry.getAmount());
                    Categories.add(entry.getCategory());
                }
            }
        }
//        behnam code:
        boolean save_data;

        float data;
        for (int i = 0; i < Categories.size(); i++) {
            data = 0;
            for (int j = i; j < Categories.size(); j++) {
                if (Categories.get(i).equals(Categories.get(j))) {
                    data += AmountValues.get(j);
                }
            }
//            yData.add(data);
//            xData.add(Categories.get(i).equals("---") ? "Not Defined" : Categories.get(i));

            //            add data modification code:
            save_data = true;
            for (int k = 0; k < i; k++) {
                if (Categories.get(k).equals(Categories.get(i))) {
                    save_data = false;
                }
                Log.i("k value:", String.valueOf(k));
            }
            if (save_data) {
                yData.add(data);
                xData.add(Categories.get(i).equals("---") ? "Not Defined" : Categories.get(i));
            }


            expenseSum = 0;
            for (float index : yData) {
                expenseSum += index;
            }

            TextView textViewExpense = findViewById(R.id.textViewChartExpenseValue);
            String stringExpenseSum = String.valueOf((float) ((int) (expenseSum * 100)) / 100);
            stringExpenseSum = "- " + stringExpenseSum;
            textViewExpense.setText(stringExpenseSum);

            /*if (Categories.get(i).equals(Categories.get(i + 1))) {
                data += AmountValues.get(i + 1);
                continue;
            }
            yData.add(data);
            xData.add(Categories.get(i));*/

        }
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
//            case R.id.nav_weekly:
//                /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new MonthlyFragment()).commit();*/
//                /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new WeeklyFragment()).commit();*/
                break;
            case R.id.nav_monthly:
                Intent intent_month = new Intent(getApplicationContext(), ChartMonthActivity.class);
                startActivity(intent_month);
                /*Intent intent_month = new Intent(getApplicationContext(), ChartMonthActivity.class);
                startActivity(intent_month);*/
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
                Toast.makeText(PieChartActivity.this, "Click one more time to exit app", Toast.LENGTH_SHORT).show();
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
