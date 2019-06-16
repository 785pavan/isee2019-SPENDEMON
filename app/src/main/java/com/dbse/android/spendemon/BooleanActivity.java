package com.dbse.android.spendemon;

import android.content.Intent;

import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;

import static com.dbse.android.spendemon.Summary.entries;

public class BooleanActivity {

    private DrawerLayout drawer1;


    ArrayList<Float> iData = new ArrayList<>();
    ArrayList<Float> eData = new ArrayList<>();
    Intent intent;


    private void getData() {
        for (com.dbse.android.spendemon.model.Entry entry : entries) {
            if(entry.getType().equals("Incomes")){
                iData.add((float)entry.getAmount());
            }else if (entry.getType().equals("Expenses")){
                eData.add((float)entry.getAmount());
            }
        }
    }


}
