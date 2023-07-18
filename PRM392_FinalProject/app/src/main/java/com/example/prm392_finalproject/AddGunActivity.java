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

public class AddGunActivity extends AppCompatActivity {

    private EditText edtGunName;
    private EditText edtGunPrice;
    private EditText edtGunImage;
    private Spinner spinnerBundle;
    private Button btnAddGun;
    private Button btnBackGun;
    private DBContext dbContext = new DBContext(this);

    private void bindingView(){
        edtGunName = findViewById(R.id.edtUpdateGunName);
        edtGunPrice = findViewById(R.id.edtUpdateGunPrice);
        edtGunImage = findViewById(R.id.edtUpdateGunImage);
        spinnerBundle = findViewById(R.id.spinnerUpdateBundle);
        btnAddGun = findViewById(R.id.btnUpdateGun);
        btnBackGun = findViewById(R.id.btnBackGun);
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<com.example.prm392_finalproject.Bundle> bundles = getBundleList();
        for (com.example.prm392_finalproject.Bundle item:
             bundles) {
            arrayList.add(item.getName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBundle.setAdapter(arrayAdapter);
    }

    private void bindingAction(){
        btnAddGun.setOnClickListener(this::onBtnAddGunClick);
        btnBackGun.setOnClickListener(this::onBtnBackGunClick);
    }

    private void onBtnBackGunClick(View view) {
        Intent i = new Intent(this, MenuActivity.class);
        setResult(0,i);
        finish();
    }

    private void onBtnAddGunClick(View view) {
        String gun_name = edtGunName.getText().toString().trim();
        String gun_price = edtGunPrice.getText().toString().trim();
        String gun_image = edtGunImage.getText().toString().trim();
        String bundle_name = spinnerBundle.getSelectedItem().toString();
        com.example.prm392_finalproject.Bundle bundle = getBundle(bundle_name);
        try{
            dbContext.insertGun_Skin(gun_name, gun_price, gun_image, String.valueOf(bundle.getId()));
        }catch (Exception ex){
            Toast.makeText(this, "Lưu Gun Skin thất bại", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Lưu Gun Skin thành công", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MenuActivity.class);
        setResult(0,i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gun);
        bindingView();
        bindingAction();
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

    private User getUser(String UserId) {
        User user = new User();
        Cursor ps = dbContext.getUserById(UserId);
        if(ps.getCount() < 1){
            return null;
        }
        if (ps.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = ps.getInt(ps.getColumnIndex(DBContext.TABLE_USER_COL_USER_ID));
                @SuppressLint("Range") String name = ps.getString(ps.getColumnIndex(DBContext.TABLE_USER_COL_USER_NAME));
                @SuppressLint("Range") String pass = ps.getString(ps.getColumnIndex(DBContext.TABLE_USER_COL_PASSWORD));
                @SuppressLint("Range") int role = ps.getInt(ps.getColumnIndex(DBContext.TABLE_USER_COL_ROLE));
                @SuppressLint("Range") boolean remember = (ps.getInt(ps.getColumnIndex(DBContext.TABLE_USER_COL_REMEMBER)) > 0);
                user = new User(id, name, pass, role, remember);
            } while (ps.moveToNext());
        }
        return user;
    }
}