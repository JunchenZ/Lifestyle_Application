package com.example.lifestyleapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MasterFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public MasterFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.master, container, false);

        //Get the recycler view
        mRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.rv_Master);

        //Tell Android that we know the size of the recyclerview doesn't change
        mRecyclerView.setHasFixedSize(true);

        //Set the layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        ArrayList<String> menus = new ArrayList<>();
        menus.add("Goal");
        menus.add("Weather");
        menus.add("Hiking");
        ArrayList<String> menuDescriptions = new ArrayList<>();
        menuDescriptions.add("Review your goals and progress");
        menuDescriptions.add("Current conditions");
        menuDescriptions.add("Find hiking destinations near you");


        //Set the adapter
        mAdapter = new RCViewAdapter(menus, menuDescriptions);
        mRecyclerView.setAdapter(mAdapter);

        return fragmentView;
    }
}
