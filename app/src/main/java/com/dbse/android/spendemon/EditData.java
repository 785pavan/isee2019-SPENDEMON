package com.dbse.android.spendemon;

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

import androidx.appcompat.app.AppCompatActivity;

import com.dbse.android.spendemon.model.Entry;
import com.dbse.android.spendemon.model.entry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static android.widget.Toast.LENGTH_LONG;
import static com.dbse.android.spendemon.Summary.entries;

public class EditData extends AppCompatActivity implements android.widget.AdapterView.OnItemSelectedListener {


    private static final String TAG = "myTag";
    private entry entry;
    private Spinner sCategory;
    private Spinner sPaymentMethod;
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
        sPaymentMethod = findViewById(R.id.sPaymentMethod);
        sType.setOnItemSelectedListener(this);
        etAmount = findViewById(R.id.etAmount);
        etdate = findViewById(R.id.etDate);
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
                final String cat = sCategory.getSelectedItem().toString();
                String paymeth = sPaymentMethod.getSelectedItem().toString();
                final double amount;
                if (etAmount.getText().toString().equals("")) {
                    amount = 0;
                } else amount = Double.parseDouble(etAmount.getText().toString());
                Date date = new Date();
                try {
                    date = new SimpleDateFormat("dd/mm/yyyy").parse(etdate.getText().toString());
                } catch (ParseException e) {
                    Toast.makeText(getApplicationContext(), "Enter valid date", LENGTH_LONG).show();
                    e.printStackTrace();
                }
                if (paymeth.equals("---")) {
                    paymeth = "Not Defined";
                }

                Intent intent = new Intent(getApplicationContext(), Summary.class);
                Entry entry = new Entry(cat, amount, date, paymeth);
                entries.clear();
                entries.add(entry);
                ArrayList<Entry> Temp = getSavedObjectFromPreference(getApplicationContext(),
                        "summary", "entries");
                if (Temp == null) Temp = new ArrayList<Entry>();
                else Temp.addAll(entries);
                saveObjectToSharedPreference(getApplicationContext(),
                        "summary", "entries", Temp);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemSelected(android.widget.AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {

        String sp1 = String.valueOf(sType.getSelectedItem());
        Toast.makeText(this, sp1, Toast.LENGTH_SHORT).show();
        String[] paymentMethodsArray = getApplicationContext().getResources().getStringArray(R.array.PaymentMethods);
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, paymentMethodsArray);
        sPaymentMethod.setAdapter(dataAdapter3);
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
        Toast.makeText(getApplicationContext(), "No type selected", LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public static void saveObjectToSharedPreference(Context context, String preferenceFileName, String serializedObjectKey, Object object) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, 0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        final Gson gson = new Gson();
        String serializedObject = gson.toJson(object);
        sharedPreferencesEditor.putString(serializedObjectKey, serializedObject);
        sharedPreferencesEditor.apply();
    }

    public static <GenericClass> GenericClass getSavedObjectFromPreference(Context context, String preferenceFileName, String preferenceKey) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, 0);
        Type classType = new TypeToken<ArrayList<Entry>>() {
        }.getType();
        if (sharedPreferences.contains(preferenceKey)) {
            final Gson gson = new Gson();
            return gson.fromJson(sharedPreferences.getString(preferenceKey, ""), classType);
        }
        return null;
    }

    public static void deleteSharedPreferences(Context context, String preferenceFileName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, 0);
        sharedPreferences.edit().clear().apply();
    }
}