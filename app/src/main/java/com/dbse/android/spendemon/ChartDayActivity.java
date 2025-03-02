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

public class ChartDayActivity extends AppCompatActivity {
    private static String TAG = "Chart";


    ArrayList<Float> AmountValues = new ArrayList<>();
    ArrayList<String> Categories = new ArrayList<>();
    ArrayList<Float> yData = new ArrayList<>();
    ArrayList<String> xData = new ArrayList<>();
    PieChart pieChart;
    String date;
    //private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_day);
        Intent intent = getIntent();
        date = intent.getStringExtra("Date");
        Log.d(TAG, "onCreate: Start of creation of chart");
        pieChart = findViewById(R.id.idPieChartDaily);
        Description description = new Description();
        description.setText("Expenditure");
        pieChart.setDescription(description);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(25);
        pieChart.setCenterText("Expenditure");
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
                Toast.makeText(ChartDayActivity.this, "Category: " + cat + "\n" +
                        "spent: " + cost, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    private void addDataSet(/*String date*/) {
        Log.i("clicked", "addData" +
                "set");
        getData(/*date*/);


        Log.d(TAG, "addDataSet called");
        ArrayList<PieEntry> yEntries = new ArrayList<>();

        int i = 0;
        for (float data : yData) {
            yEntries.add(new PieEntry(data, i++));
        }

        PieDataSet pieDataSet = new PieDataSet(yEntries, "Cost");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //Colors

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.DKGRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.MAGENTA);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.GRAY);

        pieDataSet.setColors(colors);

        //add legend
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);

        //Pie data create

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private void getData(/*String date*/) {
        yData.clear();
        for (com.dbse.android.spendemon.model.Entry entry : entries) {
            //if (entry.getDate().equals(date)) {
            AmountValues.add((float) entry.getAmount());
            Categories.add(entry.getCategory());
            //}
        }
        float data ;
        boolean save_data;
        for (int i = 0; i < Categories.size(); i++) {
            data = 0;
            for (int j = i; j < Categories.size(); j++) {
                if (Categories.get(i).equals(Categories.get(j))) {
                    data += AmountValues.get(j);
                }
            }
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
//            else {
//                save_data = true;
//            }


        }
    }

}
