package com.dbse.android.spendemon;

import android.content.Intent;

import java.util.ArrayList;

import static com.dbse.android.spendemon.Summary.entries;

public class BooleanActivity {


    ArrayList<Float> AmountValues = new ArrayList<>();
    ArrayList<String> Categories = new ArrayList<>();

    ArrayList<Float> yData = new ArrayList<>();
    ArrayList<String> xData = new ArrayList<>();
    Intent intent;


    private void getData() {
        for (com.dbse.android.spendemon.model.Entry entry : entries) {
            if ((intent.getStringExtra("Duration")).equals("Day")) {
                if (entry.getDate().equals(intent.getStringExtra("Date"))) {
                    AmountValues.add((float) entry.getAmount());
                    Categories.add(entry.getCategory());
                }
            } else if (intent.getStringExtra("Duration").equals("Month")) {
                String monthIntent = intent.getStringExtra("Month");
                String monthEntry = entry.getDate().substring(2, 3);
                if (entry.getDate().substring(1, 2).equals("/")) {
                    monthEntry = entry.getDate().substring(2, 3);
                } else if (entry.getDate().substring(2, 3).equals("/")) {
                    monthEntry = entry.getDate().substring(3, 4);
                }

                if (monthEntry.equals(monthIntent)) {
                    AmountValues.add((float) entry.getAmount());
                    Categories.add(entry.getCategory());
                }
            } else if (intent.getStringExtra("Duration").equals("All")) {
                AmountValues.add((float) entry.getAmount());
                Categories.add(entry.getCategory());
            }
        }
    }
}
