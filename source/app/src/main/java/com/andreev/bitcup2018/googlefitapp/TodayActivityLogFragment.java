package com.andreev.bitcup2018.googlefitapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TodayActivityLogFragment extends Fragment {

    private TextView tvTodaySteps;
    private TextView tvTodayDistance;
    private TextView tvTodayCalories;

    int[] colors = new int[2];

    int steps[] = {10,20,30,40,50};
    double distance[] = { 100.0, 200.0, 300.0, 400.0, 500.0};
    int calories[] = { 100, 200, 300, 400, 500 };
    int time[] = { 10, 20, 30, 40, 50 };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.today_activity_log, container, false);

        tvTodaySteps = (TextView)view.findViewById(R.id.tv_total_today_steps);
        tvTodayDistance = (TextView)view.findViewById(R.id.tv_total_today_distance);
        tvTodayCalories = (TextView)view.findViewById(R.id.tv_total_today_calories);


        colors[0] = Color.parseColor("#bdc3c7");
        colors[1] = Color.parseColor("#3498db");

        LinearLayout linLayout = (LinearLayout) view.findViewById(R.id.linLayout);

        LayoutInflater ltInflater = getLayoutInflater();

        for (int i = steps.length - 1; i >= 0; i--) {
            //Log.d("myLogs", "i = " + i);
            View item = ltInflater.inflate(R.layout.activity_today_item, linLayout, false);
            TextView tvSteps = (TextView) item.findViewById(R.id.tv_today_steps_item);
            tvSteps.setText(String.valueOf(steps[i]));
            TextView tvDistance = (TextView) item.findViewById(R.id.tv_today_distance_item);
            tvDistance.setText("Distance: " + distance[i]);
            TextView tvCalories = (TextView) item.findViewById(R.id.tv_today_calories_item);
            tvCalories.setText("Calories: " + calories[i]);
            TextView tvTime = (TextView) item.findViewById(R.id.tv_today_time_item);
            tvTime.setText("Time: " + time[i]);
            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            item.setBackgroundColor(colors[i % 2]);
            linLayout.addView(item);
        }
        return view;
    }

    public void setTvTodaySteps(int steps) {
        tvTodaySteps.setText(steps);
    }

    public void setTvTodayDistance(double distance) {
        tvTodayDistance.setText(distance + "km");

    }

    public void setTvTodayCalories(int calories) {
        tvTodayCalories.setText(calories + "ccal");
    }
}
