package com.example.prm392_finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterGunSkin extends RecyclerView.Adapter<Gun_SkinViewHolder> {
    private Context context;
    private List<Gun_skin> gun_skins;
    private LayoutInflater inflater;

    public AdapterGunSkin(Context context, List<Gun_skin> gun_skins) {
        this.context = context;
        this.gun_skins = gun_skins;
        inflater = LayoutInflater.from(context);
    }

    public AdapterGunSkin(Context context, List<Gun_skin> gun_skins, LayoutInflater inflater) {
        this.context = context;
        this.gun_skins = gun_skins;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public Gun_SkinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gunskin,parent,false);
        return new Gun_SkinViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull Gun_SkinViewHolder holder, int position) {
        Gun_skin gun_skin = gun_skins.get(position);
        holder.setGunskin(gun_skin);
    }

    @Override
    public int getItemCount() {
        return gun_skins.size();
    }
}
