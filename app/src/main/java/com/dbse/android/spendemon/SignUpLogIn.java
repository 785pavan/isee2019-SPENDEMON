package com.dbse.android.spendemon;


//package com.example.loginpage;
//public class SignUpLogIn {
//}

//import android.support.constraint.ConstraintLayout;
//import android.support.v7.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Objects;

public class SignUpLogIn extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    private static final String TAG = "myTag";
    EditText usernameField;
    EditText passwordField;
    private String usernameA1 = null;
    private Integer passwordA1;
    private String[] usernameArray;
    private Integer[] passwordArray;
    TextView changeSignUpModeTextView;
    Boolean signUpModeActive;
    Button signUpButton;
    RelativeLayout relativeLayout;
    ConstraintLayout constraintLayout;
    ImageView teamLogo;
    SharedPreferences sharedPreferences;

    public void signUpOrLogIn(View view) {
        if (signUpModeActive) {
            Log.i("AppInfo", String.valueOf(usernameField.getText()));
            Log.i("AppInfo", String.valueOf(passwordField.getText()));
            if (String.valueOf(usernameField.getText()).equals(usernameA1)) {
                Log.i("Error1", "This user is already Registered");
                Toast.makeText(getApplicationContext(), "This username is already taken: ".concat(usernameA1), Toast.LENGTH_LONG).show();
            } else if (String.valueOf(usernameField.getText()).equals("")) {
                Log.i("Error2", "The Username field should not be empty");
                Toast.makeText(getApplicationContext(), "The Username field should not be empty", Toast.LENGTH_LONG).show();
            } else if (String.valueOf(passwordField.getText()).equals("")) {
                Log.i("Error4", "The Password field should not be empty");
                Toast.makeText(getApplicationContext(), "The Password field should not be empty", Toast.LENGTH_LONG).show();
            } else if (usernameA1 != null) {
                Log.i("Error3", "You have already Created a username");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.confirm_dialog_message)
                        .setTitle(R.string.confirm_dialog_title)
                        .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.i("Successful1", "Successfully created new sign Up!");
                                sharedPreferences.edit().putString("UserName", usernameField.getText().toString()).apply();
                                sharedPreferences.edit().putInt("Password", Integer.parseInt(passwordField.getText().toString())).apply();
                                usernameA1 = sharedPreferences.getString("UserName", null);
                                passwordA1 = sharedPreferences.getInt("Password", 0);
                                Toast.makeText(getApplicationContext(), "You are signed up as: ".concat(usernameA1), Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // CANCEL
                             }
                        });
                // Create the AlertDialog object and return it
                AlertDialog dialog = builder.create();
                dialog.show();

            } else {
                Log.i("Successful1", "Successful sign Up!");
                sharedPreferences.edit().putString("UserName", usernameField.getText().toString()).apply();
                sharedPreferences.edit().putInt("Password", Integer.parseInt(passwordField.getText().toString())).apply();


                /*usernameA1 = String.valueOf(usernameField.getText());
                passwordA1 = Integer.valueOf(String.valueOf(passwordField.getText()));*/
                Toast.makeText(getApplicationContext(), "You are signed up as: ".concat(usernameA1), Toast.LENGTH_LONG).show();
            }
        } else {

            if (String.valueOf(usernameField.getText()).equals(usernameA1)) {
                if (Integer.valueOf(String.valueOf(passwordField.getText())).equals(passwordA1)) {
                    Toast.makeText(getApplicationContext(), "You are Logged in as: ".concat(usernameA1), Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(this, Summary.class);
                    startActivity(intent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong password!", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Username was not found", Toast.LENGTH_LONG).show();
            }

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signuplogin);
        sharedPreferences = this.getSharedPreferences("SignUp", 0);
        usernameA1 = sharedPreferences.getString("UserName", null);
        passwordA1 = sharedPreferences.getInt("Password", 0);
        signUpModeActive = sharedPreferences.getBoolean("SignUpMode", false);
        signUpButton = (Button) findViewById(R.id.buttonSignUp);
        teamLogo = (ImageView) findViewById(R.id.teamLogo);
//        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout1);
        constraintLayout = (ConstraintLayout) findViewById(R.id.relativeLayout1);

        teamLogo.setOnClickListener(this);
//        relativeLayout.setOnClickListener(this);
        constraintLayout.setOnClickListener(this);

        usernameField = (EditText) findViewById(R.id.username1);
        passwordField = (EditText) findViewById(R.id.password1);
        changeSignUpModeTextView = (TextView) findViewById(R.id.changeSignUpMode);

        changeSignUpModeTextView.setOnClickListener(this);

//        usernameField.setOnKeyListener(this);
        passwordField.setOnKeyListener(this);
        Log.d(TAG, "onCreate: " + sharedPreferences.getBoolean("SignUpMode", false));
        Log.d(TAG, "signUpOrLogIn: " + usernameA1);
        Log.d(TAG, "signUpOrLogIn: " + passwordA1);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.changeSignUpMode) {
            if (signUpModeActive) {

                signUpModeActive = false;
                changeSignUpModeTextView.setText(getString(R.string.signUp1));
                signUpButton.setText(getString(R.string.LogIn1));


            } else {

                signUpModeActive = true;
                changeSignUpModeTextView.setText(getString(R.string.LogIn1));
                signUpButton.setText(getString(R.string.signUp1));

            }
            sharedPreferences.edit().putBoolean("SignUpMode", signUpModeActive).apply();
            Log.i("AppInfo", "Change the mode");
        } else if (v.getId() == R.id.teamLogo || v.getId() == R.id.relativeLayout1) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
            Log.i("AppInfo", "Clicked on");
        }


    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
//        return false;

        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
//            if (usernameField.getText() == null){
//                Log.i("Error4", " One of the fields are empty!");
//            }
            signUpOrLogIn(v);
        }

        return false;
    }
}


