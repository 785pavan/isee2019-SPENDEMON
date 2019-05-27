package com.dbse.android.spendemon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dbse.android.spendemon.ui.chart.ChartFragment;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ChartFragment.newInstance())
                    .commitNow();
        }
    }
}
