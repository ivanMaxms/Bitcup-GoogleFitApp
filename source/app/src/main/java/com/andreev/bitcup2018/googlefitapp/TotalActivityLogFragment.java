package com.andreev.bitcup2018.googlefitapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TotalActivityLogFragment extends Fragment {

    int steps[] = {10,20,30,40,50};
    double distance[] = { 100.0, 200.0, 300.0, 400.0, 500.0};
    int date[] = { 1, 2, 3, 4, 5};
    int colors[] = new int[2];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.total_activity_log, container, false);

        colors[0] = Color.parseColor("#bdc3c7");
        colors[1] = Color.parseColor("#3498db");

        LinearLayout linLayout = (LinearLayout) view.findViewById(R.id.lin_layout_total);

        LayoutInflater ltInflater = getLayoutInflater();

        for (int i = steps.length - 1; i >= 0; i--) {
            //Log.d("myLogs", "i = " + i);
            View item = ltInflater.inflate(R.layout.activity_total_item, linLayout, false);
            TextView tvSteps = (TextView) item.findViewById(R.id.tv_total_steps_item);
            tvSteps.setText(String.valueOf(steps[i]));
            TextView tvDistance = (TextView) item.findViewById(R.id.tv_total_distance_item);
            tvDistance.setText("Distance: " + distance[i]);
            TextView tvDate = (TextView) item.findViewById(R.id.tv_total_date_item);
            tvDate.setText("Date: " + date[i]);
            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            item.setBackgroundColor(colors[i % 2]);
            linLayout.addView(item);
        }

        return view;
    }
}
