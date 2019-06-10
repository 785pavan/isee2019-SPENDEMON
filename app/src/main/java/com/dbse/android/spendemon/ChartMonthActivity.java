package com.dbse.android.spendemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ChartMonthActivity extends AppCompatActivity {

    TextView tvMonth;
    ImageView ivDone;
    private DrawerLayout drawer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_month);
        tvMonth = findViewById(R.id.tvMonthDaily);
        ivDone = findViewById(R.id.ivDone);
        ivDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PieChartActivity.class);
                intent.putExtra("Duration","Month");
                intent.putExtra("Month", tvMonth.getText().toString());
                startActivity(intent);
            }
        });
    }




}
