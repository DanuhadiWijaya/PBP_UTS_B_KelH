package com.example.luxuryhotel;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luxuryhotel.databinding.AdapterRecyclerViewBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<Rooms> result;

    public RecyclerViewAdapter(Context context, ArrayList<Rooms> result){
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterRecyclerViewBinding adapterRecyclerViewBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.adapter_recycler_view, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(adapterRecyclerViewBinding);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Rooms rooms = result.get(position);
        holder.adapterRecyclerViewBinding.setRooms(rooms);
//        holder.adapterRecyclerViewBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        AdapterRecyclerViewBinding adapterRecyclerViewBinding;

        public MyViewHolder(@NonNull AdapterRecyclerViewBinding adapterRecyclerViewBinding){
            super(adapterRecyclerViewBinding.getRoot());
            this.adapterRecyclerViewBinding = adapterRecyclerViewBinding;


        }
        public void onClick(View view) {
            Toast.makeText(context, "You touch me?", Toast.LENGTH_SHORT).show();
        }
    }
}

