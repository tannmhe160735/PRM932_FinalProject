package com.example.prm392_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    private DBContext dbContext;

    private void bindingView(){
        edtGunName = findViewById(R.id.edtUpdateGunName);
        edtGunPrice = findViewById(R.id.edtUpdateGunPrice);
        edtGunImage = findViewById(R.id.edtUpdateGunImage);
        spinnerBundle = findViewById(R.id.spinnerUpdateBundle);
        btnAddGun = findViewById(R.id.btnUpdateGun);
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
        spinnerBundle.setAdapter(arrayAdapter);
        dbContext = new DBContext(this);
    }

    private void bindingAction(){
        btnAddGun.setOnClickListener(this::onBtnAddGunClick);
    }

    private void onBtnAddGunClick(View view) {
        String gun_name = edtGunName.getText().toString().trim();
        String gun_price = edtGunPrice.getText().toString().trim();
        String gun_image = edtGunImage.getText().toString().trim();
        String bundle = String.valueOf(spinnerBundle.getSelectedItemPosition());
        try{
            dbContext.insertGun_Skin(gun_name, gun_price, gun_image, bundle);
        }catch (Exception ex){
            Toast.makeText(this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Lưu thành công", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, LibraryActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gun);
        bindingView();
        bindingAction();
    }
}