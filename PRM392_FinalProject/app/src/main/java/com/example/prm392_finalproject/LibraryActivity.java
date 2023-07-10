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
import android.widget.Toast;

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
        getData();
    }

    public void bindingAction(){
        add_gun_button.setOnClickListener(this::onBtnAddGunClick);
        igBtnSearch.setOnClickListener(this::onBtnSearchGunClick);
    }

    private void onBtnSearchGunClick(View view) {
        gunSkins = new ArrayList<>();
        String name = edtSearch.getText().toString().trim();
        Cursor ps = dbContext.getGunLikeName(name);
        if(ps == null){
            return;
        }
        if (ps.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = ps.getInt(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_ID));
                @SuppressLint("Range") int bundle = ps.getInt(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_BUNDLE));
                @SuppressLint("Range") String gun_name = ps.getString(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_NAME));
                @SuppressLint("Range") int price = ps.getInt(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_PRICE));
                @SuppressLint("Range") String image = ps.getString(ps.getColumnIndex(DBContext.TABLE_GUN_SKIN_COL_GUN_IMAGE));
                Gun_skin gun_skin = new Gun_skin(id, bundle, gun_name, price, image);
                gunSkins.add(gun_skin);
            } while (ps.moveToNext());
        }
        bindDataToRcvDictionary();
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
        bindDataToRcvDictionary();
    }
}