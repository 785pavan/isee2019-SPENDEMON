package com.dbse.android.spendemon;


//package com.example.loginpage;
//public class SignUpLogIn {
//}

//import android.support.constraint.ConstraintLayout;
//import android.support.v7.app.AppCompatActivity;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SignUpLogIn extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener{

    EditText usernameField;
    EditText passwordField;
    private String usernameA1 = null;
    private Integer passwordA1;
    private String[] usernameArray;
    private Integer[] passwordArray;
    TextView  changeSignUpModeTextView;
    Boolean signUpModeActive;
    Button signUpbutton;
    RelativeLayout relativeLayout;
    ConstraintLayout constraintLayout;
    ImageView teamLogo;

    public void signUpOrLogIn(View view){
        if (signUpModeActive) {
            Log.i("AppInfo", String.valueOf(usernameField.getText()));
            Log.i("AppInfo", String.valueOf(passwordField.getText()));
            if (String.valueOf(usernameField.getText()).equals(usernameA1)) {
                Log.i("Error1", "This user is already Registered");
                Toast.makeText(getApplicationContext(), "This username is already taken: ".concat(usernameA1), Toast.LENGTH_LONG).show();
            } else if (String.valueOf(usernameField.getText()).equals("")) {
                Log.i("Error2", "The Username field should not be empty");
                Toast.makeText(getApplicationContext(), "The Username field should not be empty", Toast.LENGTH_LONG).show();
            }
            //        else if (!usernameA1.equals(null)){
            //            Log.i("Error3","You have already Created a username");
            //
            //        }
            else {
                Log.i("Successful1", "Successful sign Up!");
                usernameA1 = String.valueOf(usernameField.getText());
                passwordA1 = Integer.valueOf(String.valueOf(passwordField.getText()));
                Toast.makeText(getApplicationContext(), "You are signed up as: ".concat(usernameA1), Toast.LENGTH_LONG).show();
            }
        }else {

            if (String.valueOf(usernameField.getText()).equals(usernameA1)) {
                if (Integer.valueOf(String.valueOf(passwordField.getText())).equals(passwordA1)){
                    Toast.makeText(getApplicationContext(), "You are Logged in as: ".concat(usernameA1), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong password!", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "Username was not found", Toast.LENGTH_LONG).show();
            }

        }




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        signUpModeActive = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signuplogin);
        signUpbutton = (Button) findViewById(R.id.buttonSignUp);
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

        usernameField.setOnKeyListener(this);
        passwordField.setOnKeyListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.changeSignUpMode){
            if (signUpModeActive){

                signUpModeActive = false;
                changeSignUpModeTextView.setText("Sign Up");
                signUpbutton.setText("Log In");
            }else{

                signUpModeActive = true;
                changeSignUpModeTextView.setText("Log In");
                signUpbutton.setText("Sign Up");
            }
            Log.i("AppInfo", "Change the mode");
        } else if (v.getId() == R.id.teamLogo || v.getId() == R.id.relativeLayout1){
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
            Log.i("AppInfo", "Clicked on");
        }


    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
//        return false;

        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP){
            signUpOrLogIn(v);
        }

        return false;
    }
}


