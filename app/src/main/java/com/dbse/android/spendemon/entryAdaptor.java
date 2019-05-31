package com.dbse.android.spendemon;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dbse.android.spendemon.model.Entry;

import java.util.List;

public class entryAdaptor extends
        RecyclerView.Adapter<entryAdaptor.ViewHolder> {

    private List<Entry> entries;

    private String[] catImages = {
            "@drawable/default_cat.xml",
            "@drawable/rent.png",
            "@drawable/insurance.png",
            "@drawable/groceries.png",
            "@drawable/travel.xml",
            "@drawable/restaurant.xml",
            "@drawable/allowance.png",
            "@drawable/salary.png",
            "@drawable/bonds.png"
    };
    private String[] paymentImage = {
            "@drawable/default_pay.png",
            "@drawable/cash.png",
            "@drawable/card.xml",
            "@drawable/payPal.xml",
            "@drawable/google_pay.png",
            "@drawable/apple_pay.png"
    };

    private String[] typeImages = {
            "@drawable/plus.xml",
            "@drawable/minus.xml"
    };

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
        Entry entry = (Entry) entries.get(position);

        entry.setCatResource();
        entry.setTypeResource();
        entry.setPaymentResource();


        ImageView ivCat = viewholder.ivCat;
        ivCat.setImageDrawable(Drawable.createFromPath(catImages[entry.getCatResource()]));

        ImageView ivPayment = viewholder.ivPayment;
        ivPayment.setImageDrawable(Drawable.createFromPath(paymentImage[entry.getPaymentResource()]));
        ImageView ivType = viewholder.ivType;
        ivType.setImageDrawable(Drawable.createFromPath(typeImages[entry.getTypeResource()]));
        TextView tvCategoryItem = viewholder.tvCategoryItem;
        //tvCategoryItem.setText(entry.getCategory());
        TextView tvAmountItem = viewholder.tvAmountItem;
        tvAmountItem.setText(String.valueOf(entry.getAmount()));
        TextView tvDateItem = viewholder.tvDateItem;
        tvDateItem.setText(entry.getDate());
        TextView tvPayment = viewholder.tvPaymentMethodItem;
        //tvPayment.setText(entry.getPayMethod());

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
        public ImageView ivCat;
        public ImageView ivPayment;
        public ImageView ivType;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // tvCategoryItem = itemView.findViewById(R.id.tvCategoryItem);
            ivCat = itemView.findViewById(R.id.categoryImage);
            ivPayment = itemView.findViewById(R.id.paymentImage);
            ivType = itemView.findViewById(R.id.typeImage);
            tvAmountItem = itemView.findViewById(R.id.tvAmountItem);
            tvDateItem = itemView.findViewById(R.id.tvDateItem);
            //tvPaymentMethodItem = itemView.findViewById(R.id.tvPaymentMethodItem);
        }
    }


}
