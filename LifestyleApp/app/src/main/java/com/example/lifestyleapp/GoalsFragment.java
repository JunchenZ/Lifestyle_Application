package com.example.lifestyleapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GoalsFragment extends Fragment{

    private TextView mTvGoals, mTvCurrentWeight, mTvTargetWeight, mTvCurrentBMI, mTvTargetCalorie;

    public GoalsFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the detail view
        View view = inflater.inflate(R.layout.view_weather, container, false);

        //Get the text view
        mTvGoals = (TextView) view.findViewById(R.id.tv_goal);
        mTvCurrentWeight = (TextView) view.findViewById(R.id.tv_currentWeight);
        mTvTargetWeight = (TextView) view.findViewById(R.id.tv_targetWeight);
        mTvCurrentBMI = (TextView) view.findViewById(R.id.tv_currentBMI);
        mTvTargetCalorie = (TextView) view.findViewById(R.id.tv_targetCalorie);

        mTvGoals.setText(getArguments().getString("goal"));
        mTvCurrentWeight.setText(getArguments().getString("current_weight"));
        mTvTargetWeight.setText(getArguments().getString("target_weight"));
        mTvCurrentBMI.setText(getArguments().getString("current_bmi"));
        mTvTargetCalorie.setText(getArguments().getString("target_calorie"));

        return view;
    }
}
