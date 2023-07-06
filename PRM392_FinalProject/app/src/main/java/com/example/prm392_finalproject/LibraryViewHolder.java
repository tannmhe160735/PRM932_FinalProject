package com.example.prm392_finalproject;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LibraryViewHolder extends RecyclerView.ViewHolder {
    private TextView tvLibraryName;
    private TextView tvLibraryPrice;

    private Context context;
    private void bindingView() {
        tvLibraryName = itemView.findViewById(R.id.tvLibraryName);
        tvLibraryPrice = itemView.findViewById(R.id.tvLibraryPrice);
    }

    public LibraryViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        bindingView();

    }

    public void setLibrary(Gun_skin gun_skin) {
        tvLibraryName.setText(gun_skin.getName());
        tvLibraryPrice.setId(gun_skin.getPrice());
    }
}
