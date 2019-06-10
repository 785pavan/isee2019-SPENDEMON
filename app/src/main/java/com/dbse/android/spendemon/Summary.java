package com.dbse.android.spendemon;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dbse.android.spendemon.model.Entry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import java.util.List;


public class Summary extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "myTag";
    static ArrayList<Entry> entries = new ArrayList<>();
    private final String TYPE = "type";
    private SummaryViewModel summaryViewModel; // object of View Model created.
    private DrawerLayout drawer1;



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


//        adding the navigation bar code:

        drawer1 = findViewById(R.id.drawer_layout_sum);


        NavigationView navigationView1 = findViewById(R.id.navigation_view1_Sum);

        NavigationView navigationView2 = findViewById(R.id.navigation_view2_Sum);

        navigationView1.setNavigationItemSelectedListener(this);

        navigationView2.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle1 = new ActionBarDrawerToggle(this, drawer1, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer1.addDrawerListener(toggle1);
        toggle1.syncState();


        RecyclerView rvEntries = findViewById(R.id.rvEntries);
        //Button bDelete = findViewById(R.id.bDelete);

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
                Toast.makeText(getApplicationContext(), "minus", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), EditData.class);
                intent.putExtra(TYPE, "Expenses");
                startActivity(intent);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.filter_summary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter:
                final Intent intent = new Intent(getApplicationContext(), FilterActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public void onBackPressed() {
        if (true) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else {
            super.onBackPressed();
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}