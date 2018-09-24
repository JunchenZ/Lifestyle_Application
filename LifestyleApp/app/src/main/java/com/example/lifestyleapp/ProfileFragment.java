package com.example.lifestyleapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    private TextView mTvName, mTvAge, mTvCountry, mTvCity, mTvHeight, mTvWeight, mTvSex, mTvStatus;

    public ProfileFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the detail view
        View view = inflater.inflate(R.layout.view_weather, container, false);

        //Get the text view
        mTvName = (TextView) view.findViewById(R.id.tv_name);
        mTvAge = (TextView) view.findViewById(R.id.tv_age);
        mTvCountry = (TextView) view.findViewById(R.id.tv_country);
        mTvCity = (TextView) view.findViewById(R.id.tv_city);
        mTvHeight = (TextView) view.findViewById(R.id.tv_height);
        mTvWeight = (TextView) view.findViewById(R.id.tv_weight);
        mTvSex = (TextView) view.findViewById(R.id.tv_sex);
        mTvStatus = (TextView) view.findViewById(R.id.tv_status);


        mTvName.setText(getArguments().getString("name"));
        mTvAge.setText(getArguments().getString("age"));
        mTvCountry.setText(getArguments().getString("country"));
        mTvCity.setText(getArguments().getString("city"));
        mTvHeight.setText(getArguments().getString("height"));
        mTvWeight.setText(getArguments().getString("weight"));
        mTvSex.setText(getArguments().getString("sex"));
        mTvStatus.setText(getArguments().getString("status"));

        return view;
    }
}
