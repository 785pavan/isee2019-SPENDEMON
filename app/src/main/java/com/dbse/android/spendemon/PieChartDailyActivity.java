package com.dbse.android.spendemon;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;
import java.util.Calendar;

public class PieChartDailyActivity extends AppCompatActivity {


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
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_daily);
        tvDateDaily = findViewById(R.id.tvDateDaily);
        ivDone = findViewById(R.id.ivDone);

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
                Intent intent = new Intent(getApplicationContext(), ChartDayActivity.class);
                intent.putExtra("Date", date);
                startActivity(intent);


            }
        });

    }
}
