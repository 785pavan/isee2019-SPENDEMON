package com.dbse.android.spendemon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.dbse.android.spendemon.model.entry;

public class EditData extends AppCompatActivity {

    private entry entry;
    private Spinner sCategory;
    private EditText etAmount;
    private EditText etdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        sCategory = findViewById(R.id.sCategory);
        etAmount = findViewById(R.id.etAmount);
        etdate = findViewById(R.id.etDate);

        entry.setAmount(Integer.parseInt(etAmount.getText().toString()));
        entry.setCategory(sCategory.getSelectedItem().toString());
        try {
            entry.setDate(new SimpleDateFormat("dd/mm/yyyy").parse(etdate.getText().toString()));
        } catch (ParseException e) {
            Toast.makeText(getApplicationContext(),"Date format wrong",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
