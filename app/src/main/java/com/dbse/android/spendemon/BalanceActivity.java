package com.dbse.android.spendemon;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import static com.dbse.android.spendemon.Summary.entries;

public class BalanceActivity extends AppCompatActivity {


    ArrayList<Float> iData = new ArrayList<>();
    ArrayList<Float> eData = new ArrayList<>();
    float balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        TextView textView = findViewById(R.id.balanceTextView);

        for (com.dbse.android.spendemon.model.Entry entry : entries) {
            if(entry.getType().equals("Incomes")){
                iData.add((float)entry.getAmount());
            }else if (entry.getType().equals("Expenses")){
                eData.add((float)entry.getAmount());
            }
        }
        balance = 0;
        for(float index : iData){
            balance += index;
        }
        for(float index : eData){
            balance -= index;
        }
//        string should only have two numbers after the float
        String string = String.valueOf((float) ((int) ( balance * 100)) / 100);

        if (balance > 0){
            string = "+  " + string;
            textView.setTextColor(Color.parseColor("green"));
        }else if (balance < 0){
            string = "-  " + string.substring(1);
            textView.setTextColor(Color.parseColor("red"));
        }else{
            textView.setTextColor(Color.parseColor("black"));
        }
        textView.setText(string);






    }
}
