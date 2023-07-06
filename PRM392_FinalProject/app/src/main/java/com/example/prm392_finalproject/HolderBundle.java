package com.example.prm392_finalproject;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HolderBundle extends RecyclerView.ViewHolder {
    private ImageView img;
    private TextView tvname;
    private TextView tvprice;


    private Context context;

    private void bindingView() {
        img = itemView.findViewById(R.id.img);
        tvname = itemView.findViewById(R.id.tvTitle);
        tvprice = itemView.findViewById(R.id.tvPrice);
    }
    public HolderBundle(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        bindingView();

    }

    public void setBundle(Bundle bundles) {
        img.setImageResource(bundles.getImage());
        tvname.setText(bundles.getName());
        tvprice.setId(bundles.getPrice());
    }
}
