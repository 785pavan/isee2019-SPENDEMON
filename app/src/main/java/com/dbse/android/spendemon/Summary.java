package com.dbse.android.spendemon;

import android.content.Intent;
import android.os.Bundle;

import com.dbse.android.spendemon.model.entry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Spinner;
import android.widget.TabWidget;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.List;

public class Summary extends AppCompatActivity {

    /*//TabWidget tabWidget ;
    TableLayout tableLayout;*/

    private List<entry> entries;
    private final String INCOME = "income";
    private final String EXPENSE = "expense";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*tableLayout = findViewById(R.id.tableLayout);
        //tabWidget = findViewById(R.id.tabWidget);
        TabWidget row1 = new TabWidget(this);
        TextView textView1 = new TextView(this);
        row1.addView(textView1);
        tableLayout.addView(row1);*/

        FloatingActionButton fabPlus = findViewById(R.id.fabPlus);
        fabPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditData.class);
                intent.putExtra(INCOME, "income");
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
        FloatingActionButton fabMinus = findViewById(R.id.fabMinus);
        fabMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EditData.class);
                intent.putExtra(EXPENSE,"expense");
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

}
