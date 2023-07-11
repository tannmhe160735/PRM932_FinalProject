package com.example.prm392_finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterLibrary extends RecyclerView.Adapter<LibraryViewHolder> {
    private Context context;
    private List<Gun_skin> gun_skins;
    private LayoutInflater inflater;

    public AdapterLibrary(Context context, List<Gun_skin> gun_skins) {
        this.context = context;
        this.gun_skins = gun_skins;
        inflater = LayoutInflater.from(context);
    }

    public AdapterLibrary(Context context, List<Gun_skin> gun_skins, LayoutInflater inflater) {
        this.context = context;
        this.gun_skins = gun_skins;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public LibraryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_library,parent,false);
        return new LibraryViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryViewHolder holder, int position) {
        Gun_skin gun_skin = gun_skins.get(position);
        holder.setLibrary(gun_skin);
    }

    @Override
    public int getItemCount() {
        return gun_skins.size();
    }
}
