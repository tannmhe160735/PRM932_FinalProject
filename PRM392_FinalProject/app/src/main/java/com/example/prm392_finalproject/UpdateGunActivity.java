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
    private Button btnBackGun2;
    private DBContext dbContext = new DBContext(this);

    private Gun_skin gun_skin;

    private void bindingView(){
        edtUpdateGunId = findViewById(R.id.edtUpdateGunId);
        edtUpdateGunName = findViewById(R.id.edtUpdateGunName);
        edtUpdateGunPrice = findViewById(R.id.edtUpdateGunPrice);
        edtUpdateGunImage = findViewById(R.id.edtUpdateGunImage);
        spinnerUpdateBundle = findViewById(R.id.spinnerUpdateBundle);
        btnUpdateGun = findViewById(R.id.btnUpdateGun);
        btnDeleteGun = findViewById(R.id.btnDeleteGun);
        btnBackGun2 = findViewById(R.id.btnBackGun2);
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<com.example.prm392_finalproject.Bundle> bundles = getBundleList();
        for (com.example.prm392_finalproject.Bundle item:
                bundles) {
            arrayList.add(item.getName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUpdateBundle.setAdapter(arrayAdapter);
    }

    private void bindingAction(){
        btnUpdateGun.setOnClickListener(this::onBtnUpdateGunClick);
        btnDeleteGun.setOnClickListener(this::onBtnDeleteGunClick);
        btnBackGun2.setOnClickListener(this::onBtnBackGunClick);
    }

    private void onBtnBackGunClick(View view) {
        Intent i = new Intent(this, MenuActivity.class);
        setResult(0,i);
        finish();
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
        Intent i = new Intent(this, MenuActivity.class);
        setResult(0,i);
        finish();
    }

    private void onBtnUpdateGunClick(View view) {
        String gun_id = edtUpdateGunId.getText().toString().trim();
        String gun_name = edtUpdateGunName.getText().toString().trim();
        String gun_price = edtUpdateGunPrice.getText().toString().trim();
        String gun_image = edtUpdateGunImage.getText().toString().trim();
        String bundle_name = spinnerUpdateBundle.getSelectedItem().toString();
        com.example.prm392_finalproject.Bundle bundle = getBundle(bundle_name);
        try{
            dbContext.updateGun_Skin(gun_name, gun_price, gun_image, String.valueOf(bundle.getId()), gun_id);
        }catch (Exception ex){
            Toast.makeText(this, "Update thất bại", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Update thành công", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MenuActivity.class);
        setResult(0,i);
        finish();
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
        spinnerUpdateBundle.setSelection(gun_skin.getBundle()-1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_gun);
        bindingView();
        bindingAction();
        receivingIntent();
    }

    private ArrayList<com.example.prm392_finalproject.Bundle> getBundleList(){
        ArrayList<com.example.prm392_finalproject.Bundle> bundles= new ArrayList<>();
        Cursor ps = dbContext.getAllBundle();
        if(ps == null){
            return null;
        }
        if (ps.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = ps.getInt(ps.getColumnIndex(DBContext.TABLE_BUNDLE_COL_BUNDLE_ID));
                @SuppressLint("Range") String name = ps.getString(ps.getColumnIndex(DBContext.TABLE_BUNDLE_COL_BUNDLE_NAME));
                com.example.prm392_finalproject.Bundle bundle = new com.example.prm392_finalproject.Bundle();
                bundle.setId(id);
                bundle.setName(name);
                bundles.add(bundle);
            } while (ps.moveToNext());
        }
        return bundles;
    }

    private com.example.prm392_finalproject.Bundle getBundle(String bundle_name){
        com.example.prm392_finalproject.Bundle bundle = new com.example.prm392_finalproject.Bundle();
        Cursor ps = dbContext.getBundleByName(bundle_name);
        if(ps == null){
            return null;
        }
        if (ps.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = ps.getInt(ps.getColumnIndex(DBContext.TABLE_BUNDLE_COL_BUNDLE_ID));
                @SuppressLint("Range") String name = ps.getString(ps.getColumnIndex(DBContext.TABLE_BUNDLE_COL_BUNDLE_NAME));
                bundle.setId(id);
                bundle.setName(name);
            } while (ps.moveToNext());
        }
        return bundle;
    }
}