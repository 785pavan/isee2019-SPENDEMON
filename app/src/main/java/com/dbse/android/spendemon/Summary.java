package com.dbse.android.spendemon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dbse.android.spendemon.model.Entry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

//import static com.dbse.android.spendemon.EditData.getSavedObjectFromPreference;


public class Summary extends AppCompatActivity  {
    private static final String TAG = "myTag";
    static ArrayList<Entry> entries = new ArrayList<>();
    private final String TYPE = "type";
    private SummaryViewModel summaryViewModel; // object of View Model created.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        summaryViewModel = ViewModelProviders.of(this).get(SummaryViewModel.class); //reference to current
        // activity given to view model object.
        summaryViewModel.getTable().observe(this, new Observer<List<Table>>() {
            @Override
            public void onChanged(List<Table> tables) {
                entries.clear();
                for (Table table : tables) {
                    Entry e = new Entry(table.getType(), table.getCategory(), table.getAmount(), table.getDate(), table.getPaymethod(),
                            table.getNote());
                    entries.add(e);
                }
                RecyclerView rvEntries = findViewById(R.id.rvEntries);
                entryAdaptor adaptor = new entryAdaptor(entries);
                rvEntries.setAdapter(adaptor);
                rvEntries.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView rvEntries = findViewById(R.id.rvEntries);
        Button bDelete = findViewById(R.id.bDelete);

        entryAdaptor adaptor = new entryAdaptor(entries);
        rvEntries.setAdapter(adaptor);
        rvEntries.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fabPlus = findViewById(R.id.fabPlus);
        fabPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditData.class);
                intent.putExtra(TYPE, "Incomes");
                startActivity(intent);
            }
        });
        FloatingActionButton fabMinus = findViewById(R.id.fabMinus);
        fabMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "minus",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), EditData.class);
                intent.putExtra(TYPE, "Expenses");
                startActivity(intent);

            }
        });
        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PieChartActivity.class);
                startActivity(intent);
                /*EditData.deleteSharedPreferences(getApplicationContext(),
                        "summary");*/
            }
        });

    }



}