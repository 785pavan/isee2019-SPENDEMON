package com.dbse.android.spendemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignInSignUpActivity extends AppCompatActivity {

    private Button signInButton;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_sign_up);

        signInButton = findViewById(R.id.sign_in_button);
        signupButton = findViewById(R.id.sign_up_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignIn();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUp();
            }
        });
    }

    private void openSignUp() {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    private void openSignIn() {
        Intent intent = new Intent(this, sign_in.class);
        startActivity(intent);
    }
}
