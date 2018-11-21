package com.andreev.bitcup2018.googlefitapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class DashboardFragment extends Fragment implements View.OnClickListener{
    private Button btnSet;
    private TextView tvPerсent;
    private TextView tvSteps;
    private TextView tvDistance;
    private TextView tvCalories;
    private TextView tvLeftToGo;
    private TextView tvAverage;

    private SharedPreferences sPref;
    private ProgressBar progressBar;

    String dailyGoal;

    private int preferedSteps = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.dashboard_fragment, container, false);

        tvSteps = (TextView)view.findViewById(R.id.tv_today_steps);
        btnSet = (Button)view.findViewById(R.id.btn_set_steps);
        tvPerсent = (TextView)view.findViewById(R.id.tv_persent);
        tvDistance = (TextView)view.findViewById(R.id.tv_distance);
        tvCalories = (TextView)view.findViewById(R.id.tv_calories);
        tvLeftToGo = (TextView)view.findViewById(R.id.tv_left_to_go);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);

        SharedPreferences sPref = getActivity().getApplicationContext().getSharedPreferences("DAILY_GOAL", Context.MODE_PRIVATE);
        dailyGoal = sPref.getString("goalValue", "");

        tvLeftToGo.setText(dailyGoal);

        btnSet.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Input steps");

        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                preferedSteps = Integer.parseInt(input.getText().toString());
                Toast.makeText(getContext(), "Steps = " + preferedSteps, Toast.LENGTH_LONG).show();
                tvLeftToGo.setText(String.valueOf(preferedSteps));
                dailyGoal = String.valueOf(preferedSteps);
                sPref = getActivity().getApplicationContext().getSharedPreferences("DAILY_GOAL", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString("goalValue", preferedSteps + "");
                ed.commit();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void setTvPerсent(int current) {
        if(Integer.parseInt(this.dailyGoal) < 1)
            return;
        float calculatePercent = ((float)current/(float)Integer.parseInt(this.dailyGoal)) *100;
        int truePercent = (int) calculatePercent;
        progressBar.setProgress(truePercent);
        tvPerсent.setText(truePercent + "%");
    }

    public void setTvSteps(int steps) {
        tvSteps.setText(String.valueOf(steps));
    }

    public void setTvDistance(double distance) {
        tvDistance.setText(distance + "km");
    }

    public void setTvCalories(float calories) {
        tvCalories.setText(calories + "ccal");
    }

    public void setTvLeftToGo() {
        int num = preferedSteps - Integer.parseInt(tvSteps.getText().toString());
        if(num > 0) tvLeftToGo.setText(num);
        else tvLeftToGo.setText(0);
    }

    public void setTvAverage(int average) {
        tvAverage.setText(average);
    }
}
