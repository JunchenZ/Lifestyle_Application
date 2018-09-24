package com.example.lifestyleapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WeatherFragment extends Fragment{
    private TextView mTvLocation, mTvWeather, mTvWind, mTvHumidity, mTvAir;

    public WeatherFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the detail view
        View view = inflater.inflate(R.layout.view_weather, container, false);

        //Get the text view
        mTvLocation = (TextView) view.findViewById(R.id.tv_location);
        mTvWeather = (TextView) view.findViewById(R.id.tv_weather);
        mTvWind = (TextView) view.findViewById(R.id.tv_wind);
        mTvHumidity = (TextView) view.findViewById(R.id.tv_humidity);
        mTvAir = (TextView) view.findViewById(R.id.tv_air);

        mTvLocation.setText(getArguments().getString("location"));
        mTvWeather.setText(getArguments().getString("weather"));
        mTvWind.setText(getArguments().getString("wind"));
        mTvHumidity.setText(getArguments().getString("humidity"));
        mTvAir.setText(getArguments().getString("air"));

        return view;
    }
}
