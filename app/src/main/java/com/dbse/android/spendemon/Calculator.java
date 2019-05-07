package com.dbse.android.spendemon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.text.DecimalFormat;

public class Calculator extends AppCompatActivity {


    private char CURRENT_ACTION;

    private double valueOne = Double.NaN;
    private double valueTwo;

    private DecimalFormat decimalFormat;
    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

    }
}
