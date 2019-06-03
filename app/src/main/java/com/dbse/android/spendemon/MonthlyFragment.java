package com.dbse.android.spendemon;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class MonthlyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monthly, container, false);
        ImageView ivDone = view.findViewById(R.id.ivDone);
        final TextView tvMonth = view.findViewById(R.id.tvMonth);
        ivDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PieChartActivity.class);
                intent.putExtra("Duration", "Month");
                intent.putExtra("Month", tvMonth.getText().toString());
                startActivity(intent);

            }
        });

        return inflater.inflate(R.layout.fragment_monthly, container, false);
    }

}
