package com.dbse.android.spendemon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class DetailsActivity extends AppCompatActivity {


    ImageView ivCat;
    ImageView ivPay;
    ImageView ivType;
    TextView tvAmount;
    TextView tvDate;
    TextView tvNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ivCat = findViewById(R.id.ivCatDetails);
        ivPay = findViewById(R.id.ivPaymentDetails);
        ivType = findViewById(R.id.ivTypeDetails);
        tvAmount = findViewById(R.id.tvAmountDetails);
        tvDate = findViewById(R.id.tvDateDetails);
        tvNotes = findViewById(R.id.tvNotesDetails);

        switch (intent.getStringExtra("Category")) {
            case "Rent":
                ivCat.setImageResource(R.drawable.rent);
                break;
            case "Insurance":
                ivCat.setImageResource(R.drawable.insurance);
                break;
            case "Groceries":
                ivCat.setImageResource(R.drawable.groceries);
                break;
            case "Travel":
                ivCat.setImageResource(R.drawable.travel);
                ;
                break;
            case "Restaurant":
                ivCat.setImageResource(R.drawable.restaurant);
                break;
            case "Allowance":
                ivCat.setImageResource(R.drawable.allowance);
                break;
            case "Salary":
                ivCat.setImageResource(R.drawable.salary);
                break;
            case "Bonds":
                ivCat.setImageResource(R.drawable.bonds);
                break;
            case "Bonus":
                ivCat.setImageResource(R.drawable.bonus);
                break;
            case "Gift":
                ivCat.setImageResource(R.drawable.wallet_giftcard);
                break;
            default:
                ivCat.setImageResource(R.drawable.defaultcat);
                ;
        }
        ivCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), intent.getStringExtra("Category"), Toast.LENGTH_LONG).show();
            }
        });

        switch (intent.getStringExtra("PaymentMethod")) {
            case "Cash":
                ivPay.setImageResource(R.drawable.cash_100);
                break;
            case "Card":
                ivPay.setImageResource(R.drawable.creditcard);
                break;
            case "PayPal":
                ivPay.setImageResource(R.drawable.paypal);
                break;
            case "GooglePay":
                ivPay.setImageResource(R.drawable.googlpay);
                break;
            case "ApplePay":
                ivPay.setImageResource(R.drawable.apple);
                break;

            default:
                ivPay.setImageResource(R.drawable.default_pay);

        }
        ivPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), intent.getStringExtra("PaymentMethod"), Toast.LENGTH_LONG).show();
            }
        });

        switch (intent.getStringExtra("Type")) {
            case "Incomes":
                ivType.setImageResource(R.drawable.plus_black);
                break;
            case "Expenses":
                ivType.setImageResource(R.drawable.minus_black);
                break;
            default:
                ivType.setImageResource(R.drawable.default_pay);
                break;

        }
        ivType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), intent.getStringExtra("Type"), Toast.LENGTH_LONG).show();
            }
        });


        tvAmount.setText(String.valueOf(intent.getDoubleExtra("Amount", 0.0)));
        tvNotes.setText(intent.getStringExtra("Notes"));
        tvDate.setText(intent.getStringExtra("Date"));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Edit Text", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
