package com.example.prm392_finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class LibraryActivity extends AppCompatActivity {

    private EditText edtSearch;
    private RecyclerView rcvLibrary;
    private ImageButton igBtnSearch;
    private List<Gun_skin> gunSkins;

    private void fakeData() {
        gunSkins = new ArrayList<>();
        Gun_skin gunSkin1 = new Gun_skin(1, 1, "Phantom Quang Phổ", 2675, "https://i.pinimg.com/236x/ca/fe/b2/cafeb2e288f830b582092fdcceea5dee.jpg");
        Gun_skin gunSkin2 = new Gun_skin(1, 1, "Phantom", 9999, "https://valorantinfo.com/images/vn/spectrum-phantom_valorant_full_skin_154393.webp");
        Gun_skin gunSkin3 = new Gun_skin(1, 1, "Phantom Quang Phổ", 2675, "https://valorantinfo.com/images/vn/spectrum-phantom_valorant_full_skin_154393.webp");
        Gun_skin gunSkin4 = new Gun_skin(1, 1, "Phantom Quang Phổ", 2675, "https://valorantinfo.com/images/vn/spectrum-phantom_valorant_full_skin_154393.webp");
        Gun_skin gunSkin5 = new Gun_skin(1, 1, "Phantom Quang Phổ", 2675, "https://valorantinfo.com/images/vn/spectrum-phantom_valorant_full_skin_154393.webp");
        Gun_skin gunSkin6 = new Gun_skin(1, 1, "Phantom Quang Phổ", 2675, "https://valorantinfo.com/images/vn/spectrum-phantom_valorant_full_skin_154393.webp");
        Gun_skin gunSkin7 = new Gun_skin(1, 1, "Phantom Quang Phổ", 2675, "https://valorantinfo.com/images/vn/spectrum-phantom_valorant_full_skin_154393.webp");
        Gun_skin gunSkin8 = new Gun_skin(1, 1, "Phantom Quang Phổ", 2675, "https://valorantinfo.com/images/vn/spectrum-phantom_valorant_full_skin_154393.webp");
        Gun_skin gunSkin9 = new Gun_skin(1, 1, "Phantom Quang Phổ", 2675, "https://valorantinfo.com/images/vn/spectrum-phantom_valorant_full_skin_154393.webp");
        gunSkins.add(gunSkin1);
        gunSkins.add(gunSkin2);
        gunSkins.add(gunSkin3);
        gunSkins.add(gunSkin4);
        gunSkins.add(gunSkin5);
        gunSkins.add(gunSkin6);
        gunSkins.add(gunSkin7);
        gunSkins.add(gunSkin8);
        gunSkins.add(gunSkin9);
    }

    public void bindingView(){
        edtSearch = findViewById(R.id.edtSearch);
        rcvLibrary = findViewById(R.id.rcvLibrary);
        igBtnSearch = findViewById(R.id.igBtnSearch);
    }

    public void bindingAction(){

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
        fakeData();
        bindDataToRcvDictionary();
    }
}