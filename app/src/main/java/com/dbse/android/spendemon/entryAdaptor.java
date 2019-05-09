package com.dbse.android.spendemon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dbse.android.spendemon.model.entry;

import java.util.List;

public class entryAdaptor extends
        RecyclerView.Adapter<entryAdaptor.ViewHolder> {

    private List<entry> entries;

    public entryAdaptor(List<entry> entries) {
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
        entry entry = entries.get(position);

        TextView tvCategoryItem = viewholder.tvCategoryItem;
        tvCategoryItem.setText(entry.getCategory());
        TextView tvAmountItem = viewholder.tvAmountItem;
        tvAmountItem.setText(String.valueOf(entry.getAmount()));
        TextView tvDateItem = viewholder.tvDateItem;
        tvDateItem.setText(String.valueOf(entry.getDate()));

    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCategoryItem;
        public TextView tvAmountItem;
        public TextView tvDateItem;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategoryItem = itemView.findViewById(R.id.tvCategoryItem);
            tvAmountItem = itemView.findViewById(R.id.tvAmountItem);
            tvDateItem = itemView.findViewById(R.id.tvDateItem);
        }
    }


}
