package com.example.prm392_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class UpdateGunActivity extends AppCompatActivity {

    private EditText edtUpdateGunId;
    private EditText edtUpdateGunName;
    private EditText edtUpdateGunPrice;
    private EditText edtUpdateGunImage;
    private Spinner spinnerUpdateBundle;
    private Button btnUpdateGun;
    private Button btnDeleteGun;
    private DBContext dbContext;

    private Gun_skin gun_skin;

    private void bindingView(){
        edtUpdateGunId = findViewById(R.id.edtUpdateGunId);
        edtUpdateGunName = findViewById(R.id.edtUpdateGunName);
        edtUpdateGunPrice = findViewById(R.id.edtUpdateGunPrice);
        edtUpdateGunImage = findViewById(R.id.edtUpdateGunImage);
        spinnerUpdateBundle = findViewById(R.id.spinnerUpdateBundle);
        btnUpdateGun = findViewById(R.id.btnUpdateGun);
        btnDeleteGun = findViewById(R.id.btnDeleteGun);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Spectrum");
        arrayList.add("Elderflame");
        arrayList.add("Protocol 781-A");
        arrayList.add("Glitchpop");
        arrayList.add("Singularity");
        arrayList.add("BlastX");
        arrayList.add("Radiant Entertainment System");
        arrayList.add("");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUpdateBundle.setAdapter(arrayAdapter);
        dbContext = new DBContext(this);
    }

    private void bindingAction(){
        btnUpdateGun.setOnClickListener(this::onBtnUpdateGunClick);
        btnDeleteGun.setOnClickListener(this::onBtnDeleteGunClick);
    }

    private void onBtnDeleteGunClick(View view) {
        String gun_id = edtUpdateGunId.getText().toString().trim();
        try{
            dbContext.deleteGun_Skin(gun_id);
        }catch (Exception ex){
            Toast.makeText(this, "Delete thất bại", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Delete thành công", Toast.LENGTH_SHORT).show();
    }

    private void onBtnUpdateGunClick(View view) {
        String gun_id = edtUpdateGunId.getText().toString().trim();
        String gun_name = edtUpdateGunName.getText().toString().trim();
        String gun_price = edtUpdateGunPrice.getText().toString().trim();
        String gun_image = edtUpdateGunImage.getText().toString().trim();
        String bundle = String.valueOf(spinnerUpdateBundle.getSelectedItemPosition());
        try{
            dbContext.updateGun_Skin(gun_name, gun_price, gun_image, bundle, gun_id);
        }catch (Exception ex){
            Toast.makeText(this, "Update thất bại", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Update thành công", Toast.LENGTH_SHORT).show();
    }

    private void receivingIntent() {
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        Cursor ps = dbContext.getGunByName(name);
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
                gun_skin = new Gun_skin(id, bundle, gun_name, price, image);
            } while (ps.moveToNext());
        }
        edtUpdateGunId.setText(String.valueOf(gun_skin.getId()));
        edtUpdateGunName.setText(gun_skin.getName());
        edtUpdateGunPrice.setText(String.valueOf(gun_skin.getPrice()));
        edtUpdateGunImage.setText(gun_skin.getImageUrl());
        spinnerUpdateBundle.setSelection(gun_skin.getBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_gun);
        bindingView();
        bindingAction();
        receivingIntent();
    }
}