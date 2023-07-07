package com.example.prm392_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LibraryActivity extends AppCompatActivity {

    private EditText edtSearch;
    private RecyclerView rcvLibrary;
    private ImageButton igBtnSearch;
    private List<Gun_skin> gunSkins;
    private FloatingActionButton add_gun_button;
    private DBContext dbContext;

//    private void fakeData() {
//        gunSkins = new ArrayList<>();
//        Gun_skin gunSkin1 = new Gun_skin(1, 1, "Phantom Quang Phổ", 2675, "https://static.wikia.nocookie.net/valorant/images/f/f4/SPECTRUM_Phantom.png/revision/latest?cb=20210908214445");
//        Gun_skin gunSkin2 = new Gun_skin(1, 1, "Phantom", 9999, "https://valorantinfo.com/images/vn/spectrum-phantom_valorant_full_skin_154393.webp");
//        Gun_skin gunSkin3 = new Gun_skin(1, 1, "Phantom Quang Phổ", 2675, "https://valorantinfo.com/images/vn/spectrum-phantom_valorant_full_skin_154393.webp");
//        Gun_skin gunSkin4 = new Gun_skin(1, 1, "Phantom Quang Phổ", 2675, "https://valorantinfo.com/images/vn/spectrum-phantom_valorant_full_skin_154393.webp");
//        Gun_skin gunSkin5 = new Gun_skin(1, 1, "Phantom Quang Phổ", 2675, "https://valorantinfo.com/images/vn/spectrum-phantom_valorant_full_skin_154393.webp");
//        Gun_skin gunSkin6 = new Gun_skin(1, 1, "Phantom Quang Phổ", 2675, "https://valorantinfo.com/images/vn/spectrum-phantom_valorant_full_skin_154393.webp");
//        Gun_skin gunSkin7 = new Gun_skin(1, 1, "Phantom Quang Phổ", 2675, "https://valorantinfo.com/images/vn/spectrum-phantom_valorant_full_skin_154393.webp");
//        Gun_skin gunSkin8 = new Gun_skin(1, 1, "Phantom Quang Phổ", 2675, "https://valorantinfo.com/images/vn/spectrum-phantom_valorant_full_skin_154393.webp");
//        Gun_skin gunSkin9 = new Gun_skin(1, 1, "Phantom Quang Phổ", 2675, "https://valorantinfo.com/images/vn/spectrum-phantom_valorant_full_skin_154393.webp");
//        gunSkins.add(gunSkin1);
//        gunSkins.add(gunSkin2);
//        gunSkins.add(gunSkin3);
//        gunSkins.add(gunSkin4);
//        gunSkins.add(gunSkin5);
//        gunSkins.add(gunSkin6);
//        gunSkins.add(gunSkin7);
//        gunSkins.add(gunSkin8);
//        gunSkins.add(gunSkin9);
//    }

    private void getData() {
        gunSkins = new ArrayList<>();
        Cursor ps = dbContext.getAllGun();
        if(ps == null){
            return;
        }
        if (ps.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = ps.getInt(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_ID));
                @SuppressLint("Range") int bundle = ps.getInt(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_BUNDLE));
                @SuppressLint("Range") String name = ps.getString(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_NAME));
                @SuppressLint("Range") int price = ps.getInt(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_PRICE));
                @SuppressLint("Range") String image = ps.getString(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_IMAGE));
                Gun_skin gun_skin = new Gun_skin(id, bundle, name, price, image);
                gunSkins.add(gun_skin);
            } while (ps.moveToNext());
        }
    }

    public void bindingView(){
        edtSearch = findViewById(R.id.edtSearch);
        rcvLibrary = findViewById(R.id.rcvLibrary);
        igBtnSearch = findViewById(R.id.igBtnSearch);
        add_gun_button = findViewById(R.id.add_gun_button);
        dbContext = new DBContext(this);
    }

    public void bindingAction(){
        add_gun_button.setOnClickListener(this::onBtnAddGunClick);
    }

    private void onBtnAddGunClick(View view) {
        Intent i = new Intent(this, AddGunActivity.class);
        startActivity(i);
    }

    private void bindDataToRcvDictionary() {
        rcvLibrary.setLayoutManager(new LinearLayoutManager(this));
        rcvLibrary.setAdapter(new AdapterLibrary(this,gunSkins));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        bindingView();
        bindingAction();
//        fakeData();
        getData();
        bindDataToRcvDictionary();
    }
}