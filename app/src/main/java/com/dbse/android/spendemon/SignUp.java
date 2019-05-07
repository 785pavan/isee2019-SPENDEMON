package com.dbse.android.spendemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    private EditText etName;
    private EditText etPassword;
    private EditText etPasswordRepeat;
    private EditText etPasswordRec;
    private Spinner spCurrency;
    private Spinner spLanguage;


    private static final String SHARED_PREFFS = "sharedPrefs";
    private static final String USERNAME = "UserName";
    private static final String PASSWORD = "password";
    private static final String RECOVERY = "recovery";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button signUpButton = findViewById(R.id.bSignUp);
        etPassword = findViewById(R.id.etPassword);
        etPasswordRepeat = findViewById(R.id.etPasswordRepeat);
        etPasswordRec = findViewById(R.id.etRecovery);
        spCurrency = findViewById(R.id.spCurrency);
        spLanguage = findViewById(R.id.spinnerLanguage);
        etName = findViewById(R.id.etName);
        signUpButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                saveData();

            }
        });
        Button toEdit = findViewById(R.id.toedit);
        toEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditData.class);
                startActivity(intent);
            }
        });
    }

    private void saveData() {
        if (etPassword.getText().toString().equals(etPasswordRepeat.getText().toString())) {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(USERNAME, etName.getText().toString());
            editor.putInt(PASSWORD, Integer.parseInt(etPassword.getText().toString()));
            editor.putInt(RECOVERY, Integer.parseInt(etPasswordRec.getText().toString()));

            editor.apply();
        } else {
            Toast.makeText(this, "Passwords Don't match", Toast.LENGTH_LONG).show();
        }
    }
}
