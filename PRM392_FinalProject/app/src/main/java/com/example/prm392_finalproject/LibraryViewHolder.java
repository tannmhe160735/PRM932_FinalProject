package com.example.prm392_finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class LibraryViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView tvLibraryName;
    private TextView tvLibraryPrice;
    private Context context;
    String userid;
    private static final int REQUEST_CODE_FOR_OPEN_UPDATEGUNACTIVITY = 3;
    private void bindingView() {
        imageView = itemView.findViewById(R.id.imageView);
        tvLibraryName = itemView.findViewById(R.id.tvLibraryName);
        tvLibraryPrice = itemView.findViewById(R.id.tvLibraryPrice);
        //get Userid session
        SharedPreferences sharedpreferences = context.getSharedPreferences(LoginActivity.MyPREFERENCES, context.MODE_PRIVATE);
        userid = sharedpreferences.getString("Userid", null);
        //
    }

    private void bindingAction(){
        if(Integer.parseInt(userid) == 2){
            imageView.setOnClickListener(this::onBtnUpdateGunClick);
            tvLibraryName.setOnClickListener(this::onBtnUpdateGunClick);
            tvLibraryPrice.setOnClickListener(this::onBtnUpdateGunClick);
        }
    }

    private void onBtnUpdateGunClick(View view) {
        Intent i = new Intent(context, UpdateGunActivity.class);
        String name = tvLibraryName.getText().toString();
        i.putExtra("name", name);
        ((Activity) context).startActivityForResult(i, REQUEST_CODE_FOR_OPEN_UPDATEGUNACTIVITY);
    }

    public LibraryViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        bindingView();
        bindingAction();
    }

    private void bindImgLinkToImageView(String link, ImageView imageView) {
        Glide
                .with(context)
                .load(link)
                .placeholder(R.drawable.img_loading)
                .error(R.drawable.img_crash)
                .into(imageView);
    }

    public void setLibrary(Gun_skin gun_skin) {
        tvLibraryName.setText(gun_skin.getName());
        tvLibraryPrice.setText(String.valueOf(gun_skin.getPrice()));
        bindImgLinkToImageView(gun_skin.getImageUrl(), imageView);
    }



}
