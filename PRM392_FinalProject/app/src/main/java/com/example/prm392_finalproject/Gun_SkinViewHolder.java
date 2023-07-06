package com.example.prm392_finalproject;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Gun_SkinViewHolder extends RecyclerView.ViewHolder {
    private ImageView img;
    private TextView tvname;
    private TextView tvprice;
    private TextView tvbundle;

    private Context context;
    private void bindingView() {
        img = itemView.findViewById(R.id.img);
        tvname = itemView.findViewById(R.id.tvTitle);
        tvprice = itemView.findViewById(R.id.tvPrice);
        tvbundle = itemView.findViewById(R.id.tvRank);
    }

    public Gun_SkinViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        bindingView();

    }

    public void setGunskin(Gun_skin gun_skin) {
        img.setImageResource(gun_skin.getImageId());
        tvname.setText(gun_skin.getName());
        tvprice.setId(gun_skin.getPrice());
        tvbundle.setId(gun_skin.getBundle());
    }
}
