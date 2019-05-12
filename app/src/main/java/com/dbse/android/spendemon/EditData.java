package com.dbse.android.spendemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dbse.android.spendemon.model.entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EditData extends AppCompatActivity implements android.widget.AdapterView.OnItemSelectedListener {

    private static final String TAG = "myTag";
    private entry entry;
    private Spinner sCategory;
    private Spinner sType;
    private EditText etAmount;
    private EditText etdate;
    private Button bSave;
    private static final String FILE_NAME = "NewFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        sType = findViewById(R.id.sType);
        sCategory = findViewById(R.id.sCategory);
        sType.setOnItemSelectedListener(this);
        bSave = findViewById(R.id.bSave);
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        ArrayAdapter arrayAdapter = (ArrayAdapter) sType.getAdapter();
        int spinnerPos = arrayAdapter.getPosition(type);
        sType.setSelection(spinnerPos);
        Log.d(TAG, "onCreate: " + type);


        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //writeToJson();
                Intent intent = new Intent(getApplicationContext(), Summary.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemSelected(android.widget.AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        // TODO Auto-generated method stub
        String sp1 = String.valueOf(sType.getSelectedItem());
        Toast.makeText(this, sp1, Toast.LENGTH_SHORT).show();
        if (sp1.contentEquals("Incomes")) {
            String[] incomes = getApplicationContext().getResources().getStringArray(R.array.Incomes);
            Log.d(TAG, "onCreate: " + Arrays.toString(incomes));
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, incomes);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            sCategory.setAdapter(dataAdapter);
        }
        if (sp1.contentEquals("Expenses")) {
            String[] expenses = getApplicationContext().getResources().getStringArray(R.array.Expenses);
            Log.d(TAG, "onCreate: " + Arrays.toString(expenses));

            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, expenses);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            sCategory.setAdapter(dataAdapter2);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        Toast.makeText(getApplicationContext(), "No type selected", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = this.getSharedPreferences("Storage", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("catogory", sCategory.getSelectedItem().toString()).apply();
        Log.d(TAG, "onCreate: " + sharedPreferences.getString("catogory", ""));
    }



}
