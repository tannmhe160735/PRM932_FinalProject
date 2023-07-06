package com.example.prm392_finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterBundle extends RecyclerView.Adapter<HolderBundle> {
    private Context context;
    private List<Bundle> Bunders;
    private LayoutInflater inflater;

    public AdapterBundle(Context context, List<Bundle> bunders) {
        this.context = context;
        this.Bunders = bunders;
        inflater = LayoutInflater.from(context);
    }

    public AdapterBundle(Context context, List<Bundle> bunders, LayoutInflater inflater) {
        this.context = context;
        this.Bunders = bunders;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public HolderBundle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bundle,parent,false);
        return new HolderBundle(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderBundle holder, int position) {
        Bundle bundles = Bunders.get(position);
        holder.setBundle(bundles);
    }

    @Override
    public int getItemCount() {
        return Bunders.size();
    }
}
