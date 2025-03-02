package com.dbse.android.spendemon;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import com.androidbuts.multispinnerfilter.SpinnerListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static com.dbse.android.spendemon.R.array.AllCategories;
import static com.dbse.android.spendemon.R.array.PaymentMethods;
import static java.lang.String.valueOf;


public class FilterActivity extends AppCompatActivity {
    private static final String TAG = "filterData";
    private DatePickerDialog.OnDateSetListener onDateSetListener, onDateSetListener1;
    private TextView StartDate;
    private TextView EndDate;
    List<String> types = new ArrayList<>();
    List<String> paymeths = new ArrayList<>();
    List<String> cats = new ArrayList<>();
    final List<KeyPairBoolData> listArray0 = new ArrayList<>();
    final List<KeyPairBoolData> listArray1 = new ArrayList<>();
    final List<KeyPairBoolData> listArray2 = new ArrayList<>();
    List<String> typeSelected = new ArrayList<>();
    List<String> paymethSelected = new ArrayList<>();
    List<String> catsSelected = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        MultiSpinnerSearch typeSpinner = findViewById(R.id.TypeFilter);
        MultiSpinnerSearch paymethSpinner = findViewById(R.id.PaymentMethodFilter);
        MultiSpinnerSearch catSpinner = findViewById(R.id.CategoryFilter);

        types = Arrays.asList(getResources().getStringArray(R.array.type));
        paymeths = Arrays.asList(getResources().getStringArray(PaymentMethods));
        cats = Arrays.asList(getResources().getStringArray(AllCategories));
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
        for (int i = 0; i < cats.size(); i++) {
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(i + 1);
            h.setName(cats.get(i));
            h.setSelected(false);
            listArray2.add(h);
        }

        typeSpinner.setItems(listArray0, -1, new SpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> items) {
                for (int i = 0; i < items.size(); i++) {
                    //Log.i(TAG, i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());
                    if (items.get(i).isSelected()) {
                        typeSelected.add(items.get(i).getName());
                    }

                }

            }


        });

        paymethSpinner.setItems(listArray1, -1, new SpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> items) {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isSelected()) {
                        //Log.i(TAG, i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());
                        paymethSelected.add(items.get(i).getName());
                    }
                }
            }
        });
        catSpinner.setItems(listArray2, -1, new SpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> items) {
                int j = 0;
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isSelected()) {
                        //Log.i(TAG, i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());
                        catsSelected.add(items.get(i).getName());
                        j++;
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
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter1:
                Toast.makeText(getApplicationContext(), "Filter initiated", Toast.LENGTH_LONG).show();
                String startDate = StartDate.getText().toString();
                String endDate = EndDate.getText().toString();
                if (startDate.equals("Select date")||endDate.equals("Select date")){
                    Intent intent = new Intent(getApplicationContext(), Summary.class);
                    if (typeSelected!=null && typeSelected.size()!=0)
                        intent.putExtra("Types", typeSelected.toArray(new String[0]));
                    else
                        intent.putExtra("Types", types.toArray(new String[0]));
                    if (!startDate.equals("Select date"))
                        intent.putExtra("StartDate", startDate);
                    if (!endDate.equals("Select date")) intent.putExtra("EndDate", endDate);
                    if (paymethSelected!=null && paymethSelected.size()!=0)
                        intent.putExtra("PayMethod", paymethSelected.toArray(new String[0]));
                    else
                        intent.putExtra("PayMethod", paymeths.toArray(new String[0]));
                    if (catsSelected!=null && catsSelected.size()!=0)
                        intent.putExtra("Categories", catsSelected.toArray(new String[0]));
                    else
                        intent.putExtra("Categories", cats.toArray(new String[0]));

                    startActivity(intent);
                    return true;

                }
                if (Summary.compareDate(startDate, endDate) == 1) {
                    Toast.makeText(getApplicationContext(), "Start date should be before End Date", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), Summary.class);
                    if (typeSelected!=null && typeSelected.size()!=0)
                        intent.putExtra("Types", typeSelected.toArray(new String[0]));
                    else
                        intent.putExtra("Types", types.toArray(new String[0]));
                    if (!startDate.equals("Select date"))
                        intent.putExtra("StartDate", startDate);
                    if (!endDate.equals("Select date")) intent.putExtra("EndDate", endDate);
                    if (paymethSelected!=null && paymethSelected.size()!=0)
                        intent.putExtra("PayMethod", paymethSelected.toArray(new String[0]));
                    else
                        intent.putExtra("PayMethod", paymeths.toArray(new String[0]));
                    if (catsSelected!=null && catsSelected.size()!=0)
                        intent.putExtra("Categories", catsSelected.toArray(new String[0]));
                    else
                        intent.putExtra("Categories", cats.toArray(new String[0]));

                    startActivity(intent);
                    return true;
                }


//            case android.R.id.home:
//                onBackPressed();
//                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
