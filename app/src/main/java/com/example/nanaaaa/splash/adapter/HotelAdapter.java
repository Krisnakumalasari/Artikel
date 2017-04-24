package com.example.nanaaaa.splash.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nanaaaa.splash.R;
import com.example.nanaaaa.splash.models.Hotel;

import java.util.Collections;
import java.util.List;

/**
 * Created by Nanaaaa on 4/20/2017.
 */

public class HotelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Hotel> data = Collections.emptyList();
    Hotel current;
    int currentPos = 0;
    private Context context;
    private LayoutInflater inflater;

    public HotelAdapter(Context context, List<Hotel> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        Hotel current = data.get(position);
        holder1.tvJudul.setText(current.title);
        holder1.tvDeskripsi.setText(current.category);
        Glide.with(context).load(current.thumbnail)
                .placeholder(R.drawable.ic_error_black)
                .error(R.drawable.ic_error_black)
                .into(holder1.ivFoto);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoto;
        TextView tvJudul;
        TextView tvDeskripsi;

        public ViewHolder(View itemView) {
            super(itemView);
            ivFoto = (ImageView) itemView.findViewById(R.id.imageView);
            tvJudul = (TextView) itemView.findViewById(R.id.textViewJudul);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.textViewDeskripsi);
        }
    }
}