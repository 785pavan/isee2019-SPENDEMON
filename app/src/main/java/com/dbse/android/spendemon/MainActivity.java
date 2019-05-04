package com.dbse.android.spendemon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener{


    public void presskey1(View view){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        TextView myText = (TextView)findViewById(R.id.textView3);

        myText.setText("gotta take them all");

        myText.setTextSize(30);
//        switch (view.getId()){
//            case R.id.button:
//
//        }
    }
}
