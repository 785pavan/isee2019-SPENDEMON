package com.dbse.android.spendemon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Objects;

import static com.dbse.android.spendemon.Summary.entries;

public class BalanceActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    public static float expenseSumSetting;
    public static float incomeSumSetting;
    public static float balanceSetting;
    ArrayList<Float> iData = new ArrayList<>();
    ArrayList<Float> eData = new ArrayList<>();
    float balance;
    float expenseSum;
    float incomeSum;
    ProgressBar mProgressBar;
    EditText mThreshold;
    Button mSubmit;
    SharedPreferences sharedPreferences;
    private DrawerLayout drawer1;
    private int backKey = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = this.getSharedPreferences("Balance", 0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        Toolbar toolbar = findViewById(R.id.toolBarLayoutBalance);
        setSupportActionBar(toolbar);

        drawer1 = findViewById(R.id.drawer_balance);


        NavigationView navigationView1 = findViewById(R.id.navigation_view1_balance);

        NavigationView navigationView2 = findViewById(R.id.navigation_view2_balance);

        navigationView1.setNavigationItemSelectedListener(this);

        navigationView2.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle1 = new ActionBarDrawerToggle(this, drawer1, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer1.addDrawerListener(toggle1);
        toggle1.syncState();

        mProgressBar = findViewById(R.id.progressBarThreshold);
        mSubmit = findViewById(R.id.buttonSubmit);
        mThreshold = findViewById(R.id.editTextThreshold);

        if (!(Objects.equals(sharedPreferences.getString("Threshold", ""), ""))) {
            mThreshold.setText(sharedPreferences.getString("Threshold", ""));
        }


        TextView textView = findViewById(R.id.balanceTextView);
        TextView textViewIn = findViewById(R.id.textViewIncomeValue);
        TextView textViewEx = findViewById(R.id.textViewExpenseValue);

        for (com.dbse.android.spendemon.model.Entry entry : entries) {
            if (entry.getType().equals("Incomes")) {
                iData.add((float) entry.getAmount());
            } else if (entry.getType().equals("Expenses")) {
                eData.add((float) entry.getAmount());
            }
        }
        balance = 0;
        incomeSum = 0;
        expenseSum = 0;
        for (float index : iData) {
            balance += index;
            incomeSum += index;
        }
        for (float index : eData) {
            balance -= index;
            expenseSum += index;
        }
//        string should only have two numbers after the float
        String string = String.valueOf((float) ((int) (balance * 100)) / 100);

        if (balance > 0) {
            string = "+  " + string;
            textView.setTextColor(Color.parseColor("#60a917"));
        } else if (balance < 0) {
            string = "-  " + string.substring(1);
            textView.setTextColor(Color.parseColor("#e51400"));
        } else {
            textView.setTextColor(Color.parseColor("black"));
        }
        textView.setText(string);

        String stringIncomeSum = String.valueOf((float) ((int) (incomeSum * 100)) / 100);
        stringIncomeSum = "+ " + stringIncomeSum;
        textViewIn.setText(stringIncomeSum);
        String stringExpenseSum = String.valueOf((float) ((int) (expenseSum * 100)) / 100);
        stringExpenseSum = "- " + stringExpenseSum;
        textViewEx.setText(stringExpenseSum);

        balanceSetting = balance;
        expenseSumSetting = expenseSum;
        incomeSumSetting = incomeSum;

        String progress = "0";
        progress = progress + mThreshold.getText().toString();
        int expenseSumProgressBar = (int) expenseSum;
        mProgressBar.setProgress(Integer.parseInt(String.valueOf(expenseSumProgressBar)));
        mProgressBar.setMax(Integer.parseInt(progress));
        mProgressBar.setProgress(Integer.parseInt(String.valueOf(expenseSumProgressBar)));
        mProgressBar.setMax(Integer.parseInt(progress));

//        mSubmit.performClick();

        sharedPreferences.edit().putString("Threshold", mThreshold.getText().toString()).apply();
        mSubmit.performClick();


//        int expenseSumProgressBar = (int) expenseSum;
//        int expenseSumProgressBar = 900;
//        Log.i("expenseSum   ", "onCreate: " + expenseSumProgressBar);
//        mProgressBar.setProgress(Integer.parseInt(String.valueOf(expenseSumProgressBar)));
        /*if (mProgressBar == null){
            Log.i("Null exception", "mProgressBar is null");
        }else {
            if (expenseSumProgressBar <= 0) {
                int i = 100;
                mProgressBar.setMax(Integer.parseInt(String.valueOf(i)));
            } else {
                mProgressBar.setMax(expenseSumProgressBar);
            }

            mProgressBar.setProgress(50);
        }*/

        mThreshold.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                sharedPreferences.edit().putString("Threshold", mThreshold.getText().toString()).apply();
                if(s.length() != 0 && s.length()<10){
                    String progress = "0";
                    progress = progress + mThreshold.getText().toString();
                    mProgressBar.setMax(Integer.parseInt(progress));
                    int expenseSumProgressBar = (int) expenseSum;
                    mProgressBar.setProgress(Integer.parseInt(String.valueOf(expenseSumProgressBar)));
                    mProgressBar.setMax(Integer.parseInt(progress));
                    mProgressBar.setProgress(Integer.parseInt(String.valueOf(expenseSumProgressBar)));
                    mProgressBar.setMax(Integer.parseInt(progress));
//                    mSubmit.performClick();
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sharedPreferences.edit().putString("Threshold", mThreshold.getText().toString()).apply();
                if(s.length() != 0 && s.length()<10){
                    String progress = "0";
                    progress = progress + mThreshold.getText().toString();
                    int expenseSumProgressBar = (int) expenseSum;

                    mProgressBar.setMax(Integer.parseInt(progress));
                    mProgressBar.setProgress(Integer.parseInt(String.valueOf(expenseSumProgressBar)));
                    mProgressBar.setMax(Integer.parseInt(progress));
                    mProgressBar.setProgress(Integer.parseInt(String.valueOf(expenseSumProgressBar)));


//                    mSubmit.performClick();
                }

            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putString("Threshold", mThreshold.getText().toString()).apply();
                int expenseSumProgressBar = (int) expenseSum;
                String progress = "0";
                progress = progress + mThreshold.getText().toString();
                if (!progress.equals("0")) {
                    Log.i("progress:    ", "p = " + progress);
                    mProgressBar.setMax(Integer.parseInt(progress));

                } else {
                    mProgressBar.setMax(Integer.parseInt(String.valueOf(expenseSumProgressBar)));
                }
                Log.i("expenseSum   ", "onCreate: " + expenseSumProgressBar);
                mProgressBar.setProgress(Integer.parseInt(String.valueOf(expenseSumProgressBar)));
                mProgressBar.setMax(Integer.parseInt(progress));
                mProgressBar.setProgress(Integer.parseInt(String.valueOf(expenseSumProgressBar)));


//                if (expenseSumProgressBar > Integer.parseInt(progress)){
//                    mProgressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#80DAEB"),
//                            PorterDuff.Mode.SRC_IN);
//
//                }else{
//                    mProgressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#80DAEB"),
//                            PorterDuff.Mode.SRC_IN);
//                }
//                mProgressBar.setMax(Integer.parseInt(progress));
//                mProgressBar.setMax(Integer.parseInt(progress));
                if (!progress.equals("0")) {
                    Log.i("progress:    ", "p = " + progress);
                    mProgressBar.setMax(Integer.parseInt(progress));

                } else {
                    mProgressBar.setMax(Integer.parseInt(String.valueOf(expenseSumProgressBar)));
                }



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
                break;
            //                right drawer
            case R.id.nav_currency:
                Toast.makeText(this, "Feature Not Available in the free version", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_Calculator:
                Toast.makeText(this, "Feature Not Available in the free version", Toast.LENGTH_LONG).show();
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
//                Toast.makeText(BalanceActivity.this, "Click one more time to exit app", Toast.LENGTH_SHORT).show();
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
