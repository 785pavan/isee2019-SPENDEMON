package com.dbse.android.spendemon;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinner;
import com.androidbuts.multispinnerfilter.MultiSpinnerListener;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import com.androidbuts.multispinnerfilter.SpinnerListener;
import com.dbse.android.spendemon.model.Entry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import static com.dbse.android.spendemon.R.array.PaymentMethods;
import static com.dbse.android.spendemon.Summary.entries;


public class FilterActivity extends AppCompatActivity {
    private static final String TAG = "filterData";
    private DatePickerDialog.OnDateSetListener onDateSetListener, onDateSetListener1;
    private TextView StartDate;
    private TextView EndDate;
    List<String> types = new ArrayList<>();
    List<String> paymeths = new ArrayList<>();
    final List<KeyPairBoolData> listArray0 = new ArrayList<>();
    final List<KeyPairBoolData> listArray1 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        MultiSpinnerSearch typeSpinner = findViewById(R.id.TypeFilter);
        MultiSpinnerSearch paymethSpinner = findViewById(R.id.PaymentMethodFilter);

        types = Arrays.asList(getResources().getStringArray(R.array.type));
        paymeths = Arrays.asList(getResources().getStringArray(PaymentMethods));
        StartDate = findViewById(R.id.StartDateFilter);
        EndDate = findViewById(R.id.EndDateFilter);
        for (int i = 0; i < types.size(); i++) {
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(i + 1);
            h.setName(types.get(i));
            h.setSelected(false);
            listArray0.add(h);
        }
        for (int i = 0; i < paymeths.size(); i++) {
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(i + 1);
            h.setName(paymeths.get(i));
            h.setSelected(false);
            listArray1.add(h);
        }


        typeSpinner.setItems(listArray0, -1, new SpinnerListener() {
                    @Override
                    public void onItemsSelected(List<KeyPairBoolData> items) {
                        for (int i = 0; i < items.size(); i++) {
                            if (items.get(i).isSelected()) {
                                Log.i(TAG, i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());
                            }
                        }
                    }
                });
        paymethSpinner.setItems(listArray1, -1, new SpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> items) {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isSelected()) {
                        Log.i(TAG, i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());
                    }
                }
            }
        });

        StartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        FilterActivity.this, onDateSetListener,
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
                StartDate.setText(date);
                Log.d(TAG, "onDateSet Startdate : date " + StartDate.getText().toString());
            }
        };
        EndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog1 = new DatePickerDialog(
                        FilterActivity.this, onDateSetListener1,
                        year, month, day);
                dialog1.show();

            }
        });

        onDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy: " + dayOfMonth + "/ " + month + "/ " + year);
                String date = dayOfMonth + "/" + month + "/" + year;
                EndDate.setText(date);
                Log.d(TAG, "onDateSet endDate: date " + EndDate.getText().toString());
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.filter1_summary, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter1:
                Toast.makeText(getApplicationContext(),"Filter initiated", Toast.LENGTH_LONG).show();
                String startDate = StartDate.getText().toString();
                String endDate = EndDate.getText().toString();
                String[] typeSending = new String[types.size()];
                String[] paySend = new String[paymeths.size()];
                for (int i = 0; i<types.size();i++){
                    typeSending[i] = types.get(i);
                }
                for (int i =0;i<paymeths.size();i++){
                    paySend[i] = paymeths.get(i);
                }
                Intent intent = new Intent(getApplicationContext(),Summary.class);
                intent.putExtra("Types",typeSending);
                intent.putExtra("StartDate",startDate);
                intent.putExtra("EndDate",endDate);
                intent.putExtra("PayMethod",paySend);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
