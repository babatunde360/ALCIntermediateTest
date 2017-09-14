package com.example.android.alcintermediateapp.adapter;

import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.alcintermediateapp.R;
import com.example.android.alcintermediateapp.model.User;

import java.util.ArrayList;

/**
 * Created by tunde on 9/10/2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<User> musers;
    public DataAdapter(){

    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);

        ViewHolder viewholder = new ViewHolder(v);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            name =(TextView) itemView.findViewById(R.id.tv_name);
            image =(ImageView) itemView.findViewById(R.id.civ_image);
        }



    }
}
