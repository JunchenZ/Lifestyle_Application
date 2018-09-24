package com.example.lifestyleapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class EditGoalsFragment extends Fragment{
    private Spinner mSpGoals, mSpActivity, mSpTargetWeight, mSpTargetYear, mSpTargetMonth, mTargetDay;
    private Button mButtonCalculate;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the detail view
        View view = inflater.inflate(R.layout.edit_goals, container, false);

        //Get the text view
        mSpGoals = view.findViewById(R.id.spinner_goals);
        mSpActivity = view.findViewById(R.id.spinner_activityLevel);
        mSpTargetWeight =  view.findViewById(R.id.spinner_targetWeight);
        mSpTargetYear =  view.findViewById(R.id.spinner_targetYear);
        mSpTargetMonth =  view.findViewById(R.id.spinner_targetMonth);
        mTargetDay =  view.findViewById(R.id.spinner_targetDay);
        mButtonCalculate = view.findViewById(R.id.button_calculate);

        mSpGoals.setSelection(getIndex(mSpGoals, getArguments().getString("goals")));
        mSpActivity.setSelection(getIndex(mSpActivity, getArguments().getString("goals")));
        mSpTargetWeight.setSelection(getIndex(mSpTargetWeight, getArguments().getString("goals")));
        mSpTargetYear.setSelection(getIndex(mSpTargetYear, getArguments().getString("goals")));
        mSpTargetMonth.setSelection(getIndex(mSpTargetMonth, getArguments().getString("goals")));
        mTargetDay.setSelection(getIndex(mTargetDay, getArguments().getString("goals")));

        return view;
    }

    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }
}
