package com.example.lifestyleapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RCViewAdapter extends RecyclerView.Adapter<RCViewAdapter.ViewHolder>{

    private Context mContext;
    private DataPasser mDataPasser;
    private ArrayList<String> menus, menuDescriptions;

    public RCViewAdapter(ArrayList<String> menus, ArrayList<String> menuDescriptions) {
        this.menus = menus;
        this.menuDescriptions = menuDescriptions;
    }

    @NonNull
    @Override
    public RCViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        try{
            mDataPasser = (DataPasser) mContext;
        }catch(ClassCastException e){
            throw new ClassCastException(mContext.toString()+ " must implement OnDataPass");
        }

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View myView = layoutInflater.inflate(R.layout.menu,parent,false);
        return new ViewHolder(myView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        protected View itemLayout;
        protected TextView itemTvMenu, itemTvDes;

        public ViewHolder(View view){
            super(view);
            itemLayout = view;
            itemTvMenu = (TextView) view.findViewById(R.id.tv_menu);
            itemTvDes = (TextView) view.findViewById(R.id.tv_menu_description);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.itemTvMenu.setText(menus.get(position));
        holder.itemTvDes.setText(menuDescriptions.get(position));
        holder.itemLayout.setOnClickListener(new View.OnClickListener(){
                                                 @Override
                                                 public void onClick(View view) {
                                                     mDataPasser.passData(position);
                                                 }
                                             }
        );
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    public static interface DataPasser{
        public void passData(int position);
    }
}
