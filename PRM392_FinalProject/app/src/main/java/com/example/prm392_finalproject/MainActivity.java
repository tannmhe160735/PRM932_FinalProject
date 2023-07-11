package com.example.prm392_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
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
        gun_skins.add(new Gun_skin(1,3,"Tuan",1200,"https://valorantinfo.com/images/vn/spectrum-phantom_valorant_full_skin_154393.webp"));
        gun_skins.add(new Gun_skin(2,1,"Anh",1400,"https://valorantinfo.com/images/vn/spectrum-phantom_valorant_full_skin_154393.webp"));
        gun_skins.add(new Gun_skin(3,2,"Vu",1000,"https://valorantinfo.com/images/vn/spectrum-phantom_valorant_full_skin_154393.webp"));
    }
    private void bindingView() {
        rcv = findViewById(R.id.rcvGun);
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
        //Tuan Anh
        setContentView(R.layout.activity_bundle);
        bindingView();
        bindingAction();
        fakeData();
        bindDataToRcvDictionary();
//        setContentView(R.layout.activity_login);

    }
}