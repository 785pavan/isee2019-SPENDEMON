package com.dbse.android.spendemon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dbse.android.spendemon.model.Entry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.dbse.android.spendemon.EditData.getSavedObjectFromPreference;


public class Summary extends AppCompatActivity {
    private static final String TAG = "myTag";
    private SummaryViewModel summaryViewModel; // object of View Model created.

    static ArrayList<Entry> entries = new ArrayList<>();
    private final String TYPE = "type";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        summaryViewModel = ViewModelProviders.of(this).get(SummaryViewModel.class); //reference to current
        // activity given to view model object.
        summaryViewModel.getTable().observe(this, new Observer<List<Table>>() {
            @Override
            public void onChanged(List<Table> tables) {


            }
        });


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView rvEntries = findViewById(R.id.rvEntries);
        Button bDelete = findViewById(R.id.bDelete);
        ArrayList<Entry> Temp = getSavedObjectFromPreference(getApplicationContext(),
                "summary", "entries");
        //readJson();
        entries.clear();

        if (Temp != null) {
            entries.addAll(Temp);
        }
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
                Intent intent = new Intent(getApplicationContext(), EditData.class);
                intent.putExtra(TYPE, "Expenses");
                startActivity(intent);

            }
        });
        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TrendLineActivity.class);
                startActivity(intent);
                /*EditData.deleteSharedPreferences(getApplicationContext(),
                        "summary");*/
            }
        });

    }

    private void readJson() {

        try {
            JSONArray jArray = new JSONArray(readJSONFromAsset());
            for (int i = 0; i < jArray.length(); ++i) {
                Entry en = new Entry();
                String Category = jArray.getJSONObject(i).getString("Category");
                Log.d("myTag", Category);
                String PaymentMethod = jArray.getJSONObject(i).getString("PaymentMethod");
                Log.d("myTag", PaymentMethod);
                double Amount = jArray.getJSONObject(i).getDouble("Amount");
                if (jArray.getJSONObject(i).getString("Type").equals("Expense")) {
                    Amount = -Amount;
                }
                Log.d("myTag", String.valueOf(Amount));
                Date date = new Date(jArray.getJSONObject(i).getString("Date"));
                Log.d("myTag", date.toString());
                en.setCategory(Category);
                en.setAmount(Amount);
                en.setPayMethod(PaymentMethod);
                en.setDate(date);
                Log.d("myTag", en.toString());
                entries.add(en);
                Log.d("myTag", entries.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private String readJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("ExpenseIncomeData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}