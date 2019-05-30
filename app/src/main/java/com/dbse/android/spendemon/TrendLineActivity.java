package com.dbse.android.spendemon;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class TrendLineActivity extends AppCompatActivity {

    private static String TAG = "TrendLine";

    private float[] yExpense = {25.4f, 10.6f, 66.76f, 44.32f, 46.0f, 16.8f, 23.6f};
    private float[] yIncome = {10.6f, 66.76f, 44.32f, 46.0f, 16.8f, 23.6f, 25.4f};

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
        ArrayList<Entry> yAxesEx = new ArrayList<>();
        ArrayList<String> xAxes = new ArrayList<>();
        ArrayList<Entry> yAxesIn = new ArrayList<>();

        int i = 0, j = 0;
        for (float data : yExpense) {
            yAxesEx.add(new Entry(data, i));
            xAxes.add(i, String.valueOf(data));
            i++;
        }
        for (float data : yIncome) {
            yAxesIn.add(new Entry(data, j++));
        }
        String[] xaxes = new String[xAxes.size()];
        for (int k = 0; k < xAxes.size(); k++) {
            xaxes[k] = xAxes.get(k).toString();
        }

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();
        LineDataSet lineDataSet1 =new LineDataSet(yAxesEx,"Expenses");
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.BLUE);

        LineDataSet lineDataSet2 =new LineDataSet(yAxesIn,"Incomes");
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setColor(Color.RED);

        lineDataSets.add(lineDataSet1);
        lineDataSets.add(lineDataSet2);

        lineChart.setData(new LineData(lineDataSets));

        lineChart.setVisibleXRangeMaximum(65f);

    }
}
