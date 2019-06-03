package com.dbse.android.spendemon;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import static com.dbse.android.spendemon.Summary.entries;

public class PieChartActivity extends AppCompatActivity {

    private static String TAG = "PieChart";
    /* private float[] yData = {25.4f, 10.6f, 66.76f, 44.32f, 46.0f, 16.8f, 23.6f};
     private String[] xData = {"Mitch", "Jessica", "Md", "Kelsey", "Sam", "Robert", "Ashley"};*/
    ArrayList<Float> AmountValues = new ArrayList<>();
    ArrayList<String> Categories = new ArrayList<>();

    ArrayList<Float> yData = new ArrayList<>();
    ArrayList<String> xData = new ArrayList<>();
    Intent intent;


    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = getIntent();
        setContentView(R.layout.activity_pie_chart);
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

        addDataSet();

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

        getData();


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
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        //add legend
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);

        //Pie data create

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
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
                String monthEntry = entry.getDate().substring(1, 3);
                if (monthEntry.equals(monthIntent)) {
                    AmountValues.add((float) entry.getAmount());
                    Categories.add(entry.getCategory());
                }
            } else if (intent.getStringExtra("Duration").equals("All")){
                AmountValues.add((float) entry.getAmount());
                Categories.add(entry.getCategory());
            }
        }
        float data = 0;
        for (int i = 0; i < Categories.size(); i++) {
            data = 0;
            for (int j = i; j < Categories.size(); j++) {
                if (Categories.get(i).equals(Categories.get(j))) {
                    data += AmountValues.get(j);
                }
            }
            yData.add(data);
            xData.add(Categories.get(i).equals("---") ? "Not Defined" : Categories.get(i));

            /*if (Categories.get(i).equals(Categories.get(i + 1))) {
                data += AmountValues.get(i + 1);
                continue;
            }
            yData.add(data);
            xData.add(Categories.get(i));*/

        }
    }
}
