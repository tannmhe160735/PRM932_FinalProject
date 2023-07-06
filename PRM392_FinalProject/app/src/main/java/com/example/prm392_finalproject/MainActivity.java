package com.example.prm392_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Gun_skin> gun_skins;
    private TextView txttilte;
    private TextView txtprice;
    private RecyclerView rcv;

    @SuppressLint("SuspiciousIndentation")
    private void fakeData() {
        gun_skins = new ArrayList<>();
        gun_skins.add(new Gun_skin(1,3,"Tuan",1200,R.drawable.ic_launcher_background));
        gun_skins.add(new Gun_skin(2,1,"Anh",1400,R.drawable.ic_launcher_background));
        gun_skins.add(new Gun_skin(3,2,"Vu",1000,R.drawable.ic_launcher_background));

    }
    private void bindingView() {
        rcv = findViewById(R.id.rcv);
        txttilte = findViewById(R.id.TitleBunder);
        txtprice = findViewById(R.id.BunderPrice);
    }

    private void bindingAction() {

    }
    private void bindDataToRcvDictionary() {
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(new AdapterGunSkin(this,gun_skins));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bundle);
        bindingView();
        bindingAction();
        fakeData();
        bindDataToRcvDictionary();
    }
}