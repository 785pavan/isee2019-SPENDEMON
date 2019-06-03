package com.dbse.android.spendemon;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;

import static com.dbse.android.spendemon.Summary.entries;

public class TrendLineActivity extends AppCompatActivity {

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
            lineDataSet = new LineDataSet(yAxesEx, "Sample Data");
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
            //lineDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
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
            lineDataSet3 = new LineDataSet(yAxesEx, "Sample Data");
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
            //lineDataSet3.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            lineDataSet3.setFormSize(15.f);
            if (Utils.getSDKInt() >= 18) {
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_blue);
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
}
