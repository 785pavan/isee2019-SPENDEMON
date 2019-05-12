package com.dbse.android.spendemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.dbse.android.spendemon.model.entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EditData extends AppCompatActivity implements android.widget.AdapterView.OnItemSelectedListener {

    private entry entry;
    private Spinner sCategory;
    private Spinner sType;
    private EditText etAmount;
    private EditText etdate;
    private Button bSave;

    private Object AdapterView;




    private static final String FILE_NAME = "NewFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        bSave = findViewById(R.id.bSave);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Summary.class);
                startActivity(intent);
            }
        });
        sType = findViewById(R.id.sType);
        sCategory = findViewById(R.id.sCategory);
    }

    @Override
    public void onItemSelected(android.widget.AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        // TODO Auto-generated method stub
        String sp1= String.valueOf(sType.getSelectedItem());
        Toast.makeText(this, sp1, Toast.LENGTH_SHORT).show();
        if(sp1.contentEquals("Incomes")) {
            String[] incomes = getApplicationContext().getResources().getStringArray(R.array.Incomes);

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, incomes);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            sCategory.setAdapter(dataAdapter);
        }
        if(sp1.contentEquals("Expenses")) {
            String[] expenses = getApplicationContext().getResources().getStringArray(R.array.Expenses);
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
        sCategory = findViewById(R.id.sCategory);
        etAmount = findViewById(R.id.etAmount);
        etdate = findViewById(R.id.etDate);

        entry.setAmount(etAmount.getText().toString().equals("") ? 0.0 : Integer.parseInt(etAmount.getText().toString()));
        entry.setCategory(sCategory.getSelectedItem().toString());
        try {
            entry.setDate(new SimpleDateFormat("dd/mm/yyyy").parse(etdate.getText().toString()));
        } catch (ParseException e) {
            Toast.makeText(getApplicationContext(), "Date format wrong", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void writeToJson() {
        File file = new File(this.getFilesDir(), FILE_NAME);
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        String response = null;

        if (!file.exists()) {
            try {
                file.createNewFile();
                fileWriter = new FileWriter(file.getAbsoluteFile());
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("{}");
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        StringBuilder output = new StringBuilder();
        try {
            fileReader = new FileReader(file.getAbsoluteFile());
            bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                output.append(line).append("\n");
            }
            response = output.toString();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject messageDetails = new JSONObject(response);
            boolean isUserExisting = messageDetails.has("Username");
            if (!isUserExisting) {
                JSONArray newUserMessage = new JSONArray();
                int id = (int) (Math.random() * 100);
                newUserMessage.put(id);
                messageDetails.put("Username", newUserMessage);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
