package com.example.prm392_finalproject;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.InputStream;

public class Gun_SkinViewHolder extends RecyclerView.ViewHolder {
    private ImageView img;
    private TextView tvname;
    private TextView tvprice;
    private TextView tvdiscount;

    private Context context;
    private void bindingView() {
        img = itemView.findViewById(R.id.img);
        tvname = itemView.findViewById(R.id.tvTitle);
        tvprice = itemView.findViewById(R.id.tvPrice);
        tvdiscount = itemView.findViewById(R.id.tvdiscount);
    }

    public Gun_SkinViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        bindingView();

    }

    private void bindImgLinkToImageView(String link, ImageView imageView) {
        Glide
                .with(context)
                .load(link)
                .placeholder(R.drawable.img_loading)
                .error(R.drawable.img_crash)
                .into(imageView);
    }

    public void setGunskin(Gun_skin gun_skin) {
        tvname.setText(gun_skin.getName());
        tvprice.setText(String.valueOf(gun_skin.getPrice()));
        bindImgLinkToImageView(gun_skin.getImageUrl(), img);
//        tvdiscount.setText(String.valueOf(gun_skin.getDiscount()));
    }
}
