package com.dbse.android.spendemon;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;

import java.util.ArrayList;

public class TrendLineActivity extends AppCompatActivity {

    private static String TAG = "TrendLine";

    private float[] yData = {25.4f, 10.6f, 66.76f, 44.32f, 46.0f, 16.8f, 23.6f};

    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend_line);
        Log.d(TAG, "onCreate: started");

        lineChart = findViewById(R.id.idTrend);
        Description desc = new Description();
        desc.setText("Expenditure");
        lineChart.setDescription(desc);

        addDataSet();

    }

    private void addDataSet() {
        Log.d(TAG, "addDataSet called");
        ArrayList<Entry> yAxes = new ArrayList<>();
        ArrayList<String> xAxes = new ArrayList<>();

        int i = 0;
        for (float data : yData) {
            yAxes.add(new Entry(data, i));
            xAxes.add(i, String.valueOf(data));
            i++;
        }


        LineDataSet lineDataSet = new LineDataSet(yAxes, "Cost");
        lineDataSet.
                lineDataSet.setValueTextSize(12);

        //Colors

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.MAGENTA);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);

        lineDataSet.setColors(colors);

        //add legend
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);

        //Pie data create

        PieData pieData = new PieData(lineDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
