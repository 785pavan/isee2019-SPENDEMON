package com.dbse.android.spendemon;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.util.Arrays;
import java.util.Calendar;

import static android.widget.Toast.LENGTH_LONG;

public class EditData extends AppCompatActivity
        implements android.widget.AdapterView.OnItemSelectedListener {


    private static final String TAG = "editData";
    Intent intent;
    ArrayAdapter arrayAdapter;
    String cat = "";
    String pay = "";
    int id = 0;
    private Spinner sCategory;
    private Spinner sPaymentMethod;
    private Spinner sType;
    private EditText etAmount;
    private TextView etdate;
    private EditText etDescription;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private SummaryViewModel summaryViewModel;

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
        etDescription = findViewById(R.id.etDescription);

        //Save menu created:
        intent = getIntent();
        String type = intent.getStringExtra("type");
        arrayAdapter = (ArrayAdapter) sType.getAdapter();
        int spinnerPos = arrayAdapter.getPosition(type);
        sType.setSelection(spinnerPos);
        if (intent.hasExtra("Category")) cat = intent.getStringExtra("Category");
        if (intent.hasExtra("PaymentMethod")) pay = intent.getStringExtra("PaymentMethod");

        if (intent.hasExtra("Date")) {
            etdate.setText(intent.getStringExtra("Date"));
        }
        /*ArrayAdapter arrayAdapterPay = (ArrayAdapter) sPaymentMethod.getAdapter();
        if (!intent.getStringExtra("PaymentMethod").equals("")) {
            int spinnerPay = arrayAdapterPay.getPosition(pay);
            sPaymentMethod.setSelection(spinnerPay);
        }*/
        if (intent.hasExtra("id")) {
            id = intent.getIntExtra("id", 0);
        }
        if (intent.hasExtra("Notes")) {
            etDescription.setText(intent.getStringExtra("Notes"));
        }
        if (intent.hasExtra("Amount")) {
            etAmount.setText(intent.getStringExtra("Amount"));
        }
        Log.d(TAG, "onCreate: " + type);
        summaryViewModel = ViewModelProviders.of(this).get(SummaryViewModel.class); //reference to current
        // activity given to view model object.


        etdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EditData.this, onDateSetListener,
                        year, month, day);
                dialog.show();

            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy: " + dayOfMonth + "/ " + month + "/ " + year);
                String date = dayOfMonth + "/" + month + "/" + year;
                etdate.setText(date);
                Log.d(TAG, "onDateSet: date " + etdate.getText().toString());
            }
        };

    }

    @Override
    public void onItemSelected(android.widget.AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {

        intent = getIntent();
        String sp1 = String.valueOf(sType.getSelectedItem());
        Toast.makeText(this, sp1, Toast.LENGTH_SHORT).show();
        String[] paymentMethodsArray = getApplicationContext().getResources()
                .getStringArray(R.array.PaymentMethods);
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, paymentMethodsArray);
        sPaymentMethod.setAdapter(dataAdapter3);
        if (sp1.contentEquals("Incomes")) {
            String[] incomes = getApplicationContext().getResources()
                    .getStringArray(R.array.Incomes);
            Log.d(TAG, "onCreate: " + Arrays.toString(incomes));
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item, incomes);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            sCategory.setAdapter(dataAdapter);
            arrayAdapter = (ArrayAdapter) sCategory.getAdapter();
            if (!cat.equals("")) {
                int spinnerCat = arrayAdapter.getPosition(cat);
                sCategory.setSelection(spinnerCat);
            }
        }
        if (sp1.contentEquals("Expenses")) {
            String[] expenses = getApplicationContext().getResources()
                    .getStringArray(R.array.Expenses);
            Log.d(TAG, "onCreate: " + Arrays.toString(expenses));

            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, expenses);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            sCategory.setAdapter(dataAdapter2);
            arrayAdapter = (ArrayAdapter) sCategory.getAdapter();
            if (!cat.equals("")) {
                int spinnerCat = arrayAdapter.getPosition(cat);
                sCategory.setSelection(spinnerCat);
            }

        }
    }

    public void saveTable() {
        String cat = sCategory.getSelectedItem().toString();
        String note = etDescription.getText().toString();
        String payMethod = sPaymentMethod.getSelectedItem().toString();
        final double amount;
        if (cat.equals("---")) {
            cat = "Not Defined";
        }

        if (note.equals("---")) {
            note = "Not Defined";
        }
        if (payMethod.equals("---")) {
            payMethod = "Not Defined";
        }
        if (etAmount.getText().toString().equals("")) {
            amount = 0;
        } else {
            amount = Double.parseDouble(etAmount.getText().toString());
        }
        String date = etdate.getText().toString();
        if (date.equals("")) {
            Calendar cal = Calendar.getInstance();
            date = cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
        }
        Table table = new Table(sType.getSelectedItem().toString(), cat, amount, date, payMethod, note);
        summaryViewModel.insert(table);
        final Intent intent = new Intent(getApplicationContext(), Summary.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_table) {
            if (id != 0) {
                summaryViewModel.delete(summaryViewModel.getTableById(id));
            }
            saveTable();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        Toast.makeText(getApplicationContext(), "No type selected", LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
