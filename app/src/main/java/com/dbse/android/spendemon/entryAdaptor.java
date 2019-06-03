package com.dbse.android.spendemon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dbse.android.spendemon.model.Entry;

import java.util.List;

public class entryAdaptor extends
        RecyclerView.Adapter<entryAdaptor.ViewHolder> {

    private List<Entry> entries;


    public entryAdaptor(List<Entry> entries) {
        this.entries = entries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemEntry = inflater.inflate(R.layout.activity_item_entry, parent, false);

        return new ViewHolder(itemEntry);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, int position) {
        final Entry entry = (Entry) entries.get(position);


        ImageView ivCat = viewholder.ivCat;
        switch (entry.getCategory()) {
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
                Toast.makeText(v.getContext(), "Category: " +  entry.getCategory(), Toast.LENGTH_LONG).show();
            }
        });

        //ivCat.setImageDrawable(Drawable.createFromPath(catImages[entry.getCatResource()]));

        ImageView ivPayment = viewholder.ivPayment;
        switch (entry.getPayMethod()) {
            case "Cash":
                ivPayment.setImageResource(R.drawable.cash_100);
                break;
            case "Card":
                ivPayment.setImageResource(R.drawable.creditcard);
                break;
            case "PayPal":
                ivPayment.setImageResource(R.drawable.paypal);
                break;
            case "GooglePay":
                ivPayment.setImageResource(R.drawable.googlpay);
                break;
            case "ApplePay":
                ivPayment.setImageResource(R.drawable.apple);
                break;

            default:
                ivPayment.setImageResource(R.drawable.default_pay);

        }
        ivPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Payment Method: " + entry.getPayMethod(), Toast.LENGTH_LONG).show();
            }
        });
        //ivPayment.setImageDrawable(Drawable.createFromPath(paymentImage[entry.getPaymentResource()]));
        ImageView ivType = viewholder.ivType;
        switch (entry.getType()) {
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
                Toast.makeText(v.getContext(),"Type: " + entry.getType(),Toast.LENGTH_LONG).show();
            }
        });
        //ivType.setImageDrawable(Drawable.createFromPath(typeImages[entry.getTypeResource()]));
        TextView tvCategoryItem = viewholder.tvCategoryItem;
        //tvCategoryItem.setText(entry.getCategory());
        TextView tvAmountItem = viewholder.tvAmountItem;
        tvAmountItem.setText(String.valueOf(entry.getAmount()));
        TextView tvDateItem = viewholder.tvDateItem;
        tvDateItem.setText(entry.getDate());
        TextView tvPayment = viewholder.tvPaymentMethodItem;
        TextView tvNotes = viewholder.tvNotes;
        tvNotes.setText(entry.getNote());
        //tvPayment.setText(entry.getPayMethod());
        RelativeLayout rvItem = viewholder.rvItem;
        rvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),DetailsActivity.class);
                intent.putExtra("Category",entry.getCategory());
                intent.putExtra("Amount",entry.getAmount());
                intent.putExtra("Type",entry.getType());
                intent.putExtra("Notes",entry.getNote());
                intent.putExtra("PaymentMethod",entry.getPayMethod());
                intent.putExtra("Date",entry.getDate());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCategoryItem;
        public TextView tvAmountItem;
        public TextView tvDateItem;
        public TextView tvPaymentMethodItem;
        public TextView tvNotes;
        public ImageView ivCat;
        public ImageView ivPayment;
        public ImageView ivType;
        public RelativeLayout rvItem;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // tvCategoryItem = itemView.findViewById(R.id.tvCategoryItem);
            ivCat = itemView.findViewById(R.id.categoryImage);
            ivPayment = itemView.findViewById(R.id.paymentImage);
            ivType = itemView.findViewById(R.id.typeImage);
            tvAmountItem = itemView.findViewById(R.id.tvAmountItem);
            tvDateItem = itemView.findViewById(R.id.tvDateItem);
            tvNotes = itemView.findViewById(R.id.tvNotes);
            rvItem = itemView.findViewById(R.id.rootItem);
            //tvPaymentMethodItem = itemView.findViewById(R.id.tvPaymentMethodItem);
        }
    }


}
