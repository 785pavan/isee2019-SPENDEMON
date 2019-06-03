package com.dbse.android.spendemon;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.TextView;
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
import java.util.Calendar;

import static com.dbse.android.spendemon.Summary.entries;

public class PieChartDailyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private static String TAG = "PieChart";
    /* private float[] yData = {25.4f, 10.6f, 66.76f, 44.32f, 46.0f, 16.8f, 23.6f};
     private String[] xData = {"Mitch", "Jessica", "Md", "Kelsey", "Sam", "Robert", "Ashley"};*/
    ArrayList<Float> AmountValues = new ArrayList<>();
    ArrayList<String> Categories = new ArrayList<>();

    ArrayList<Float> yData = new ArrayList<>();
    ArrayList<String> xData = new ArrayList<>();
    TextView tvDateDaily;
    PieChart pieChart;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_daily);
        tvDateDaily = findViewById(R.id.tvDateDaily);

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
        String date = tvDateDaily.getText().toString();

        addDataSet(date);

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
                Toast.makeText(PieChartDailyActivity.this, "Category: " + cat + "\n" +
                        "spent: " + cost, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    private void addDataSet(String date) {

        getData(date);


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

    private void getData(String date) {
        for (com.dbse.android.spendemon.model.Entry entry : entries) {
            if (entry.getDate().equals(date)) {
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
